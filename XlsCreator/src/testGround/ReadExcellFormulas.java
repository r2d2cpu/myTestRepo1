package testGround;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcellFormulas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try
		    {
		        FileInputStream file = new FileInputStream(new File("formulaDemo.xls"));
		 
		        //Create Workbook instance holding reference to .xlsx file
		        HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		         
		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = workbook.getSheetAt(0);
		 
		        //Iterate through each rows one by one
		        Iterator<Row> rowIterator = sheet.iterator();
		        while (rowIterator.hasNext())
		        {
		            Row row = rowIterator.next();
		            //For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();
		             
		            while (cellIterator.hasNext())
		            {
		                Cell cell = cellIterator.next();
		                //Check the cell type after eveluating formulae
		                //If it is formula cell, it will be evaluated otherwise no change will happen
		                switch (evaluator.evaluateInCell(cell).getCellType())
		                {
		                    case NUMERIC:
		                        System.out.print(cell.getNumericCellValue() + "\t");
		                        break;
		                    case STRING:
		                        System.out.print(cell.getStringCellValue() + "\t");
		                        break;
		                    case FORMULA:
		                        //Not again
		                        break;
		                }
		            }
		            System.out.println("");
		        }
		        file.close();
		        workbook.close();
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
	}

}
