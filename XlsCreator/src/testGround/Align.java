package testGround;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class Align {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Workbook wb = new HSSFWorkbook();  
	        try  (OutputStream os = new FileOutputStream("Javatpoint.xls")) {  
	        	
	            Sheet sheet = wb.createSheet("New Sheet");  
	            Row row = sheet.createRow(0);  
	            Cell cell = row.createCell(0);  
	            cell.setCellValue("Javatpoint");  
	            row.setHeight((short) 800);  
	            
	            CellStyle cellStyle = wb.createCellStyle();
	            cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
	            cell.setCellStyle(cellStyle);
	            
	            
	            row = sheet.createRow(5);   
	            cell = row.createCell(0);  
	            // Top Left alignment   
	            HSSFCellStyle style1 = (HSSFCellStyle) wb.createCellStyle();  
	            sheet.setColumnWidth(0, 8000);  
	            cell.setCellValue("Top Left");  
	            cell.setCellStyle(style1);  
	            row = sheet.createRow(6);   
	            cell = row.createCell(1);  
	            row.setHeight((short) 800);  
	            wb.write(os);  
	        }catch(Exception e) {  
	            System.out.println(e.getMessage());  
	        }  
	}

}
