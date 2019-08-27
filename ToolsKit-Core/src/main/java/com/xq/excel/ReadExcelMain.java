package com.xq.excel;


import com.xq.excel.Impl.ReadExcelServiceImpl;

public class ReadExcelMain {

	public static void main(String[] args) {
		ReadExcelService excelService = new ReadExcelServiceImpl();
		excelService.insertSql();
	}

}
