package testGround;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {
	
	private static String fullPath = "Pdf.pdf";


	public static void main(String[] args) {
		createPdf();
		
	}
	
	public static void createPdf() {
		try {
            Document pdfDocuement = new Document();
            PdfWriter.getInstance(pdfDocuement, new FileOutputStream(fullPath));
            pdfDocuement.open();
            
            PdfPTable table = new PdfPTable(3);            
            
           
            
            table.addCell("0,0 First Cell Data"); // Use AddCell to Add a string data in first Cell
            PdfPCell table_cell; // Create a PDFPCell Object
            table_cell=new PdfPCell(new Phrase("A Cell with Colspan of 2")); // Create a Phrase data for Colspan 2 First Row
            table_cell.setColspan(2); //Define the colspan for Cell object
            table.addCell(table_cell);//push the cell to the table
            
            /* Define a Cell Object with ROWSPAN of 2 and COLSPAN of 2 */
            table_cell=new PdfPCell(new Phrase("A Cell with COLSPAN of 2 and ROWSPAN of 2"));
            table_cell.setColspan(2); //Define the colspan for Cell object
            table_cell.setRowspan(2); //Define the ROWSPAN for Cell object
            table.addCell(table_cell);//push the cell to the table
            
            /* Add data in left over cells*/
            table.addCell("2,3 Row 2 Column 3");
            table.addCell("3,3 Row 3 Column 3");
            
            pdfDocuement.add(table);                       
            pdfDocuement.close();
            System.out.println("Pdf created ");
        }
        catch (Exception i)
        {
            System.out.println(i);
        }
	}

}
