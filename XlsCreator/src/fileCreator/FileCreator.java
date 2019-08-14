package fileCreator;

import java.io.FileNotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileCreator {

	private String filePath;
	private String fileName;
	private String fileExtension;
	private String fullPath;

	/**
	 * Create File
	 * 
	 * @author ojelenic
	 * @param filePath
	 * @param fileName
	 * @param fileExtension
	 */
	public FileCreator(String filePath, String fileName, String fileExtension) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.fullPath = filePath + fileName + "." + fileExtension;

		switch (fileExtension) {
		case "xls":
			createXls();
			break;
		case "pdf":
			createPdf();
			break;
		default:
			invalidFileExtension();
			break;
		}
	}

	private void invalidFileExtension() {
		// TODO Auto-generated method stub
		System.out.println("File with " + fileExtension + "");
	}

	protected void createPdf() {
		
		try {
            Document iText_Create_Table = new Document();
            PdfWriter.getInstance(iText_Create_Table, new FileOutputStream(fullPath));
            iText_Create_Table.open();
            
            PdfPTable my_first_table = new PdfPTable(3);            
            
            my_first_table.addCell("0,0 First Cell Data"); // Use AddCell to Add a string data in first Cell
            PdfPCell table_cell; // Create a PDFPCell Object
            table_cell=new PdfPCell(new Phrase("A Cell with Colspan of 2")); // Create a Phrase data for Colspan 2 First Row
            table_cell.setColspan(2); //Define the colspan for Cell object
            my_first_table.addCell(table_cell);//push the cell to the table
            
            /* Define a Cell Object with ROWSPAN of 2 and COLSPAN of 2 */
            table_cell=new PdfPCell(new Phrase("A Cell with COLSPAN of 2 and ROWSPAN of 2"));
            table_cell.setColspan(2); //Define the colspan for Cell object
            table_cell.setRowspan(2); //Define the ROWSPAN for Cell object
            my_first_table.addCell(table_cell);//push the cell to the table
            
            /* Add data in left over cells*/
            my_first_table.addCell("2,3 Row 2 Column 3");
            my_first_table.addCell("3,3 Row 3 Column 3");
            
            iText_Create_Table.add(my_first_table);                       
            iText_Create_Table.close();
        }
        catch (Exception i)
        {
            System.out.println(i);
        }
	
		
		// TODO Auto-generated method stub

		/*com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();
		try {
			PdfWriter.getInstance(pdfDocument, new FileOutputStream(fullPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pdfDocument.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);
			pdfDocument.add(chunk);
			pdfDocument.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	protected void createXls() {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("First Sheet");
		//HSSFRow row = sheet.createRow(0);
		//HSSFCell cell = row.createCell(0);

		
		
		
		
		//This data needs to be written (Object[])
				Map<String, Object[]> data = new TreeMap<String, Object[]>();
				data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
				data.put("2", new Object[] {1, "Amit", "Shukla"});
				data.put("3", new Object[] {2, "Lokesh", "Gupta"});
				data.put("4", new Object[] {3, "John", "Adwards"});
				data.put("5", new Object[] {4, "Brian", "Schultz"});
				 
				//Iterate over data and write to sheet
				Set<String> keyset = data.keySet();
				int rownum = 0;
				for (String key : keyset)
				{
				    Row row = sheet.createRow(rownum++);
				    Object [] objArr = data.get(key);
				    int cellnum = 0;
				    for (Object obj : objArr)
				    {
				       Cell cell = row.createCell(cellnum++);
				       if(obj instanceof String)
				            cell.setCellValue((String)obj);
				        else if(obj instanceof Integer)
				            cell.setCellValue((Integer)obj);
				    }
				}
		
		
		
		
		
		
		//cell.setCellValue("1. Cell");

		try {
			FileOutputStream fout = new FileOutputStream(fullPath);
			workbook.write(fout);
			workbook.close();
			System.out.println("File " + fullPath + " created");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
