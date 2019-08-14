package testGround;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

public class AddExcellFormulas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Calculate Simple Interest");
		  
		    Row header = sheet.createRow(20);
		    header.createCell(0).setCellValue("Pricipal");
		    header.createCell(1).setCellValue("RoI");
		    header.createCell(2).setCellValue("T");
		    header.createCell(3).setCellValue("Interest (P r t)");
		      
		    Row dataRow = sheet.createRow(21);
		    dataRow.createCell(0).setCellValue(14500d);
		    dataRow.createCell(1).setCellValue(9.25);
		    dataRow.createCell(2).setCellValue(3d);
		    dataRow.createCell(3).setCellFormula("A2*B2*C2");
		    
		//IMAGE
		    InputStream my_banner_image = null;
			try {
				my_banner_image = new FileInputStream("FINA-logo.jpg");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            /* Convert Image to byte array */
            byte[] bytes = null;
			try {
				bytes = IOUtils.toByteArray(my_banner_image);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            /* Add Picture to workbook and get a index for the picture */
            int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            /* Close Input Stream */
            try {
				my_banner_image.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}                
            /* Create the drawing container */
            HSSFPatriarch drawing = sheet.createDrawingPatriarch();
            /* Create an anchor point */
            ClientAnchor my_anchor = new HSSFClientAnchor();
            /* Define top left corner, and we can resize picture suitable from there */
            my_anchor.setCol1(20);
            my_anchor.setRow1(11);           
            /* Invoke createPicture and pass the anchor point and ID */
            HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
            /* Call resize method, which resizes the image */
            my_picture.resize();            
		      
            
            
         //IMAGE END 
            
		    try {
		        FileOutputStream out =  new FileOutputStream(new File("formulaDemo.xls"));
		        workbook.write(out);
		        out.close();
		        workbook.close();
		        System.out.println("Excel with foumula cells written successfully");
		          
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

}
