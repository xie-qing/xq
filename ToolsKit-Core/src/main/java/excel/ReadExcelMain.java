package excel;

import com.tools.excel.Impl.ReadExcelServiceImpl;

public class ReadExcelMain {

	public static void main(String[] args) {
		ReadExcelService excelService = new ReadExcelServiceImpl();
		excelService.insertSql();
	}

}
