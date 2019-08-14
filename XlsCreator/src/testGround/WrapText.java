package testGround;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WrapText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			    FileInputStream is = new FileInputStream(new File("bob.xlsx"));
			    XSSFWorkbook wb = new XSSFWorkbook(is);
			    String header = "123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789";
			    Sheet sheet = wb.getSheet("Sheet1");
			    sheet.setColumnWidth(0, 18000);
			    Row row = sheet.createRow(0);
			    Cell cell = row.createCell(0);

			    if(header.length() > 50){ //Length of String for my test
			      sheet.setColumnWidth(0, 18000); //Set column width, you'll probably want to tweak the second int
			      CellStyle style = wb.createCellStyle(); //Create new style
			      style.setWrapText(true); //Set wordwrap
			      cell.setCellStyle(style); //Apply style to cell
			      cell.setCellValue(header); //Write header
			    }

			    wb.write(new FileOutputStream(new File("bob.xlsx")));
			    wb.close();
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
	}

}
