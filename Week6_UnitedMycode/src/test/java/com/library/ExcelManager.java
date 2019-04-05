package com.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelManager {
	final static Logger logger = Logger.getLogger(ExcelManager.class);

	private static String file;
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public ExcelManager(String excelFileName) {
		file = excelFileName;
	}

	public static void setExcelFile(String sheetName) {
		try {
			//FileOutputStream excelfile = new FileOutputStream(file);			
			ExcelWBook = new XSSFWorkbook();
			ExcelWSheet = ExcelWBook.createSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCellData(int rowNum, int colNum) {
		String cellData = null;
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			cellData = Cell.getStringCellValue();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return cellData;
	}

	public static void setCellData(String inputData, int rowNum, int colNum) {
		FileOutputStream fileOut = null;
		try {
			Row = ExcelWSheet.createRow(rowNum);
			Cell = Row.getCell(colNum);
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(inputData);
				fileOut = new FileOutputStream(file);
				ExcelWBook.write(fileOut);
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				logger.error("Error: " + e);
			}
		}
	}
	
	public String[][] getExcelData(String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalCols = sh.getColumns();
			int totalRows = sh.getRows();

			arrayExcelData = new String[totalRows - 1][totalCols];
			for (int i = 1; i < totalRows; i++) {
				// reading rows
				for (int j = 0; j < totalCols; j++) {
					// reading columns
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
					//System.out.println(arrayExcelData[i - 1][j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	public static void main(String[] args) {
		ExcelManager excelM = new ExcelManager("C:/Users/sunkara/Desktop/a/MyFirstExcel.xlsx");//need to change the path
		setExcelFile("MyFirstPage");
		setCellData("I love Java/Selenium programming", 0, 0);
		System.out.println("File is created.");
	}
}