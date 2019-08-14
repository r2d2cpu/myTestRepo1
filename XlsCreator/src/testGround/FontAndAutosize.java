package testGround;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FontAndAutosize {

	public static void main(String[] args) throws IOException, FileNotFoundException {

		HSSFWorkbook workbook = new HSSFWorkbook(); 
		String sheetName ="Mjenjač 1";
		HSSFSheet spreadsheet = workbook.createSheet(sheetName);
		HSSFRow row = spreadsheet.createRow(0);
		HSSFCell cell;
		
		row.setHeight((short) 900);

		
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		
		//style
		style.setFont(font);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setIndention((short)2);
		style.setWrapText(true);
		//font
		font.setFontName("Arial");

		// create/set cell & style
		cell = row.createCell(0);
		cell.setCellValue("New Celliiiiiiiinjorrrrrr  rrrrrrrg");
		cell.setCellStyle(style);

		// auto size
		spreadsheet.autoSizeColumn(0);

		// create file
		File aFile = new File("Your Filename.xls");
		FileOutputStream out = new FileOutputStream(aFile);
		workbook.write(out);
		workbook.close();
	}

}
