package com.tools.excel.service;

import com.tools.excel.service.Impl.ReadExcelServiceImpl;

public class ReadExcelMain {

	public static void main(String[] args) {
		ReadExcelService excelService = new ReadExcelServiceImpl();
		excelService.insertSql();
	}

}
