import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Framework_Methods.Generic_Methods.*;
import static Methods.NABERS_Common_Methods.BrowserSetUp.browserLaunch;
import static Methods.NABERS_Common_Methods.BrowserSetUp.loginLink;
import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.RatingNumber;
import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.hashMapReturn;

public class NABERS_Main {
    static int passCount=0,failCount=0,totalCount=0,executedCount=0,notExecutedCount=0;
    static String text="";

    public static void main(String[] args) throws IOException, InvalidFormatException {
       WebDriver driver;

        try
        {
            String globalPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\global.properties";
            String HTMLLogInfoPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\HTML_Log_Info.properties";

            createCustomDataAndTimeStampForFileNames(customDateFormat());


            String destFile = System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\Report_" + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("customDataAndTimeStampForFileNames") + ".html";
            String sourceFile = System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\HTML_Report_Template.html";
            copyFileUsingApacheCommonsIO(new File(sourceFile), new File(destFile));


            createReportFile();
            createLogFile();
            writeLog("Info", "NABERS Automation Script has been Started");

            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            HashMap<String,String> loginDetails =loginLink("SIT","L2",dataSheetPathAndName,globalPropertiesFileNameAndPath);

            String arr[] = readExecutionMethodsFromExcel(ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath"), ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName"), ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetName"));
            for (String vin:arr) {
                System.out.println(vin);

            }
            if (arr.length == 0) {
                writeLog("Info", "Headers not found in Execution Data File");
            } else if (arr.length == 1) {
                writeLog("Info", "Scenario sheet Methods not found");
            }
            else {
                writeLog("Info", "There are "+(arr.length-1)+" Scenario present");
                FileInputStream fis = new FileInputStream(dataSheetPathAndName);
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet xs = wb.getSheet(ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetName"));

                for (int methodsCount = 1; methodsCount < arr.length; methodsCount++) {
                    passCount = 0;
                    failCount = 0;
                    totalCount = 0;
                    notExecutedCount = 0;
                    executedCount = 0;
                    Row row = xs.getRow(methodsCount);
                    Row rowKey = xs.getRow(0);
                    HashMap<String, String> executionPerameters = new HashMap<>();

                    for (int cell = 0; cell < rowKey.getLastCellNum(); cell++) {
                        Cell cellvalue = row.getCell(cell);
                        String value_Main_Page = cellvalue.toString();

                        Cell keyValue = rowKey.getCell(cell);
                        String key_Main_Page = keyValue.toString();

                        executionPerameters.put(key_Main_Page, value_Main_Page);
                    }
                    writeLog("Info", executionPerameters.get("Ratings") + " Scenario sheet start executing");
//                    System.out.println(executionPerameters);
                    writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(), 0, "Executed_By"), methodsCount, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"), "Vinay");
                    writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(), 0, "Start_Date"), methodsCount, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"), getCustomDate());
                    writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(), 0, "Start_Time"), methodsCount, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"), getCustomTime());

                    String ratingSheetName = null;
                    try {
                        ratingSheetName = executionPerameters.get("Ratings") + executionPerameters.get("Rating_Scope");
//                        System.out.println(ratingSheetName);


                        boolean sheetAvalStatus = isSheetAvailableInExcel(dataSheetPathAndName, ratingSheetName);
                        if (sheetAvalStatus) {
                            writeLog("Info", "Scenario sheet is available in test data workbook and start counting the sheet Methods");

                        XSSFSheet sheet = wb.getSheet(ratingSheetName);
                        HashMap<Integer, String> hmMethodsAndRowNumber = new HashMap();
                        for (int v = 0; v <= sheet.getLastRowNum(); v++) {
                            Row sheetRow = sheet.getRow(v);
                            try {
                                Cell sheetCell = sheetRow.getCell(0);
                                String cellValue = sheetCell.toString();
                                if (!cellValue.equalsIgnoreCase("")) {
                                    hmMethodsAndRowNumber.put(v, cellValue);
                                }
                            } catch (Exception e) {
                            }
                        }
                            totalCount = hmMethodsAndRowNumber.size();
//                            System.out.println(hmMethodsAndRowNumber);
                            driver = browserLaunch(executionPerameters.get("Browser"), executionPerameters.get("Environment"));

                            for (int values = 0; values <= sheet.getLastRowNum(); values++) {

                                if (hmMethodsAndRowNumber.containsKey(values)) {

                                    String methodNames = hmMethodsAndRowNumber.get(values);
                                    String className = null;
                                    if (hmMethodsAndRowNumber.get(values).contains("_")) {
                                        String arrMethod[] = hmMethodsAndRowNumber.get(values).split("_", 2);
                                        className = "Methods." + "NABERS_" + arrMethod[0] + ".NABERS_" + arrMethod[0];
                                        methodNames = arrMethod[1];
                                    } else {
                                        className = "Methods.NABERS_Common_Methods.NABERS_Common_Methods";
                                        methodNames = hmMethodsAndRowNumber.get(values);
//
                                    }
                                    writeLog("Info", methodNames + " Method of " + executionPerameters.get("Ratings") + " sheet start running");

                                    Class<?> c = Class.forName(className);
                                    Class[] argTypes = new Class[]{int.class, WebDriver.class, String.class, HashMap.class};
                                    Method main = c.getDeclaredMethod(methodNames, argTypes);
                                    boolean strMethodsReturnStatus = false;
                                    strMethodsReturnStatus = (boolean) main.invoke(methodNames, values, driver, ratingSheetName, executionPerameters);
                                    executedCount++;
                                    if (strMethodsReturnStatus) {
                                        passCount++;
                                        writeLog("Info", methodNames + " Method of " + executionPerameters.get("Ratings") + " sheet Passed");
                                        writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ratingSheetName, values, "Status"), values + 1, ratingSheetName, "PASS");
                                    } else {
                                        failCount++;
                                        writeLog("Error", methodNames + " Method of " + executionPerameters.get("Ratings") + " sheet Faild");
                                        writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ratingSheetName, values, "Status"), values + 1, ratingSheetName, "FAIL");
                                        writeLog("Error", "Script terminates here and goes to next scenario.");

                                    }
                                }
                            }

                        } else {
                            failCount++;
                            writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, "NABERS", 0, "Status"), methodsCount, "NABERS", "Not Available");
                            writeLog("Error", "Scenario sheet is not available test data workbook");
                            continue;
                        }
                        text = stringWhichHasToReplaceForPieChart(text, passCount, failCount, notExecutedCount, totalCount, methodsCount,ratingSheetName );

                    } catch (Exception e) {
                        System.out.println(e);
                        writeLog("Error", String.valueOf(e));
                        String numberOfRating = RatingNumber();
                        writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(), 0, "RatingNumber"), methodsCount, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"), numberOfRating);
                        text = stringWhichHasToReplaceForPieChart(text, passCount, failCount, notExecutedCount, totalCount, methodsCount, ratingSheetName);

                        continue;
                    }
                    executionPerameters.clear();

                    try {
                        String numberOfRating = "";
                        numberOfRating = RatingNumber();
                        writeDataIntoExcelCell(findColumnPositionInExcel(dataSheetPathAndName, ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName").toString(), 0, "RatingNumber"), methodsCount, (String) ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).get("ExcelDataSheetName"), numberOfRating);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, ratingSheetName, 0, (executionPerameters.get("Ratings")+"_RatingDetails"));
                    HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, ratingSheetName);
                    if(RatingDetails.get("RatingDetails").contains("Energy"))
                    {
                        totalCount=totalCount-4;
                        passCount=passCount-4;
                    }if(RatingDetails.get("RatingDetails").contains("Water"))
                    {
                        totalCount=totalCount-1;
                        failCount=passCount-1;
                    }

                }
                HTMLExecutionSummary(text,passCount,failCount,notExecutedCount, arr.length-1);

            }
        }


        catch (Exception e) {
            System.out.println(e);
            writeLog("Error", String.valueOf(e));
        }


    }
}
