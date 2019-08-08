package com.tools.excel.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;

import com.tools.excel.ReadExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import static java.util.regex.Pattern.*;

@Service
public class ReadExcelServiceImpl implements ReadExcelService {

	public int insertSql() {
		String fileName = "C:/Users/admin3/Desktop/test1.xlsx";
		Workbook wb = getWorkBook(fileName);
		if (wb == null) {
			System.out.println("文件不存在！");
		}
		Sheet st = null;
		Row row = null;
		Cell cell = null;
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			StringBuffer head = new StringBuffer();
			st = wb.getSheetAt(i);
			String tableName = st.getSheetName();
			for (int j = st.getFirstRowNum(); j < st.getLastRowNum(); j++) {
				StringBuffer temp = new StringBuffer();
				row = st.getRow(j);
				if (j == st.getFirstRowNum()) {
					head.append("insert into ").append(tableName);
				} else {
					temp.append(head.toString()).append(" values");
				}
				for (int j2 = row.getFirstCellNum(); j2 < row.getLastCellNum(); j2++) {
					cell = row.getCell(j2);
					if (j == st.getFirstRowNum() && row.getFirstCellNum() == j2) {
						head.append("(").append(cell.getStringCellValue() + ",");
					} else if (j == st.getFirstRowNum() && row.getLastCellNum() - 1 == j2) {
						head.append(cell.getStringCellValue() + ")");
					} else if (j == st.getFirstRowNum()) {
						head.append(cell.getStringCellValue() + ",");
					}
					cell.getCellType();
					if (j != st.getFirstRowNum() && row.getFirstCellNum() == j2) {
						temp.append("(").append(getJavaValue(cell) + ",");
					} else if (j != st.getFirstRowNum() && row.getLastCellNum() - 1 == j2) {
						temp.append(getJavaValue(cell) + ");");
					} else if (j != st.getFirstRowNum()) {
						temp.append(getJavaValue(cell) + ",");
					}

				}
				System.out.println(temp.toString());
			}
		}

		return 0;
	}

	/**
	 * 
	 * @param fileName 文件名称
	 * @return Workbook
	 */
	public Workbook getWorkBook(String fileName) {
		File file = new File(fileName);

		Workbook wb = null;
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			System.out.println(suffix);
			if (suffix.equals(".xls")) {
				wb = new HSSFWorkbook(in);
			} else {
				wb = new XSSFWorkbook(in);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 根据不同情况获取Java类型
	 * @param cell XSSFCell类型单元格
	 * @return 返回Object类型值
	 * @since -- ::{@link #getValueOfNumericCell()}
	 */
	public static Object getJavaValue(Cell cell) {
		Object o = null;
		int cellType = cell.getCellType();
		switch (cellType) {
		case Cell.CELL_TYPE_BLANK:
			o = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
			o = "Bad value!";
			break;
		case Cell.CELL_TYPE_NUMERIC:
			o = getValueOfNumericCell(cell);
			break;
		case Cell.CELL_TYPE_STRING:
			o = "'" + cell.getStringCellValue() + "'";
			break;
		case Cell.CELL_TYPE_FORMULA:
			try {
				o = getValueOfNumericCell(cell);
			} catch (IllegalStateException e) {
				try {
					o = cell.getRichStringCellValue().toString();
				} catch (IllegalStateException e1) {
					o = cell.getErrorCellValue();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			o = cell.getRichStringCellValue().getString();
		}
		return o;
	}

	// 获取数字类型的cell值
	private static Object getValueOfNumericCell(Cell cell) {
		Boolean isDate = DateUtil.isCellDateFormatted(cell);
		Double d = cell.getNumericCellValue();
		Object o = null;
		if (isDate) {
			o = DateFormat.getDateTimeInstance().format(cell.getDateCellValue());
		} else {
			o = getRealStringValueOfDouble(d);
		}
		return o;
	}

	// 处理科学计数法与普通计数法的字符串显示，尽最大努力保持精度
	private static String getRealStringValueOfDouble(Double d) {
		String doubleStr = d.toString();
		boolean b = doubleStr.contains("E");
		int indexOfPoint = doubleStr.indexOf('.');
		if (b) {
			int indexOfE = doubleStr.indexOf('E');
			// 小数部分
			BigInteger xs = new BigInteger(doubleStr.substring(indexOfPoint + BigInteger.ONE.intValue(), indexOfE));
			// 指数
			int pow = Integer.valueOf(doubleStr.substring(indexOfE + BigInteger.ONE.intValue()));
			int xsLen = xs.toByteArray().length;
			int scale = xsLen - pow > 0 ? xsLen - pow : 0;
			doubleStr = String.format("%." + scale + "f", d);
		} else {
			java.util.regex.Pattern p = compile(".$");
			java.util.regex.Matcher m = p.matcher(doubleStr);
			if (m.find()) {
				doubleStr = doubleStr.replace(".0", "");
			}
		}
		return doubleStr;
	}

}
