package Methods.NABERS_Common_Methods;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelRangeCopy {
    public static void main(String[] args) {
        try {
            // Specify the Excel file path
            String excelFilePath = "samle.xlsx";

            // Specify the sheet name and cell range
            String sheetName = "Sheet1";
            String cellRange = "A1:B5"; // Example range, modify as needed

            // Read Excel file
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFilePath)));
            Sheet sheet = workbook.getSheet(sheetName);

            Row vv=sheet.getRow(0);
            Cell c=vv.getCell(0);
            System.out.println(c.toString());
            // Iterate through rows and cells in the specified range
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.println("vinay  "+cell);
                    String cellRef = new CellReference(cell).formatAsString();
                    if (cellRef.startsWith(cellRange)) {
                        System.out.println("Salala  "+cell);
                        System.out.print(cell.toString() + "\t");
                    }
                }
                System.out.println(); // Move to the next row
            }

            // Close the workbook
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}












////import com.spire.xls.CellRange;
////import com.spire.xls.ExcelVersion;
////import com.spire.xls.Workbook;
////import com.spire.xls.Worksheet;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.chrome.ChromeDriver;
////
////import java.awt.*;
////import java.awt.event.KeyEvent;
//
//
////public class ExcelToClipboard {
////    public static void main(String[] args) throws AWTException {
//////        System.setProperty("webdriver.chrome.driver","D:\\Nabers_FrameWork\\src\\test\\java\\Driver\\chromedriver.exe");
//////        WebDriver driver=new ChromeDriver();
////        //Create a Workbook instance
////        Workbook wb = new Workbook();
////
////        //Load a sample Excel file from disk
////        wb.loadFromFile("samle.xlsx", ExcelVersion.Version2013);
////
////        //Get the first worksheet
////        Worksheet sheet1 = wb.getWorksheets().get("Sheet1");
////
////        //Get the second worksheet
////        Worksheet sheet2 = wb.getWorksheets().get("Sheet2");
////
////
////        //Get the source range and target range
////        CellRange sourceRange = sheet1.getCellRange("A1:K5");
////        CellRange destRange = sheet2.getCellRange("A7:K11");
////
////        //Copy a specific cell range within a worksheet
////        sheet1.copy (sourceRange,destRange,true);
////
////
/////**/
////
////
////        //Auto fit column width in sheet 2
////        for (int i = 0; i < 15; i++) {
////            sheet2.autoFitColumn(i+1);
////        }
////
////        //Save the result file
////        wb.saveToFile("CopyRangeWithinSheet.xlsx", ExcelVersion.Version2013);
////        wb.dispose();
////        Robot robot=new Robot();
////        robot.keyPress(KeyEvent.VK_CONTROL);
////        robot.keyPress(KeyEvent.VK_V);
////        robot.keyRelease(KeyEvent.VK_V);
////        robot.keyRelease(KeyEvent.VK_CONTROL);
////
////    }
////}
////import org.apache.poi.ss.usermodel.*;
////import org.apache.poi.ss.util.CellReference;
////import org.apache.poi.xssf.usermodel.XSSFWorkbook;
////
////import java.awt.*;
////import java.awt.datatransfer.Clipboard;
////import java.awt.datatransfer.StringSelection;
////import java.io.File;
////import java.io.FileInputStream;
////import java.io.IOException;
////
////public class ExcelToClipboard {
////    public static void main(String[] args) {
////        try {
////            // Specify the Excel file path
////            String excelFilePath = "samle.xlsx";
////
////            // Specify the sheet name and cell range
////            String sheetName = "Sheet1";
////            String cellRange = "A1:K5"; // Example range, modify as needed
////
////            // Read Excel file
////            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFilePath)));
////            Sheet sheet = workbook.getSheet(sheetName);
////
////            // StringBuilder to store cell values with formatting
////            StringBuilder cellValues = new StringBuilder();
////
////            // Create a data formatter to format cell values
////            DataFormatter dataFormatter = new DataFormatter();
////
////            // Iterate through rows and cells, preserving formatting
////            for (Row row : sheet) {
////                for (Cell cell : row) {
////                    String cellRef = new CellReference(cell).formatAsString();
//////                    if (cellRef.startsWith(cellRange)) {
//////                        String formattedValue = dataFormatter.formatCellValue(cell);
//////                        System.out.println(formattedValue+" "+row+" "+cell);
//////                        cellValues.append(formattedValue).append("\t"); // Use tab as a delimiter
//////                    }
////                    if (cellRef.startsWith(cellRange)) {
////                        cellValues.append(cell.toString()).append("\t");
////                    }
////                }
////                cellValues.append("\n");
////            }
////
////            // Close the workbook
////            workbook.close();
////
////            // Copy cell values with formatting to clipboard
////            copyToClipboard(cellValues.toString());
////
////            System.out.println("Cell values with formatting copied to clipboard.");
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    private static void copyToClipboard(String text) {
////        StringSelection selection = new StringSelection(text);
////        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
////        clipboard.setContents(selection, null);
////    }
////}
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellReference;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.awt.*;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class ExcelToClipboard {
//    public static void main(String[] args) {
//        try {
//            // Specify the Excel file path
//            String excelFilePath = "samle.xlsx";
//
//            // Specify the sheet name and cell range
//            String sheetName = "Sheet1";
//            String cellRange = "A1:A10"; // Example range, modify as needed
//
//            // Read Excel file
//            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFilePath)));
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            // Extract cell values from the specified range
//            StringBuilder cellValues = new StringBuilder();
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    String cellRef = new CellReference(cell).formatAsString();
//                    if (cellRef.startsWith(cellRange)) {
//                        cellValues.append(cell.toString()).append("\n");
//                    }
//                }
//            }
//
//            // Close the workbook
//            workbook.close();
///**/
//            // Copy cell values to clipboard
//            copyToClipboard(cellValues.toString());
//
//            System.out.println("Cell values copied to clipboard.");
//            Robot robot=new Robot();
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void copyToClipboard(String text) {
//        StringSelection selection = new StringSelection(text);
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(selection, null);
//    }
//}