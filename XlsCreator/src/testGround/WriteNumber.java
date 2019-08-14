
package testGround;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WriteNumber {
	public static void main(String[] args) throws IOException {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("format sheet");
		CellStyle style;
		DataFormat format = wb.createDataFormat();
		Row row;
		Cell cell;
		short rowNum = 0;
		short colNum = 0;

		row = sheet.createRow(rowNum++);
		cell = row.createCell(colNum);
		cell.setCellValue(11111.25);
		style = wb.createCellStyle();
		style.setDataFormat(format.getFormat("0.0"));
		cell.setCellStyle(style);

		row = sheet.createRow(rowNum++);
		cell = row.createCell(colNum);
		cell.setCellValue(11111.25);
		style = wb.createCellStyle();
		style.setDataFormat(format.getFormat("#,##0.0000"));
		cell.setCellStyle(style);

		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		wb.write(fileOut);
		fileOut.close();
	}
}
