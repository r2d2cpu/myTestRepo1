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

public class FinalSheet {

	public static void main(String[] args) throws FileNotFoundException,IOException {
		
		String sheetName ="Tablica 1";
		String[] headerTitles = {"Ovlašteni mjenjač", "OIB", "Tečaj","Poslovnica Fine", "AUD", "CAD", "CZK","DKK","HUF","JPY","NOK","SEK","CHF","GBP","USD","BAM","EUR","PLN", "Ukupna protuvrijednost u kunama"};
        String[] dataValues = {"Pero mjenač d.o.o", "12345678901", "Zagreb -poslovnica","KUPEF", "", "233", "","","","","12","123","","","","23","590","", "Ukupna protuvrijednost u kunama"};
        
		
		//Create Workbook and sheet
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		HSSFSheet spreadsheet = workbook.createSheet(sheetName);
		HSSFRow headerRow = spreadsheet.createRow(0);
		HSSFCell cell;
		
		//
		headerRow.setHeight((short) 900);

		
		
		//Create font & style
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		
		//style
		style.setFont(font);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setIndention((short)3);
		style.setWrapText(false);
		//font
		font.setFontName("Arial");
		
		
		
		
		// create/set cell & style
		//cell = row.createCell(0);
		
		//header Row
		for(int i = 0; i<headerTitles.length; i++) {
			cell = headerRow.createCell(i);
			cell.setCellValue(headerTitles[i]);
			cell.setCellStyle(style);
			//headerRow.createCell(i).setCellValue(headerTitles[i]);
        }
		
		
		// auto size
		//spreadsheet.autoSizeColumn(0);
		for (int i = headerRow.getFirstCellNum(); i <= headerRow.getLastCellNum(); i++) {
			spreadsheet.autoSizeColumn(i);
			}
		
		
		// create file
		File aFile = new File("Final.xls");
		FileOutputStream out = new FileOutputStream(aFile);
		workbook.write(out);
		workbook.close();
	}

}
