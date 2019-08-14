package testGround;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcell {

    public static void main(String[] args) throws Exception{

        ArrayList data = new ArrayList();

        FileInputStream file = new FileInputStream("D:\\\\TEST files\\\\excel1.xls");
        HSSFWorkbook book = new HSSFWorkbook(file);
        HSSFSheet s = book.getSheet("TestSteps");

    Iterator itr = s.iterator();
    while (itr.hasNext()) {
        Row rowitr = (Row) itr.next();
        Iterator cellitr = rowitr.cellIterator();
        while(cellitr.hasNext()) {
            Cell celldata = (Cell) cellitr.next();

            switch(celldata.getCellType()) {
            case STRING:
                data.add(celldata.getStringCellValue());
                break;
            case NUMERIC:
                data.add(celldata.getNumericCellValue());
                break;
            case BOOLEAN:
                data.add(celldata.getBooleanCellValue());
                break;
            }
        }
    }

    for (int i=0;i<data.size();i++) {
        if(data.get(i).equals("Sharan")) {
            System.out.println(data.get(i));
            System.out.println(data.get(i+1));
            System.out.println(data.get(i+2));
            System.out.println(data.get(i+3));          
        }
        if(data.get(i).equals("Kiran")) {
            System.out.println(data.get(i));
            System.out.println(data.get(i+1));
            System.out.println(data.get(i+2));
            System.out.println(data.get(i+3));
        }
        if(data.get(i).equals("Jhade")) {
            System.out.println(data.get(i));
            System.out.println(data.get(i+1));
            System.out.println(data.get(i+2));
            System.out.println(data.get(i+3));
}
}       
}
}