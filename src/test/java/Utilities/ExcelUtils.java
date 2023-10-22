package Utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtils {
    private static XSSFWorkbook xss;
    private static XSSFSheet xssSheet;

    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabarray = null;
        try {
            FileInputStream excelFile = new FileInputStream(FilePath);
             xss = new XSSFWorkbook(excelFile);
             xssSheet = xss.getSheet(SheetName);
             int rowCount  = xssSheet.getLastRowNum();
             int colCount = xssSheet.getRow(0).getPhysicalNumberOfCells();
             tabarray = new String[rowCount][colCount];
             for(int i = 0; i < rowCount; i++ ) {
                 for (int j = 0; j < colCount; j++) {
                     tabarray[i][j] = xssSheet.getRow(i).getCell(j).getStringCellValue();
                 }
             }
        } catch (FileNotFoundException e) {
            System.out.print("Excel File doesn't exist ");
        }
        return tabarray;
    }
}
