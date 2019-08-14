package testGround;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.util.IOUtils;

public class AddImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Create a Workbook and Worksheet */
        HSSFWorkbook my_workbook = new HSSFWorkbook();
        HSSFSheet my_sheet = my_workbook.createSheet("MyBanner");     
        
      //Create font & style
      		HSSFCellStyle style = my_workbook.createCellStyle();
      		HSSFFont font = my_workbook.createFont();
      		
      		//style
      		style.setFont(font);
      		style.setVerticalAlignment(VerticalAlignment.TOP);
      		style.setAlignment(HorizontalAlignment.CENTER);
      		//style.setIndention((short)3);
      		style.setWrapText(false);
      		//font
      		font.setFontName("Arial");
       /* Cell cell = row.createCell(0);
        CellStyle style = my_workbook.createCellStyle(); //Create new style
	      style.setWrapText(true); //Set wordwrap
	      cell.setCellStyle(style); //Apply style to cell*/
	      
        
	   // CellStyle css = my_workbook.createCellStyle();
	   // css.setAlignment(HorizontalAlignment.RIGHT);
     //   css.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.TOP);
        
        String[] headerTitles = {"Ovlašteni mjenjač", "OIB", "Tečaj","Poslovnica Fine", "AUD", "CAD", "CZK","DKK","HUF","JPY","NOK","SEK","CHF","GBP","USD","BAM","EUR","PLN", "Ukupna protuvrijednost u kunama"};
        String[] dataValues = {"Mjenjačnica 123 d.o.o.", "145345345541", "Zagreb -poslovnica","KUPEF", "44", "", "","5445","","","12","","","","","23","590","", "23233,454"};
        String[] dataValues2 = {"Pero mjenač d.o.o", "12345678901", "Zagreb -poslovnica","KUPEF", "", "233", "","","","","12","123","","","","23","590","", "432423423,454"};
        String[] sum = {"", "UKUPNO", "","", "", "233", "","","","","12","123","","","","23","590","", "432423423,454"};
        
        Row header = my_sheet.createRow(11);
        
        for(int i = 0; i<headerTitles.length; i++) {
        	header.createCell(i).setCellValue(headerTitles[i]);
        }
        
	    header.setHeight((short) 500);
	   // header.setRowStyle(css);
	   
	
	      
	    Row dataRow = my_sheet.createRow(12);
	    Row dataRow2 = my_sheet.createRow(13);
	    Row dataRow3 = my_sheet.createRow(14);
	    
	    dataRow2.createCell(2).setCellValue(9.25);
	   /* dataRow.createCell(1).setCellValue(14500d);
	    dataRow.createCell(2).setCellValue(9.25);
	    dataRow.createCell(3).setCellValue(3d);
	    dataRow.createCell(4).setCellFormula("A13*B13*C13");*/
	    
	    //Spoji celije (od reda, do reda, od celije, do celije)
	    
	    my_sheet.addMergedRegion(new CellRangeAddress(0,3,0,3));
	    
	    for(int i = 0; i<dataValues.length; i++) {
	    	dataRow.createCell(i).setCellValue(dataValues[i]);
        }
	    
	    for(int i = 0; i<dataValues2.length; i++) {
	    	dataRow2.createCell(i).setCellValue(dataValues2[i]);
        }
	    
	/*    
///**********************    
	    // Create a row and put some cells in it.
	    Row row = my_sheet.createRow((short) 1);
        Row row5 = my_sheet.createRow((short) 5);
        Cell cell = row.createCell((short) 1);
        style = my_workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        cell = row5.createCell((short) 1);
        cell.setCellValue("X21");
        cell.setCellStyle(style);

        style = my_workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row5.createCell((short) 2);
        cell.setCellValue("X22");
        cell.setCellStyle(style);

        style = my_workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row5.createCell((short) 3);
        cell.setCellValue("X23");
        cell.setCellStyle(style);
        style = my_workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row5.createCell((short) 4);
        cell.setCellValue("X24");
        cell.setCellStyle(style);

        style = my_workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row5.createCell((short) 5);
        cell.setCellValue("X25");
        cell.setCellStyle(style);
	    
  ///**********************        
	  */  
	    CellStyle style2 = my_workbook.createCellStyle();
	    //style2.setFillBackgroundColor(HSSFColorPredefined.BRIGHT_GREEN.getIndex());
	    style2.setFillForegroundColor(IndexedColors.CORAL.getIndex());
	    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    SheetConditionalFormatting sheetCF = my_sheet.getSheetConditionalFormatting();
	   /* 
	    // Condition 1: Formula Is   =A2=A1   (White Font)
	    ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");
	    PatternFormatting fill1 = rule1.createPatternFormatting();
	    fill1.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.index);
	    fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	 
	    CellRangeAddress[] regions = {
	            CellRangeAddress.valueOf("A1:Z100")
	    };
	 
	    sheetCF.addConditionalFormatting(regions, rule1);
	    

	    my_sheet.createRow(10).createCell(1).setCellValue("Shade Alternating Rows");
	    my_sheet.createRow(11).createCell(1).setCellValue("Condition: Formula Is  =MOD(ROW(),2)   (Light Green Fill)");
	    */
	    
        
	    for(int i = 0; i<sum.length; i++) {
	    	
	    	style = my_workbook.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        //cell = row.createCell((short) 3);
	        dataRow3.createCell(i).setCellValue(sum[i]);
	      //  cell.setCellValue("X3");
	       // cell.setCellStyle(style);
	        
	    	
	    	dataRow3.createCell(i).setCellStyle(style);
        }
	  // dataRow3.setRowStyle(style2);
	    //for(int rownum = 10; rownum <= 14; rownum++) my_sheet.getRow(rownum).getCell(0).setCellStyle(style2);
	    
	    //CellStyle wrapCellStyle = my_workbook.createCellStyle();
        
        
        System.out.println("Prvi red  " + my_sheet.getFirstRowNum() + " Zadnji red  "+ my_sheet.getLastRowNum());
        
        //Border
	    PropertyTemplate pt = new PropertyTemplate();  
	   // pt.drawBorders(range, borderType, color, extent););
	    pt.drawBorders(new CellRangeAddress(my_sheet.getFirstRowNum(), my_sheet.getLastRowNum(), dataRow.getFirstCellNum(), (dataRow.getLastCellNum() -1)),  BorderStyle.MEDIUM, BorderExtent.OUTSIDE);  
        pt.drawBorders(new CellRangeAddress(my_sheet.getFirstRowNum(), my_sheet.getLastRowNum(), dataRow.getFirstCellNum(), (dataRow.getLastCellNum() -1)), BorderStyle.THIN,  BorderExtent.INSIDE); 
        //END border
        
        
        /* Read the input image into InputStream */
        InputStream my_banner_image = null;
		try {
			my_banner_image = new FileInputStream("images/FINA-logo_white.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /* Convert Image to byte array */
        byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(my_banner_image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        /* Add Picture to workbook and get a index for the picture */
        int my_picture_id = my_workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        /* Close Input Stream */
        try {
			my_banner_image.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                
        /* Create the drawing container */
        HSSFPatriarch drawing = my_sheet.createDrawingPatriarch();
        /* Create an anchor point */
        ClientAnchor my_anchor = new HSSFClientAnchor();
        /* Define top left corner, and we can resize picture suitable from there */
        my_anchor.setCol1(0);
        my_anchor.setRow1(0);           
        
 
		
        /* Invoke createPicture and pass the anchor point and ID */
        HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
        /* Call resize method, which resizes the image */
        my_picture.resize(4);            
        /* Write changes to the workbook */
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("workbook.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	pt.applyBorders(my_sheet);  
        	System.out.println("Zadnja kolona " + my_sheet.getLastRowNum() + " Prva celija "+ dataRow.getFirstCellNum() + " Zadnji broj celije " + dataRow.getLastCellNum());
        	
        	// Column auto-sizing
          /*  for (columnIndex = 0; columnIndex < table.getLastHeaderRow().getColumns(ReservedFormat.ALL, ReservedFormat.XLSX).size(); columnIndex++) {
              if (exportConf.getAutoSize()) {
                sheet.autoSizeColumn(columnIndex);
              }
            }*/
        	
        	
        	int MIN_COL_WIDTH = 10 << 8;
            int MAX_COL_WIDTH = 100 << 8;
        	for (int i = dataRow.getFirstCellNum(); i <= dataRow.getLastCellNum(); i++) {
        		my_sheet.autoSizeColumn(i);
        	int cw = (int) (my_sheet.getColumnWidth(i) * 0.8);
                 my_sheet.setColumnWidth(i, Math.max(Math.min(cw, MAX_COL_WIDTH), MIN_COL_WIDTH));
            }
        	
        
        	//PROMJENA FORMULE I Dohvacanje zadnje celije
        	 my_sheet.getRow(my_sheet.getLastRowNum()).getCell(dataRow.getLastCellNum()-1).setCellFormula("A13+B13+C13");
        	
        	
   /*
        	  Font font = my_workbook.createFont();
        	  font.setFontName("Impact");
        	  font.setFontHeightInPoints((short)14);
        	  font.setBold(true);
        	  wrapCellStyle.setFont(font);
        	  wrapCellStyle.setWrapText(true);
        	  wrapCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        	  wrapCellStyle.setAlignment(HorizontalAlignment.CENTER);
        	  wrapCellStyle.setLocked(true);
        	  wrapCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        	  wrapCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        	  wrapCellStyle.setBorderBottom(BorderStyle.THIN);
        	  wrapCellStyle.setBorderLeft(BorderStyle.THIN);
        	*/
        	
        	
        	//Print
        	PrintSetup printSetup = my_sheet.getPrintSetup();
        	  printSetup.setLandscape(true);
        	  printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        	 my_sheet.setPrintGridlines(false);
        	//EndPrint
        	//my_sheet.autoSizeColumn(3);
			my_workbook.write(out);
			System.out.println("Writing ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
			my_workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	   
	}

}
