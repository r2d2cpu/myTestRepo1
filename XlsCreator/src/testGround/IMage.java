package testGround;

import java.io.FileOutputStream;  
import java.io.OutputStream;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
public class IMage {  
    public static void main(String[] args) {  
         try (OutputStream os = new FileOutputStream("Javatpoint2.xls")) {  
             Workbook wb = new HSSFWorkbook();  
             Sheet sheet = wb.createSheet("Sheet");  
             sheet.groupRow( 4, 10 );  
             sheet.groupColumn( 2, 8 );  
             wb.write(os);  
         }catch(Exception e) {  
             System.out.println(e.getMessage());  
         }  
    }  
}  