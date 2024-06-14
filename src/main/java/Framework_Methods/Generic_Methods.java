package Framework_Methods;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generic_Methods {

    public static void fillExecutionDataIntoExcelSheet(int rowValue, String cellValue, String dataSheetPathAndName, String cellValueIntoDataSheet) throws IOException, InvalidFormatException {
        String globalPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\global.properties";
        int cellNumber=findColumnPositionInExcel(dataSheetPathAndName,ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(),0,cellValue);
        writeDataIntoExcelCell(cellNumber,rowValue, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"),cellValueIntoDataSheet);
    }
    public static String globalPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\global.properties";
    static String HTMLLogInfoPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\HTML_Log_Info.properties";

    public static boolean isColumnAvaiInExcel(String dataSheetFile, String dataSheetName, int rowNumber, String columnHeader) throws IOException, InvalidFormatException {
        File file = new File(dataSheetFile);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(dataSheetName);
        Row row = sheet.getRow(rowNumber);

        boolean bool = false;

        for (int column = 0; column < row.getLastCellNum(); column++) {
            Cell celll = row.getCell(column);
            String columnVal = celll.toString();
            if (columnVal.equalsIgnoreCase(columnHeader)) {
                bool = true;
                break;
            } else {
                bool = false;
            }
        }
        workbook.close();
        return bool;
    }

    public static int findColumnPositionInExcel(String dataSheetFile, String dataSheetName, int rowNumber, String columnHeader) throws IOException, InvalidFormatException {
        File file = new File(dataSheetFile);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(dataSheetName);
        Row row = sheet.getRow(rowNumber);
        int columnPosition = -1;
        try {
            for (int column = 0; column < row.getLastCellNum(); column++) {
                Cell cell = row.getCell(column);
                String columnVal = cell.toString();
                if (columnVal.equalsIgnoreCase(columnHeader)) {
                    columnPosition = column;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        workbook.close();
        return columnPosition;
    }






    public static int findRowPositionInExcel(String dataSheetFile, String dataSheetName, int colNumber, String columnHeader) throws IOException, InvalidFormatException {
        File file = new File(dataSheetFile);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(dataSheetName);
        int rowPosition = -1;
        try{
            for (int rowNumber=0;rowNumber<= sheet.getLastRowNum();rowNumber++)
            {
                Row row = sheet.getRow(rowNumber);
                Cell cell = row.getCell(colNumber);
                String columnVal = cell.toString();
                if(columnVal.equalsIgnoreCase(columnHeader))
                {
                    rowPosition = rowNumber;
                    break;
                }
            }
        }
        catch (Exception e)
        {

        }
        workbook.close();
        return rowPosition;
    }
















    public static void writeDataIntoExcelCell(int colValue, int rowValue, String sheetName) throws IOException {
        // ZipSecureFile.setMinInflateRatio(0);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath")+ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet xs = wb.getSheet(sheetName);
        FileOutputStream fos = null;
        XSSFCell columnNumber = null;
        XSSFRow rowNumber = null;
        int colNum = colValue;
        rowNumber = xs.getRow(rowValue);
        if (rowNumber == null) {
            rowNumber = xs.createRow(rowValue);
        }
        columnNumber = rowNumber.getCell(colNum);
        if (columnNumber == null) {
            columnNumber = rowNumber.createCell(colNum);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String current_Date = dateFormat.format(Calendar.getInstance().getTime());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        TimeZone etTimeZone = TimeZone.getTimeZone("Australia/Melbourne");
        timeFormat.setTimeZone(etTimeZone);
        String current_Time = timeFormat.format(Calendar.getInstance().getTime());
        columnNumber.setCellValue(current_Time);
        //System.out.println(current_Time);
        fos = new FileOutputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath")+ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName"));
        wb.write(fos);
        wb.close();
        fos.close();

    }

    public static String[] readExecutionMethodsFromExcel(String filePath, String fileName, String sheetName) throws IOException {
        String v=System.getProperty("user.dir") + "\\" + filePath + "\\" + fileName;
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\" + filePath + "\\" + fileName);
        XSSFWorkbook xwb = new XSSFWorkbook(file);
        //Need to change below sheetName to executedScriptName
        String[] arr_ExecutionMethods = new String[0];
        try {
            XSSFSheet xs = xwb.getSheet(sheetName);
            arr_ExecutionMethods = new String[xs.getLastRowNum() + 1];
//            for (int i = 0; i <= xs.getLastRowNum(); i++) {
//                Row row = xs.getRow(i);
//                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    arr_ExecutionMethods[i] = String.valueOf(row.getCell(j));
//                }
//            }
            for(int i=0;i<=xs.getLastRowNum();i++)
            {
                Row row=xs.getRow(i);
                Cell cell=row.getCell(0);
                arr_ExecutionMethods[i]= cell.toString();

            }
        } catch (Exception e) {
            System.out.println(e);
            writeLog("Error", "Execution sheet not found in Execution Data File");
            writeLog("Error", String.valueOf(e));
            arr_ExecutionMethods = null;
        }
        return arr_ExecutionMethods;
    }
    public static void writeDataIntoExcelCell(int colValue, int rowValue, String sheetName, String cellValue) throws IOException {
        // ZipSecureFile.setMinInflateRatio(0);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath")+ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet xs = wb.getSheet(sheetName);
        FileOutputStream fos = null;
        XSSFCell columnNumber = null;
        XSSFRow rowNumber = null;
        int colNum = colValue;
        rowNumber = xs.getRow(rowValue);
        if (rowNumber == null) {
            rowNumber = xs.createRow(rowValue);
        }
        columnNumber = rowNumber.getCell(colNum);
        if (columnNumber == null) {
            columnNumber = rowNumber.createCell(colNum);
        }
        columnNumber.setCellValue(cellValue);
        fos = new FileOutputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath")+ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName"));
        wb.write(fos);
        wb.close();
        fos.close();
    }
    public static String TimeDiffCalculation(Date StartTime, Date EndTime) throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        long timeDifference = EndTime.getTime() - StartTime.getTime();
        //System.out.println(StartTime);
        //System.out.println(EndTime);

        String TimeTaken = String.format("%s:%s:%s", Long.toString(TimeUnit.MILLISECONDS.toHours(Math.round(timeDifference))), TimeUnit.MILLISECONDS.toMinutes(Math.round(timeDifference)), TimeUnit.MILLISECONDS.toSeconds(Math.round(timeDifference)));
        //System.out.println(String.format("Time taken %s", TimeTaken));
        return TimeTaken;
    }

    public static boolean isSheetAvailableInExcel(String fileName, String sheetName) throws IOException, InvalidFormatException {
        File file = new File(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        boolean sheetAvaliableStatus = false;
        if (workbook.getNumberOfSheets() != 0) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetName(i).equals(sheetName)) {
                    sheetAvaliableStatus = true;
                    break;
                } else {
                    sheetAvaliableStatus = false;
                }
            }
        }
        workbook.close();
        return sheetAvaliableStatus;
    }

    public static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public static void createCustomDataAndTimeStampForFileNames(String dataTimeStamp) throws IOException {
        WriteDataIntoPropertiesFile(globalPropertiesFileNameAndPath, "customDataAndTimeStampForFileNames", String.valueOf(dataTimeStamp));

        WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "Scripts_Execution_Start_Time", getCustomTime());
    }

    public static void createReportFile() throws IOException {
        String logFileName = "Report_" + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("customDataAndTimeStampForFileNames");
        WriteDataIntoPropertiesFile(globalPropertiesFileNameAndPath, "executionReportFileName", logFileName);


    }
    public static String stringWhichHasToReplaceForPieChart(String text,int Pass,int Fail,int NotExecuted,int totalMethods,int methodNumber,String methodName)
    {
        int exe=Pass+Fail;
        String v=" <table width=\"100%\" border=\"1\">\n" +
//                "            <tr>\n" +
////                "                <th width=50%></th>\n" +
////                "                <th width=50%></th>\n" +
//                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <html>\n" +
                "                    <head>\n" +
                "                        <script type=\"text/javascript\" src=https://www.gstatic.com/charts/loader.js></script>\n" +
                "                        <script type=\"text/javascript\" name=\"vinay piechart\">\n" +
                "      google.charts.load(\"current\", {packages:[\"corechart\"]});\n" +
                "      google.charts.setOnLoadCallback(drawChart);\n" +
                "      function drawChart() {\n" +
                "        var data = google.visualization.arrayToDataTable([\n" +
                "          ['Task', 'Number of Executions'],\n" +
                "          ['Pass',     "+Pass+"],\n" +
                "          ['Fail',      "+Fail+"],\n" +
                "          ['Not Executed',      "+(totalMethods-exe)+"],\n" +
                "        ]);\n" +
                "\n" +
                "        var options = {\n" +
                "          title: '',\n" +
                "          width:600,\n" +
                "          height:400,\n" +
                "          colors: ['green', 'red', 'orange'],\n" +
                "          is3D: true,\n" +
                "          legend: {\n" +
                "          position: 'bottom'\n" +
                "        },\n" +
                "        pieSliceText: 'value'\n" +
                "        };\n" +
                "\n" +
                "        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d"+methodNumber+"'));\n" +
                "        chart.draw(data, options);\n" +
                "      }\n" +
                "\n" +
                "</script>\n" +
                "                    </head>\n" +
                "                    <body>\n" +
                "                    <div id=\"piechart_3d"+methodNumber+"\" style=\"width: 600px; height: 400px;\"></div>\n" +
                "                    </body>\n" +
                "                    </html>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "                    <script type=\"text/javascript\">\n" +
                "        google.charts.load(\"current\", {packages:['corechart']});\n" +
                "        google.charts.setOnLoadCallback(drawChart);\n" +
                "        function drawChart() {\n" +
                "          var data = google.visualization.arrayToDataTable([\n" +
                "            [\"Element\", \"\", { role: \"style\" } ],[\"Pass\", "+Pass+", 'green'],[\"Fail\", "+Fail+", 'red'],[\"Not Executed\", "+(totalMethods-exe)+", 'orange'], ]);\n" +
                "          var view = new google.visualization.DataView(data);\n" +
                "          view.setColumns([0, 1,\n" +
                "                           { calc: \"stringify\",\n" +
                "                             sourceColumn: 1,\n" +
                "                             type: \"string\",\n" +
                "                             role: \"annotation\" },\n" +
                "                           2]);\n" +
                "\n" +
                "          var options = {\n" +
                "            //title: \"Density of Precious Metals, in g/cm^3\",\n" +
                "            width: 600,\n" +
                "            height: 400,\n" +
                "            bar: {groupWidth: \"75%\"},\n" +
                "            legend: { position: \"none\" },\n" +
                "            vAxis: {\n" +
                "        gridlines: {\n" +
                "            color: 'none'\n" +
                "        },\n" +
                "    }\n" +
                "          };\n" +
                "          var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_values"+methodNumber+"'));\n" +
                "          chart.draw(view, options);\n" +
                "      }\n" +
                "  </script>\n" +
                "                    <div id=\"columnchart_values"+methodNumber+"\" style=\"width: 600px; height: 400px;\"></div>\n" +
                "                </td>\n" +
                "            </tr>\n" +"<tr><td colspan=\"2\"><br><center><h3>Fig. :- "+methodName+" Scenario Summary</h3></center><br></td></tr>"+
                "        </table><br><br>";
        text=text+v;

        return text;

    }
    public static  void replaceTextInHTMLWithpiechart(String text,String vv) throws IOException {

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\" + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("executionReportFileName") + ".html");
        // Get all the lines
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            String replacement=vv;
            // Do the replace operation
            List<String> list = stream.map(line -> line.replace(text, replacement)).collect(Collectors.toList());
            // Write the content back
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void replaceTextInHTML(String text, String replacement) throws IOException {

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\" + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("executionReportFileName") + ".html");
        // Get all the lines
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            // Do the replace operation
            List<String> list = stream.map(line -> line.replace(text, replacement)).collect(Collectors.toList());
            // Write the content back
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String customDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_YYYY_hh_mm_ss");
        Date date = new Date();
        dateFormat.format(date);
        String dateandtime = dateFormat.format(date).toString();
        return dateandtime;
    }

    public static String getCustomTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        TimeZone etTimeZone = TimeZone.getTimeZone("Australia/Melbourne");
        timeFormat.setTimeZone(etTimeZone);
        String current_Time = timeFormat.format(Calendar.getInstance().getTime());
        return current_Time;
    }

    public static String getCustomDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        Date date = new Date();
        dateFormat.format(date);
        String dateandtime = dateFormat.format(date).toString();
        return dateandtime;
    }

    public static Properties ReadDataFromPropertiesFile(String propertiesFileName) throws IOException {
        FileInputStream readPropertyFile = new FileInputStream(propertiesFileName);
        Properties pro = new Properties();
        pro.load(readPropertyFile);
        return pro;
    }


    //need to update below pageload method with default time
    public void pageLoadReadyState(WebDriver driver, String PageName) throws InterruptedException, IOException {
        String expectedPageTitle = "Test";
        Duration.ofSeconds(5);

        do {
            if (driver.getTitle().equalsIgnoreCase(PageName)) {
                break;
            }
        } while (!driver.getTitle().equalsIgnoreCase(expectedPageTitle));
    }

    public static void elementAvailabilityState(WebDriver driver, WebElement element, String elementName) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementName)));
    }

    public void stableZoomSizeBrowserWindow() throws InterruptedException, AWTException {
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_0);
    }
    public void zoomInBrowserWindowWithRobotClass(int val) throws InterruptedException, AWTException {
        Thread.sleep(1000);
        Robot robot = new Robot();
        for (int i = 0; i < val; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    public void zoomOutBrowserWindowWithRobotClass(int val) throws InterruptedException, AWTException {
        Thread.sleep(1000);
        Robot robot = new Robot();
        for (int i = 0; i < val; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    public static int convertDecimalStringToInteger(String decimalStringValue) {
        // Parse the decimal string as a double
        double doubleValue = Double.parseDouble(decimalStringValue);

        // Convert the double value to an integer using rounding
        int integerValue = (int) Math.round(doubleValue);

        return integerValue;
    }

    public static void WriteDataIntoPropertiesFile(String requiredPropertiesFileName, String propertiesKey, String propertiesValue) throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(requiredPropertiesFileName);
        properties.load(fis);
        properties.setProperty(propertiesKey, propertiesValue);
        FileOutputStream fos = new FileOutputStream(requiredPropertiesFileName);
        properties.store(fos, "LogFileFormat ==> Options ==> TextFile,Excel" + "\n" + "LogDataAppendedToExistingFileorNot ==> Options ==> Yes, No" + "\n" + "If LogFileFormate is null then by default TextLogs will be created.");
    }

    public static void writeLog(String Category, String LogString) throws IOException {
        boolean defaultLog = true;
        String arrlogFormats[] = new String[]{};
        String logFormats = ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileFormat");
        if (logFormats.contains(",")) {
            arrlogFormats = logFormats.split(",");
            for (int logFormatInit = 0; logFormatInit < arrlogFormats.length; logFormatInit++) {
                if (arrlogFormats[logFormatInit].contains("Excel")) {
                    writeLogInToExcelFile(Category, LogString);
                }
                if (arrlogFormats[logFormatInit].contains("TextFile")) {
                    writeLogInToTextFile(Category, LogString);
                }
            }
        } else if (logFormats.equalsIgnoreCase("Excel")) {
            writeLogInToExcelFile(Category, LogString);
        } else if (logFormats.equalsIgnoreCase("TextFile")) {
            writeLogInToTextFile(Category, LogString);
        } else if (logFormats.equalsIgnoreCase("") && defaultLog) {
            writeLogInToTextFile(Category, LogString);
        }

        String propertiesValue = "<table border=1 width=100%><tr><td>" + getCustomDate() + "</td><td>" + getCustomTime() + "</td></tr></table>";
        WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "ReportLogInfo", propertiesValue);


    }


    public static void createTextLogFile(String logFileName) throws IOException {
        File myObj = new File(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            PrintWriter out = new PrintWriter(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".txt"); // Step 2
            out.println("Date" + "\t\t\t" + "Time" + "\t\t\t" + "Category" + "\t\t" + "Description");
            out.close();
        } else {
            System.out.println("File already exists.");
        }
    }


    public static void createTempReportTextFile() throws IOException {
        File myObj = new File(System.getProperty("user.dir") + "\\" + "createTempReportTextFile.txt");
        if (myObj.createNewFile()) {
            //System.out.println("File created: " + myObj.getName());
            PrintWriter out = new PrintWriter(System.getProperty("user.dir") + "\\" + "createTempReportTextFile.txt"); // Step 2
            out.println("");
            out.close();
        } else {
            //System.out.println("File already exists.");
        }
    }

    public static void createExcelLogFile(String logFileName) throws IOException {
        try {
            File f = new File(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".xlsx");
            if (f.exists() && !f.isDirectory()) {
                System.out.println("Excel file already exists");
            } else {
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook();
                //Need to identify proper newly created excel sheet.
                XSSFSheet sheet = workbook.createSheet("Logs");
                Row row = sheet.createRow(0);
                String arr[] = {"Date", "Time", "Category", "Description"};
                HashMap<String, String> logData = new HashMap();
                sheet.setZoom(100);
                for (int cell = 0; cell < arr.length; cell++) {
                    Cell cell0 = row.createCell(cell);
                    cell0.setCellValue(arr[cell]);
                    if (cell == 3) {
                        sheet.setColumnWidth(cell, 35000);
                    } else {
                        sheet.setColumnWidth(cell, 3500);
                    }
                }
                workbook.write(fos);
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void createLogFile() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        Process process;
        if (isWindows) {
            process = Runtime.getRuntime().exec(String.format("taskkill /f /im excel.exe"));
            process = Runtime.getRuntime().exec(String.format("taskkill /f /im notepad.exe"));
        }

        String logFileName = "Logs_" + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("customDataAndTimeStampForFileNames");
        if (ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogDataAppendedToExistingFileorNot").equalsIgnoreCase("Yes")) {
            System.out.println("Data is appended to existing log file");
        } else {
            WriteDataIntoPropertiesFile(globalPropertiesFileNameAndPath, "LogFileName", logFileName);
        }


        String arrlogFormats[] = new String[]{};
        String logFormats = ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileFormat");
        if (logFormats.contains(",")) {
            arrlogFormats = logFormats.split(",");
            for (int logFormatInit = 0; logFormatInit < arrlogFormats.length; logFormatInit++) {
                if (arrlogFormats[logFormatInit].contains("Excel")) {
                    createExcelLogFile(logFileName);
                }
                if (arrlogFormats[logFormatInit].contains("TextFile")) {
                    createTextLogFile(logFileName);
                }
            }
        } else if (logFormats.equalsIgnoreCase("Excel")) {
            createExcelLogFile(logFileName);
        } else if (logFormats.equalsIgnoreCase("TextFile")) {
            createTextLogFile(logFileName);
        }
    }

    public static void writeLogFileHeader(String logFileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(System.getProperty("user.dir") + "\\src\\main\\java\\Logs\\" + logFileName + ".txt"); // Step 2
        out.println("Date" + "\t\t\t" + "Time" + "\t\t\t" + "Category" + "\t\t" + "Description");
        out.close();
    }

    public static void writeLogInToTextFile(String Category, String LogString) throws IOException {
        /*Categories - Info, Warning, Error*/
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".txt", true);
        PrintWriter out = new PrintWriter(fw);
        String customDate = getCustomDate();
        String customTime = getCustomTime();
        out.println(customDate + "\t\t" + customTime + "\t\t" + Category + "\t\t\t" + LogString);
        out.close();


        replaceTextInHTML("Scripts_Execution_Logs", " <TR><TD style=\"text-align: center\">" + customDate + "</TD><TD style=\"text-align: center\">" + customTime + "</TD><TD style=\"text-align: center\">" + Category + "</TD><TD>&nbsp;" + LogString + "</TD></TR>" + "Scripts_Execution_Logs");

    }

    public static void writeLogInToExcelFile(String Category, String LogString) throws IOException {
        // ZipSecureFile.setMinInflateRatio(0);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet xs = wb.getSheet("Logs");
        FileOutputStream fos = null;
        XSSFCell columnNumber = null;
        Row row = xs.createRow(xs.getLastRowNum() + 1);
        String arrLogCellValues[] = {getCustomDate(), getCustomTime(), Category, LogString};
        for (int writeLogCellValues = 0; writeLogCellValues <= 3; writeLogCellValues++) {
            Cell cellDate = row.createCell(writeLogCellValues);
            cellDate.setCellValue(arrLogCellValues[writeLogCellValues]);
        }
        fos = new FileOutputStream(System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("LogFileName") + ".xlsx");
        wb.write(fos);
        wb.close();
        fos.close();

    }

    public static void HTMLExecutionSummary(String text,int passCount,int failCount,int notExecutedCount,int totalMethods) throws IOException {
        String globalPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\global.properties";
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        String sheetName=ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetName");
        FileInputStream fis = new FileInputStream(dataSheetPathAndName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowNumber = sheet.getLastRowNum();
        String strPropFileValue = null;

        // 14-July-2023 ---- Need to get the values from loop and updated the below values and print the proper values in console
        int passedCount = passCount;
        int failedCount = failCount;
        int notexecutedCount = notExecutedCount;
        int totalExecutedCount = passCount+failCount;
        // 14-July-2023

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row1 = sheet.getRow(i);
            for (int j = 0; j <= row1.getLastCellNum(); j++) {
                strPropFileValue = ReadDataFromPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath).getProperty("HTMLReportExecutionMethodsInfo");
                Cell celll = row1.getCell(j);
                //if any column dnt required in HTML Report, please enable the below if condition with column number
                // if (j != 4 && j != 5 && j != 6) {
                if (i == 0) {
                    WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", strPropFileValue + "<td style=\"text-align: center\"><b>" + String.valueOf(celll) + "<b></td>");
                } else if (j == 0 && i > 0) {
                    WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", strPropFileValue + "<td style=\"text-align: Left\">" + String.valueOf(celll) + "</td>");
                } else {
                    WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", strPropFileValue + "<td style=\"text-align: center\">" + String.valueOf(celll) + "</td>");
                }
                // }

            }
            WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", strPropFileValue + "</TR>");
        }
        WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", strPropFileValue + "</TABLE>");

        replaceTextInHTML("Current_Execution_Methods_List", ReadDataFromPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath).getProperty("HTMLReportExecutionMethodsInfo"));

        replaceTextInHTMLWithpiechart("PieChart vinay",text);
        WriteDataIntoPropertiesFile(HTMLLogInfoPropertiesFileNameAndPath, "HTMLReportExecutionMethodsInfo", " <Table border=1 width=100% style=font-size:14px;><Tr>");

        //Adding Images to HTML Report
        replaceTextInHTML("Images_Base64_Code_For_Current_Execution", "");

        //Scripts_Execution_Logs
        replaceTextInHTML("Scripts_Execution_Logs", "");

    }
//    public static void scrollIntoView(WebElement element) throws Exception {
//        element.isDisplayed();
//        execJavascript("arguments[0].scrollIntoView(true);", element);
//        log().info(element + " exists on " + getCurrentPageName());
//    }

}


/*














*/