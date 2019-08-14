package com.hpb;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.util.IOUtils;

public class XlsGenerator {
	
		//Set  variables
		String filePath;
	    Short headerHeight = 500;
		Short dataHeight = 300;
		Short sumRowHeigth = 350;
		String imagePath = "images/finaLogo.jpg";
	    

	protected String createXls(String path) throws IOException {
		
		this.filePath = path;
		
		LocalDate date = LocalDate.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String addressTitle = "Financijska agencija";
		String address = "Ulica grada Vukovara 70, 10000 Zagreb";
		String tableTitle= "Izvještaj o obavljenim transakcijama otkupa strane gotovine od ovlaštenih mjenjača Hrvatske poštanske banke u Fini na dan " + date.format(formatter);

		
//GET DATA FROM DATABASE
		String[] headerTitles = {"Ovlašteni mjenjač", "OIB", "Tečaj","Poslovnica Fine", "AUD", "CAD", "CZK","DKK","HUF","JPY","NOK","SEK","CHF","GBP","USD","BAM","EUR","PLN", "Ukupna protuvrijednost u kunama"};
		String[] dataValues1 = {"Mjenjačnica 123 d.o.o.", "145345345541", "Zagreb -poslovnica","KUPEF", "44", "", "","5445","","","12","","","","","23","590","", "23233,454"};
		String[] dataValues2 = {"Pero mjenač d.o.o", "12345678901", "Zagreb -poslovnica","KUPEF", "", "233", "","","","","12","123","","","","23","590","", "432423423,454"};
        String[] sum = {"", "UKUPNO", "","", "", "233", "","","","","12","123","","","","23","590","", "432423423,454"};
        
//Create woorkbook
		// Create a workbook object
		Workbook workbook = new HSSFWorkbook();
		// Create sheet
        Sheet sheet = workbook.createSheet("Tablica 1");
        //Create style
        CellStyle style = workbook.createCellStyle();
        
//Create font & style
  		//Header font
        Font headerFont = workbook.createFont();
  		headerFont.setFontName("Arial");
  		headerFont.setBold(true);
  		//Data font
  		Font dataFont = workbook.createFont();
  		dataFont.setFontName("Arial");
  		dataFont.setFontHeightInPoints((short) 10 );
  		//Address title font
  		Font addressTitleFont = workbook.createFont();
  		addressTitleFont.setFontName("Arial");
  		addressTitleFont.setBold(true);
  		//Address
  		Font addressFont = workbook.createFont();
  		addressFont.setFontHeightInPoints((short) 10);
  		addressFont.setFontName("Arial");
  		addressFont.setBold(false);
  		//Table title font
  		Font tableTitleFont = workbook.createFont();
  		tableTitleFont.setFontHeightInPoints((short) 11);
  		tableTitleFont.setFontName("Arial");
  		tableTitleFont.setBold(true);
  	  		
//Create cell
        Cell cell;
        
//Create rows
  		
        //Adress title
  		Row adressTitleRow = sheet.createRow((short)5);
  		style = workbook.createCellStyle();
	    	//style
	  		style.setFont(addressTitleFont);
	  		cell = adressTitleRow.createCell((short) 0);
			cell.setCellValue(addressTitle );
	        cell.setCellStyle(style);
        //Address
        Row adressRow = sheet.createRow((short)6);
  		style = workbook.createCellStyle();
	    	//style
	  		style.setFont(addressFont);
	  		cell = adressRow.createCell((short) 0);
			cell.setCellValue(address);
	        cell.setCellStyle(style);
        //Table title
        Row tableTitleRow = sheet.createRow((short)8);
        sheet.addMergedRegion(new CellRangeAddress(8,9,0,18));
        
  		style = workbook.createCellStyle();
	    	//style
	  		style.setFont(tableTitleFont);
	  		style.setVerticalAlignment(VerticalAlignment.CENTER);
	  		style.setAlignment(HorizontalAlignment.CENTER);
	  		cell = tableTitleRow.createCell((short) 0);
			cell.setCellValue(tableTitle);
	        cell.setCellStyle(style);        
  		//Header row
  		Row headerRow = sheet.createRow((short) 12);
  		headerRow.setHeight(headerHeight);
  		//data1 row
  		Row dataRow1 = sheet.createRow((short) 13);
  		dataRow1.setHeight(dataHeight);
  		//data2 row
  		Row dataRow2 = sheet.createRow((short) 14);
  		dataRow2.setHeight(dataHeight);
  		//Sum row
  		Row sumRow = sheet.createRow((short) 15);
  		sumRow.setHeight(sumRowHeigth);
        

        

//Create image
  		//rowspan,colspan
  		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0,0));
  		//sheet.addMergedRegion(new CellRangeAddress(0,4,0,18));
        InputStream inputStream = new FileInputStream(imagePath);
        //Get the contents of an InputStream as a byte[].
        byte[] bytes = IOUtils.toByteArray(inputStream);
        //Adds a picture to the workbook
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        //close the input stream
        inputStream.close();
      
        //Returns an object that handles instantiating concrete classes
        CreationHelper helper = workbook.getCreationHelper();
      
        //Creates the top-level drawing patriarch.
        Drawing<?> drawing = sheet.createDrawingPatriarch();
      
        //Create an anchor that is attached to the worksheet
        ClientAnchor anchor = helper.createClientAnchor();
        //set top-left corner for the image
        anchor.setCol1(0);
        anchor.setRow1(0);
        //Creates a picture
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        pict.resize(1,2.7);
 

        
//Header  
        //Header Row
        for(int i = 0; i < headerTitles.length; i++) {     	
        	style = workbook.createCellStyle();
        	//style
      		style.setFont(headerFont);
      		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      		style.setVerticalAlignment(VerticalAlignment.CENTER);
      		style.setAlignment(HorizontalAlignment.CENTER);
      		//style.setIndention((short)3);
      		style.setWrapText(false);
            
            cell = headerRow.createCell((short) i);
            cell.setCellValue(headerTitles[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
 //Data 1   
        //Data row1
        for(int i = 0; i < headerTitles.length; i++) {     	
        	style = workbook.createCellStyle();
        	//style
      		style.setFont(dataFont);
      		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      		style.setVerticalAlignment(VerticalAlignment.CENTER);
      		style.setAlignment(HorizontalAlignment.RIGHT);
      		//style.setIndention((short)3);
      		style.setWrapText(false);
            
            cell = dataRow1.createCell((short) i);
            cell.setCellValue(dataValues1[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
//Data 2   
        //Data row2
        for(int i = 0; i < headerTitles.length; i++) {     	
        	style = workbook.createCellStyle();
        	//style
      		style.setFont(dataFont);
      		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      		style.setVerticalAlignment(VerticalAlignment.CENTER);
      		style.setAlignment(HorizontalAlignment.RIGHT);
      		//style.setIndention((short)3);
      		style.setWrapText(false);
            
            cell = dataRow2.createCell((short) i);
            cell.setCellValue(dataValues2[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
//SUM   
        //Data row3
        for(int i = 0; i < headerTitles.length; i++) {     	
        	style = workbook.createCellStyle();
        	//style
      		style.setFont(dataFont);
      		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      		style.setVerticalAlignment(VerticalAlignment.CENTER);
      		style.setAlignment(HorizontalAlignment.RIGHT);
      		//style.setIndention((short)3);
      		style.setWrapText(false);
            
            cell = sumRow.createCell((short) i);
            cell.setCellValue(sum[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

//Create border
	    PropertyTemplate pt = new PropertyTemplate();  
	    pt.drawBorders(new CellRangeAddress(12, sheet.getLastRowNum(), headerRow.getFirstCellNum(), (sumRow.getLastCellNum() -1)),  BorderStyle.MEDIUM, BorderExtent.OUTSIDE);  
        pt.drawBorders(new CellRangeAddress(12, sheet.getLastRowNum(), headerRow.getFirstCellNum(), (sumRow.getLastCellNum() -1)), BorderStyle.THIN,  BorderExtent.INSIDE); 
        //apply
        pt.applyBorders(sheet); 
//Set printing type       
        PrintSetup ps = sheet.getPrintSetup();
        ps.setFitWidth( (short) 1);
        ps.setFitHeight( (short) 1);
        ps.setLandscape(true);
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        sheet.setFitToPage(true);
// Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(filePath );
        workbook.write(fileOut);
        //close
        workbook.close();
        fileOut.close();
       
		return "Xls " + filePath + " kreiran!";
	}


}
