package testGround;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableStyleInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Demonstrates how to create a simple table using Apache POI.
 */
public class ExcellAstable {

    public static void main(String[] args) throws IOException {

        try (Workbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = (XSSFSheet) wb.createSheet();

            // Set which area the table should be placed in
            AreaReference reference = wb.getCreationHelper().createAreaReference(
                    new CellReference(0, 0), new CellReference(2, 2));

            // Create
            XSSFTable table = sheet.createTable(reference); //creates a table having 3 columns as of area reference
            // but all of those have id 1, so we need repairing
            table.getCTTable().getTableColumns().getTableColumnArray(1).setId(2);
            table.getCTTable().getTableColumns().getTableColumnArray(2).setId(3);

            table.setName("Test");
            table.setDisplayName("Test_Table");

            // For now, create the initial style in a low-level way
            table.getCTTable().addNewTableStyleInfo();
            table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");

            // Style the table
            XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
            style.setName("TableStyleMedium2");
            style.setShowColumnStripes(false);
            style.setShowRowStripes(true);
            style.setFirstColumn(false);
            style.setLastColumn(false);
            style.setShowRowStripes(true);
            style.setShowColumnStripes(true);

            // Set the values for the table
            XSSFRow row;
            XSSFCell cell;
            for (int i = 0; i < 3; i++) {
                // Create row
                row = sheet.createRow(i);
                for (int j = 0; j < 3; j++) {
                    // Create cell
                    cell = row.createCell(j);
                    if (i == 0) {
                        cell.setCellValue("Column" + (j + 1));
                    } else {
                        cell.setCellValue((i + 1.0) * (j + 1.0));
                    }
                }
            }

            // Save
            try (FileOutputStream fileOut = new FileOutputStream("ooxml-table.xlsx")) {
                wb.write(fileOut);
            }
        }
    }
}