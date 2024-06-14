package Methods.NABERS_Common_Methods;

import Framework_Methods.Generic_Methods;
import Page_Elements.Common_WeElement;
import Page_Elements.LogIn_WebElements;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;

import static Framework_Methods.Generic_Methods.*;
//import static Framework_Methods.Generic_Methods.clickAndEnterValueInTextBox;
import static Methods.NABERS_Common_Methods.BrowserSetUp.driver;
import static Methods.NABERS_Common_Methods.BrowserSetUp.loginLink;

public class NABERS_Common_Methods {
    static String ratingNumberOfTheBuilding="";
    public static boolean Login(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {


        boolean strMethodsStatus = false;
        Generic_Methods obj_GenericMethods = new Generic_Methods();
        LogIn_WebElements lo = new LogIn_WebElements(driver);
        try {
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");

            HashMap<String, String> loginDetails = loginLink("UAT", "assessor", dataSheetPathAndName, globalPropertiesFileNameAndPath);

            enterValueInTextBox(lo.get_element_UserName_TextBox(), loginDetails.get("AccountID"));
            enterValueInTextBox(lo.get_element_Password_TextBox(), loginDetails.get("Password"));
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            Thread.sleep(3000);
            clickOnButton(lo.get_element_LogIn_Button());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.titleContains("Home"));

            strMethodsStatus = true;
//        Login_WebElements lo = new Login_WebElements(driver);
            Thread.sleep(2000);
            obj_GenericMethods.stableZoomSizeBrowserWindow();
            Thread.sleep(2000);
            obj_GenericMethods.zoomOutBrowserWindowWithRobotClass(3);
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

        } catch (Exception e) {
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);
            strMethodsStatus = false;
        }
        return strMethodsStatus;
    }

    public static boolean SelectRatingType(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");

        LogIn_WebElements lo = new LogIn_WebElements(driver);
        System.out.println(executionSheetName);
        try {
            Thread.sleep(7000);
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            clickOnButton(lo.get_element_Create_a_new_rating());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.titleContains("CreateNewRating"));
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);


//            System.out.println("mera bhai");
            Thread.sleep(8000);
            clickOnButton(lo.get_element_Select_building_type());
//            System.out.println("mera bhai 22");
            Thread.sleep(1000);

            //These is for Select rating so don't touch
            String value_For_Rating_Type = "//span[text()='" + hmMethodDataFill.get("SelectRatingType") + "']";
            System.out.println(value_For_Rating_Type);
            driver.findElement(By.xpath(value_For_Rating_Type)).click();

            Thread.sleep(5000);
            if (isColumnAvaiInExcel(dataSheetPathAndName, executionSheetName, rowNumber, "SelectRatingScope")) {
                radioButtonClick(lo.get_element_select_Scope(), hmMethodDataFill.get("SelectRatingScope"));
                Thread.sleep(3000);
            }
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);
            clickOnButton(driver.findElement(By.xpath("//button[text()='Agree and continue']")));

            strMethodsStatus = true;
        } catch (Exception e) {
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);
            strMethodsStatus = false;
        }
        return strMethodsStatus;
    }

    public static boolean RatingPeriod(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            clickOnButton(comm.getelement_ClickOnRatingPeriod());
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/label[text()='Starting date']")));
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inputDate = inputFormat.parse(hmMethodDataFill.get("Starting date"));
            String value = outputFormat.format(inputDate);
            enterValueInTextBox(comm.get_element_StartingDate(), value);
            Thread.sleep(2000);
            ratingNumberOfTheBuilding =driver.findElement(By.xpath("//p[@class='slds-show_inline'][2]")).getText();
            System.out.println(ratingNumberOfTheBuilding.substring(8));
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            Thread.sleep(2000);
            clickOnButton(comm.getelement_SaveButton());
            strMethodsStatus = true;
        } catch (Exception e) {
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            System.out.println(e);
            strMethodsStatus = false;
        }

        System.out.println("RatingPeriod");
        return strMethodsStatus;
    }

    public static String RatingNumber(){
        String vin="";
       if(!ratingNumberOfTheBuilding.equalsIgnoreCase(null))
       {
           vin= ratingNumberOfTheBuilding.substring(8);
       }
    return vin;
    }

    public static boolean Premises(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            clickOnButton(comm.getelement_ClickOnPremises());

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Premises']")));
            Thread.sleep(3000);
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            clickOnButton(comm.get_element_Search_rating_number());
//            System.out.println("after click to search rating number");
            Thread.sleep(3000);
            enterValueInTextBox(comm.get_element_Type_rating_number(), hmMethodDataFill.get("Search for street name and number"));
            Thread.sleep(3000);
            clickOnButton(comm.get_element_Rating_dropdown());
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            windowScroll(driver, comm.getelement_SaveButton());

            Thread.sleep(3000);
            clickOnButton(comm.getelement_SaveButton());
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);


            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            strMethodsStatus = false;
        }
        System.out.println("Premises");
        return strMethodsStatus;
    }

    public static boolean Contact(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            clickOnButton(comm.getelement_ClickOnContact());

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Customer']")));
            Thread.sleep(2000);
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            enterValueInTextBox(comm.getelement_EnterEmail(), hmMethodDataFill.get("Email address"));
            enterValueInTextBox(comm.get_element_Customer_organisation(), hmMethodDataFill.get("Search company"));
            wait.until(ExpectedConditions.visibilityOf(comm.get_element_Select_Customer_organisation()));
            clickOnButton(comm.get_element_Select_Customer_organisation());
            Thread.sleep(2000);
            dropDownOptionSelection(driver, comm.get_element_Select_role(), comm.get_element_Select_theRoll(), hmMethodDataFill.get("Role"));
            clickOnButton(comm.get_element_Approve_Radio_Button());
            clickOnButton(comm.get_element_Send_invite());
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            windowScroll(driver, comm.getelement_SaveButton());

            Thread.sleep(3000);
            clickOnButton(comm.getelement_SaveButton());
            strMethodsStatus = true;
        } catch (Exception e) {
            TakeScreenshot(new String(new Throwable().getStackTrace()[0].getMethodName()),driver);

            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("Contact");
        return strMethodsStatus;
    }


    public static boolean GridElectricity(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
            if (RatingDetails.get("RatingDetails").contains("Energy")) {
                clickOnButton(comm.getelement_ClickOnGridElect());
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Grid electricity overview']")));
                Thread.sleep(3000);
                System.out.println(hmMethodDataFill);

                if (hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes")) {
                    HashMap<Integer, HashMap<String, String>> tableData = DataForTable(executionSheetName + " Table", driver, "Utility meter");
                    System.out.println(tableData);
                    Thread.sleep(2000);
                    for (int NumberOfUtility = 1; NumberOfUtility <= tableData.size(); NumberOfUtility++) {
                        System.out.println(NumberOfUtility + "  vinay");
                        Utility(NumberOfUtility, driver, tableData.get(NumberOfUtility), executionSheetName);

                        Thread.sleep(2000);
                    }
                }
                if (hmMethodDataFill.get("Non-utility meter inclusions").equalsIgnoreCase("yes")) {
                    HashMap<Integer, HashMap<String, String>> tableData = DataForTable(executionSheetName + " Table", driver, "Non-utility meter inclusions");
                    System.out.println(tableData);
                    Thread.sleep(2000);
                    NonUtilityInclusion(rowNumber, driver, tableData.get(1), executionSheetName);
                }

                if (hmMethodDataFill.get("Small end use inclusions").equalsIgnoreCase("yes")) {

                }
                if (hmMethodDataFill.get("Thermal: Standard methodology inclusions").equalsIgnoreCase("yes")) {

                }
                if (hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes")) {

                }


                Thread.sleep(3000);
                clickOnButton(driver.findElement(By.xpath("//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Grid electricity']/../lightning-button-icon")));
                Thread.sleep(2000);
            }else{

            }


            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("GridElectricity");
        return strMethodsStatus;
    }

    public static boolean GasAndLPG(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
            if (RatingDetails.get("RatingDetails").contains("Energy")) {
                clickOnButton(comm.getelement_ClickOnGasAndLPG());
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Gas and LPG overview']")));
                Thread.sleep(3000);

                System.out.println(hmMethodDataFill.get("Gas and LPGNeed"));
                radioButtonClick(driver.findElements(By.xpath("//input[@name='hasGasOptionsGroup']//following-sibling::label")), hmMethodDataFill.get("Gas and LPGNeed"));

                System.out.println(hmMethodDataFill);
                if (hmMethodDataFill.get("Gas and LPGNeed").equalsIgnoreCase("yes"))
//                {
//
//
//                    if(hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Non-utility meter inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//
//                    if(hmMethodDataFill.get("Small end use inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Thermal: Standard methodology inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//
//                    Thread.sleep(3000);
//                }
                {
                    System.out.println("print yes for LPG AND GAS");
                } else {
                    System.out.println("print No for LPG AND GAS");
                }
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("GasAndLPG");
        return strMethodsStatus;
    }

    public static boolean Diesel(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
            if (RatingDetails.get("RatingDetails").contains("Energy")) {
                clickOnButton(comm.getelement_ClickOnDiesel());
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Gas and LPG overview']")));
                Thread.sleep(3000);
                System.out.println(hmMethodDataFill);

                System.out.println(hmMethodDataFill.get("DieselNeed"));
                radioButtonClick(driver.findElements(By.xpath("//input[@name='hasDieselOptionsGroup']//following-sibling::label")), hmMethodDataFill.get("DieselNeed"));

                if (hmMethodDataFill.get("DieselNeed").equalsIgnoreCase("yes"))
//                {
//
//
//                    if(hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Non-utility meter inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//
//                    if(hmMethodDataFill.get("Small end use inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Thermal: Standard methodology inclusions").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//                    if(hmMethodDataFill.get("Utility meter").equalsIgnoreCase("yes"))
//                    {
//
//                    }
//
//                    Thread.sleep(3000);
//                }
                {
                    System.out.println("print yes for DDD");
                } else {
                    System.out.println("print No for DDD");
                }
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("DDDD");
        return strMethodsStatus;
    }

    public static boolean Water(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
            if (RatingDetails.get("RatingDetails").contains("Water")) {
                clickOnButton(comm.getelement_ClickOnWaternav());
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Water overview']")));
                Thread.sleep(3000);
                System.out.println(hmMethodDataFill);


                    if(hmMethodDataFill.get("Water Utility meter").equalsIgnoreCase("yes"))
                    {
                        HashMap<Integer, HashMap<String, String>> tableData = DataForTable(executionSheetName + " Table", driver, "Water Utility meter");
                        System.out.println(tableData);
                        Thread.sleep(2000);
                        for (int NumberOfUtility = 1; NumberOfUtility <= tableData.size(); NumberOfUtility++) {
                            System.out.println(NumberOfUtility + "  vinay");
                            WaterUtility(NumberOfUtility, driver, tableData.get(NumberOfUtility), executionSheetName);

                            Thread.sleep(2000);
                        }
                    }
                    if(hmMethodDataFill.get("Water Non-utility meter inclusions").equalsIgnoreCase("yes"))
                    {

                    }

                    if(hmMethodDataFill.get("Water Small end use inclusions").equalsIgnoreCase("yes"))
                    {

                    }
                    if(hmMethodDataFill.get("Water Thermal: Standard methodology inclusions").equalsIgnoreCase("yes"))
                    {

                    }
                Thread.sleep(3000);
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("DDDD");
        return strMethodsStatus;
    }

    public static void TakeScreenshot(String fileName, WebDriver driver) throws IOException, InterruptedException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_YYYY_hh_mm_ss_SSS");
        Date date = new Date();
        TakesScreenshot TakesScreenshot = null;
        TakesScreenshot ts = (TakesScreenshot)driver;
        File SourceFile = ts.getScreenshotAs(OutputType.FILE);
        String imageFileName = System.getProperty("user.dir") + "\\src\\main\\java\\Screenshots\\" + fileName + "_" + dateFormat.format(date) + ".jpg";

        // If screenshot images to be saved in Screenshots folder, enable the below three lines and comment the "File f = new File(SourceFile.toURI());"
        /*FileUtils.copyFile(SourceFile, new File(imageFileName));
        File f = new File(imageFileName);
        File f = new File(SourceFile);*/

        File f = new File(SourceFile.toURI());
        FileInputStream fin = new FileInputStream(f);
        byte imagebytearray[] = new byte[(int) f.length()];
        fin.read(imagebytearray);
        String imagetobase64 = "<Tr><td style=\"text-align: center; vertical-align: middle;\">" + "<img src=\"data:image/png;base64," + Base64.encodeBase64String(imagebytearray) + "\" width=\"900\" height=\"450\"/>" + "</td></Tr><Tr height = 10><td></td></Tr>" + "Images_Base64_Code_For_Current_Execution";
        fin.close();
        replaceTextInHTML("Images_Base64_Code_For_Current_Execution", imagetobase64);
    }


    public static boolean WaterUtility(int rowNumber, WebDriver driver, HashMap datavalues, String executionSheetName) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Generic_Methods genericMethods = new Generic_Methods();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.findElement(By.xpath("//button[text()='Add utility meter inclusion']")).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath("//label[text()='Description of coverage']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Description of coverage"));
            driver.findElement(By.xpath("//label[text()='Meter number']//following-sibling::div/input")).sendKeys((CharSequence) datavalues.get("Meter number"));
            driver.findElement(By.xpath("//label[text()='Water provider/supplier']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Water provider/supplier"));
            driver.findElement(By.xpath("//label[text()='Account number']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Account number"));
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,600)");
            genericMethods.zoomOutBrowserWindowWithRobotClass(2);
            DataFillingIntoTheWebTable(driver, executionSheetName, datavalues.get("Table").toString());
            js.executeScript("window.scrollBy(0,400)");
            radioButtonClick(driver.findElements(By.xpath("//input//following-sibling::label")), (String) datavalues.get("GreenPower"));
            genericMethods.zoomInBrowserWindowWithRobotClass(2);
            Thread.sleep(3000);

            clickOnButton(comm.getelement_ClickOnGridElect());
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,200)");
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }
        return strMethodsStatus;
    }

    public static boolean ReviewSummary(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Generic_Methods genericMethods = new Generic_Methods();
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            clickOnButton(comm.getelement_ClickOnReviewSummary());
            Thread.sleep(8000);

            clickOnButton(driver.findElement(By.xpath("//button[text()='Calculate rating']")));
            Thread.sleep(4000);

            strMethodsStatus =true;
        }
        catch (Exception e)
        {
            strMethodsStatus=false;

        }
        return strMethodsStatus;

    }


    public static boolean CertificationProcess(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);

        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Generic_Methods genericMethods = new Generic_Methods();
        try {
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            clickOnButton(comm.getelement_CertificationProcess());
            Thread.sleep(6000);
            System.out.println("Certicicate me ja rahaa");

            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(2000);
            clickOnButton(driver.findElement(By.xpath("//span[text()='I confirm that the invoicing details are correct and up to date']")));

            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(2000);
            clickOnButton(driver.findElement(By.xpath("//span[text()='The customer is aware of what star rating they’ve achieved']")));
            Thread.sleep(3000);
            UploadFile(comm.getelement_clickOn_Upload_File(), hmMethodDataFill.get("Upload Files"));
            Thread.sleep(3000);
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(2000);
            radioButtonClick(driver.findElements(By.xpath("//input[@name='compliantWithRules']//following-sibling::label")),hmMethodDataFill.get("Has this rating been carried out in full compliance with the NABERS rules?"));
            Thread.sleep(3000);
            radioButtonClick(driver.findElements(By.xpath("//input[@name='siteVisitDone']//following-sibling::label")),hmMethodDataFill.get("Did you carry out a site visit?"));
            Thread.sleep(3000);
            radioButtonClick(driver.findElements(By.xpath("//input[@name='conflictOfInterest']//following-sibling::label")),hmMethodDataFill.get("Do you have any conflict of interest in conducting this rating?"));
            Thread.sleep(3000);
            clickOnButton(driver.findElement(By.xpath("//span[text()='To the best of my knowledge, the information provided in this rating is accurate, including any changes made during the level 1 audit process, if applicable.']")));
            js.executeScript("window.scrollBy(0,200)");
            Thread.sleep(3000);
            clickOnButton(driver.findElement(By.xpath("//button[text()='Lodge rating']")));
            Thread.sleep(3000);
            js.executeScript("window.scrollTo(0,0)");
            Thread.sleep(4000);
            strMethodsStatus =true;
        }
        catch (Exception e)
        {
            strMethodsStatus=false;

        }
        return strMethodsStatus;

    }
    public static boolean RenewableElectricity(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        System.out.println("RenewableElectricity lo vastunda leda ani check chestunna");
        Common_WeElement hWE = new Common_WeElement(driver);
        Generic_Methods genericMethods = new Generic_Methods();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            clickOnButton(hWE.getelement_RenewableElectricity());
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(5000);
            System.out.println("Vinay");
            radioButtonClick(hWE.getDoes_the_premises_Have_OREG_system_YesOrNo(), hmMethodDataFill.get("Does the premises have an (OREG) system"));
            Thread.sleep(2000);
            UploadFile(hWE.getelement_clickOn_Upload_File(), hmMethodDataFill.get("Upload Files"));
            Thread.sleep(3000);
            clickAndEnterValueInTextBox(hWE.getelement_Total_capacity_OREG_System(), hmMethodDataFill.get("total capacity of the OREG system"));
            Thread.sleep(2000);
            String value = hmMethodDataFill.get("OREG system is connected to a Remote Meter Reading System (RMRS");
            switch (value) {
                case "Yes":
                    radioButtonClick(hWE.getelement_OREG_System_connected_To_Remote(), hmMethodDataFill.get("OREG system is connected to a Remote Meter Reading System (RMRS"));
                    Thread.sleep(2000);
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date inputDate = inputFormat.parse(hmMethodDataFill.get("Starting date"));
                    String value1 = outputFormat.format(inputDate);
                    enterValueInTextBox(hWE.getelement_Date_Of_Validation(), value1);
                    break;
                case "No":
                    radioButtonClick(hWE.getelement_OREG_System_connected_To_Remote(),  hmMethodDataFill.get("OREG system is connected to a Remote Meter Reading System (RMRS"));
                    break;
                case "Not applicable":
                    radioButtonClick(hWE.getelement_OREG_System_connected_To_Remote(), hmMethodDataFill.get("OREG system is connected to a Remote Meter Reading System (RMRS"));
                    break;
                default:
                    System.out.println(" no option selected");
                    break;
            }
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,400)");
Thread.sleep(2000);
            clickenterValueInTextBox(hWE.getelement_total_capacity_of_the_OREG_system(), hmMethodDataFill.get("total onsite renewable electricity consumption"));
            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(2000);
            clickenterValueInTextBox(hWE.getelement_LGCs_were_created_and_sold(), hmMethodDataFill.get("LGCs were created and sold"));
            Thread.sleep(2000);
            clickenterValueInTextBox(hWE.getelement_LGCs_were_voluntarily_surrendered(), hmMethodDataFill.get("LGCs were voluntarily surrendered"));
            Thread.sleep(2000);
            radioButtonClick(hWE.getelement_Has_the_onsite_renewable_consumption_YesOrNo(), hmMethodDataFill.get("Has the onsite renewable consumption"));
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(2000);
            clickenterValueInTextBox(hWE.getelement_total_amount_of_GreenPower_purchased(), hmMethodDataFill.get("total amount of GreenPower purchased"));
//            js.executeScript("window.scrollBy(0,200)");
            Thread.sleep(2000);
            clickenterValueInTextBox(hWE.getelement_offsite_Large_scale_Generation_Certificates(), hmMethodDataFill.get(" offsite Large-scale Generation Certificates"));
            js.executeScript("window.scrollBy(0,200)");
            Thread.sleep(2000);

            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println("error in RenewableElectricity" + e);
            strMethodsStatus = true;
        }
        System.out.println("RenewableElectricity");
        return strMethodsStatus;
    }
    public static boolean Utility(int rowNumber, WebDriver driver, HashMap datavalues, String executionSheetName) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Generic_Methods genericMethods = new Generic_Methods();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.findElement(By.xpath("//button[text()='Add utility meter inclusion']")).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath("//label[text()='Description of coverage']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Description of coverage"));
            driver.findElement(By.xpath("//label[text()='NMI']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("NMI"));
            driver.findElement(By.xpath("//label[text()='Electricity provider/supplier']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Electricity provider/supplier"));
            driver.findElement(By.xpath("//label[text()='Account number']/../lightning-input//input")).sendKeys((CharSequence) datavalues.get("Account number"));
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,600)");
            genericMethods.zoomOutBrowserWindowWithRobotClass(2);
            DataFillingIntoTheWebTable(driver, executionSheetName, datavalues.get("Table").toString());
            js.executeScript("window.scrollBy(0,400)");
            radioButtonClick(driver.findElements(By.xpath("//input//following-sibling::label")), (String) datavalues.get("GreenPower"));
            genericMethods.zoomInBrowserWindowWithRobotClass(2);
            Thread.sleep(3000);

            clickOnButton(comm.getelement_ClickOnGridElect());
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,200)");
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }
        return strMethodsStatus;
    }
    public static boolean NonUtilityInclusion(int rowNumber, WebDriver driver, HashMap datavalues, String executionSheetName) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Common_WeElement comm = new Common_WeElement(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Generic_Methods genericMethods = new Generic_Methods();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-300)");
            driver.findElement(By.xpath("//button[text()='Add non-utility meter inclusion']")).click();
            Thread.sleep(4000);
            js.executeScript("window.scrollBy(0,400)");
            genericMethods.zoomOutBrowserWindowWithRobotClass(2);
            DataFillingIntoTheWebTable(driver, executionSheetName, datavalues.get("Table").toString());
            js.executeScript("window.scrollBy(0,400)");
            genericMethods.zoomInBrowserWindowWithRobotClass(2);
            Thread.sleep(3000);
            clickOnButton(comm.getelement_ClickOnGridElect());
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,200)");
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }
        return strMethodsStatus;
    }


    public static boolean Logout(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        System.out.println("Logout");
        String ratingNumber =driver.findElement(By.xpath("//div/div/div[3]/p[3]")).getText();
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        int colInt=findColumnPositionInExcel(dataSheetPathAndName,executionSheetName.toString(),rowNumber,"ratingNumber");
        writeDataIntoExcelCell(colInt,rowNumber+1,executionSheetName,ratingNumber);
        strMethodsStatus=true;
        return strMethodsStatus;
    }

    public static void TakeScreenshot1(String fileName, WebDriver driver) throws IOException, InterruptedException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_YYYY_hh_mm_ss_SSS");
        Date date = new Date();
        TakesScreenshot TakesScreenshot = null;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File SourceFile = ts.getScreenshotAs(OutputType.FILE);
        String imageFileName = System.getProperty("user.dir") + "\\src\\main\\java\\Screenshots\\" + fileName + "_" + dateFormat.format(date) + ".jpg";

        // If screenshot images to be saved in Screenshots folder, enable the below three lines and comment the "File f = new File(SourceFile.toURI());"
//        FileUtils.copyFile(SourceFile, new File(imageFileName));
//        File f = new File(imageFileName);
//        File f = new File(SourceFile);

        File f = new File(SourceFile.toURI());
        FileInputStream fin = new FileInputStream(f);
        byte imagebytearray[] = new byte[(int) f.length()];
        fin.read(imagebytearray);
        String imagetobase64 = "<Tr><td style=\"text-align: center; vertical-align: middle;\">" + "<img src=\"data:image/png;base64," + Base64.encodeBase64String(imagebytearray) + "\" width=\"900\" height=\"450\"/>" + "</td></Tr><Tr height = 10><td></td></Tr>" + "Images_Base64_Code_For_Current_Execution";
        fin.close();
    }

    public static void dropDownOptionSelection(WebDriver driver, WebElement dropDown, List<WebElement> elements, String value) throws InterruptedException {
//    <WebElement> elements=driver.findElements(By.xpath("//input[@name='$PpyWorkPage$pApplicant$pApplOnBehalfOfCompany']//following-sibling::label"));
        dropDown.click();
//        System.out.println("sharanya");
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
//        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        Thread.sleep(1000);
//        System.out.println("vinay 123123123123123");
        for (WebElement element : elements) {
//            System.out.println(element.getText());
            if (element.getText().equalsIgnoreCase(value)) {
//                System.out.println("elements print ho rahedexxxwd344444445cvxewzs2a");
//                System.out.println(element.getText());
                Thread.sleep(2000);
                element.click();
                break;
            }
        }

    }

    public static void radioButtonClick(List<WebElement> elements, String value) throws InterruptedException {
// <WebElement> elements=driver.findElements(By.xpath("//input[@name='$PpyWorkPage$pApplicant$pApplOnBehalfOfCompany']//following-sibling::label"));
        for (WebElement element : elements) {
//            System.out.println(element.getText());
            if (element.getText().equalsIgnoreCase(value)) {
//                System.out.println("elements print ho rahedexxxwd344444445cvxewzs2a");
//                System.out.println(element.getText());
                Thread.sleep(2000);
                element.click();
                break;
            }
        }
    }


    public static void enterValueInTextBox(WebElement element, String inputText) throws InterruptedException {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(inputText);
        }
    }

    public static void clickAndEnterValueInTextBox(WebElement element, String inputText) throws InterruptedException, AWTException {
        if (element.isDisplayed()) {
            element.click();
//
//            element.clear();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Actions action = new Actions(driver);
            action.sendKeys(element, inputText).perform();

        }
    }

    public static void clickOnButton(WebElement element) {
        element.click();
    }

    public static HashMap hashMapReturn(int rownumber, String executionSheetName) throws IOException {
//        System.out.println(executionSheetName);
        String globalPropertiesFileNameAndPath = System.getProperty("user.dir") + "\\src\\main\\java\\GlobalProperties\\global.properties";
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        FileInputStream fis = new FileInputStream(dataSheetPathAndName);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(executionSheetName);
//        System.out.println(executionSheetName);
        Row rowKey = sheet.getRow(rownumber);
        Row rowValue = sheet.getRow(rownumber + 1);
        HashMap<String, String> hmMethodDataFill = new HashMap();
        for (int cellKeyNum = 1; cellKeyNum < rowKey.getLastCellNum(); cellKeyNum++) {
            try {
                Cell cellKey = rowKey.getCell(cellKeyNum);
                String key = cellKey.toString();
                Cell cellValue = rowValue.getCell(cellKeyNum);
                String value = cellValue.toString();


                hmMethodDataFill.put(key, value);
//                System.out.println(value);
            } catch (Exception e) {
                System.out.println("vinay " + e);
            }
        }
//        System.out.println(hmMethodDataFill);

        return hmMethodDataFill;
    }

    public static void DataFillingIntoTheWebTable(WebDriver driver, String executionSheetName, String TableName) throws IOException, InvalidFormatException, InterruptedException, AWTException {

        Generic_Methods genericMethods = new Generic_Methods();

        HashMap<Integer, HashMap<String, String>> tableData = DataForTable(executionSheetName + " Table", driver, TableName);

        System.out.println(tableData);
        Thread.sleep(2000);


        for (int rowOfTable = 1; rowOfTable <= tableData.size(); rowOfTable++) {
            driver.findElement(By.xpath("//small[text()='Add rows']")).click();
            for (int i = 1; i < 20; i++) {
                try {
                    String vinayXp = "//div[2]/div/div/div/table/thead/tr/th[" + i + "]";
                    WebElement element = driver.findElement(By.xpath(vinayXp));

                    int cell = i - 1;
                    String v = element.getText();
                    if (!v.equalsIgnoreCase("")) {
                        int row = 1;
                        String rowinWeb = "//div[1]/div/div/div/table/tbody/tr[" + rowOfTable + "]/th";
                        System.out.println(driver.findElement(By.xpath(rowinWeb)).getText());

                        Thread.sleep(3000);

                        for (Map.Entry<String, String> cellOfTable : tableData.get(rowOfTable).entrySet()) {
                            String cellval = "//div[1]/div/div/div/table/thead/tr/th[" + i + "]/../../../tbody/tr[" + rowOfTable + "]/td[" + cell + "]";

                            WebElement cellinweb = driver.findElement(By.xpath(cellval));
                            String tableKeyValue = cellOfTable.getKey();
                            if (element.getText().contains(tableKeyValue)
                            ) {
                                System.out.println(cellval);
                                Thread.sleep(2000);
                                clickAndEnterValueInTextBox(cellinweb, cellOfTable.getValue());
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                                System.out.println(rowOfTable + "  " + element.getText());
                                break;
                            } else if (element.getText().contains(tableKeyValue.substring(0, tableKeyValue.length() - 2))) {
                                if(cellinweb.getText().isEmpty())
                                {
                                    Thread.sleep(2000);
                                    clickAndEnterValueInTextBox(cellinweb, cellOfTable.getValue());
                                    Robot robot = new Robot();
                                    robot.keyPress(KeyEvent.VK_ENTER);
                                    robot.keyRelease(KeyEvent.VK_ENTER);
                                    System.out.println(rowOfTable + "  " + element.getText());
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static void DataFillingIntoTheWebTable2(WebDriver driver, String executionSheetName, String TableName,int TableNumber) throws IOException, InvalidFormatException, InterruptedException, AWTException {

        Generic_Methods genericMethods = new Generic_Methods();

        HashMap<Integer, HashMap<String, String>> tableData = DataForTable(executionSheetName + " Table", driver, TableName);

        System.out.println(tableData);
        Thread.sleep(2000);


        for (int rowOfTable = 1; rowOfTable <= tableData.size(); rowOfTable++) {
            driver.findElement(By.xpath("//small[text()='Add rows']")).click();
            for (int i = 1; i < 20; i++) {
                try {
                    String vinayXp = "(//div[2]/div/div/div/table/thead/tr/th[" + i + "])["+TableNumber+"]";
                    WebElement element = driver.findElement(By.xpath(vinayXp));

                    int cell = i - 1;
                    String v = element.getText();
                    if (!v.equalsIgnoreCase("")) {
                        int row = 1;
                        String rowinWeb = "(//div[1]/div/div/div/table/tbody/tr[" + row + "])["+TableNumber+"]";
                        System.out.println(driver.findElement(By.xpath(rowinWeb)).getText());

                        Thread.sleep(3000);

                        for (Map.Entry<String, String> cellOfTable : tableData.get(rowOfTable).entrySet()) {
                            String cellval = "(//div[1]/div/div/div/table/thead/tr/th[" + i + "]/../../../tbody/tr[" + rowOfTable + "]/td[" + cell + "])["+TableNumber+"]";
                            WebElement cellinweb = driver.findElement(By.xpath(cellval));
                            String tableKeyValue = cellOfTable.getKey();
                            if (element.getText().contains(tableKeyValue)) {
                                Thread.sleep(2000);
                                clickAndEnterValueInTextBox(cellinweb, cellOfTable.getValue());
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                                System.out.println(rowOfTable + "  " + element.getText());
                                break;
                            } else if (element.getText().contains(tableKeyValue.substring(0, tableKeyValue.length() - 2))) {
                                if(cellinweb.getText().isEmpty())
                                {
                                    Thread.sleep(2000);
                                    clickAndEnterValueInTextBox(cellinweb, cellOfTable.getValue());
                                    Robot robot = new Robot();
                                    robot.keyPress(KeyEvent.VK_ENTER);
                                    robot.keyRelease(KeyEvent.VK_ENTER);
                                    System.out.println(rowOfTable + "  " + element.getText());
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
    }


//    public static void DataFillingIntoTheWebTable2(WebDriver driver,String executionSheetName,String TableName,int TableNumber) throws IOException, InvalidFormatException, InterruptedException {
//        System.out.println("Entering data table2");
//        HashMap<Integer, HashMap<String, String>> tableData1 = DataForTable(executionSheetName + " Table", driver, TableName);
//        System.out.println(tableData1);
//        Thread.sleep(2000);
//        for (int rowOfTable = 1; rowOfTable <= tableData1.size(); rowOfTable++) {
//            for (int i = 1; i < 20; i++) {
//                try {
//                    String vinayXp = "(//div[2]/div/div/div/table/thead/tr/th[" + i + "])["+TableNumber+"]";
//                    WebElement element = driver.findElement(By.xpath(vinayXp));
//
//                    int cell = i - 1;
//                    String v = element.getText();
//                    if (!v.equalsIgnoreCase("")) {
//                        int row = 1;
//                        String rowinWeb = "(//div[1]/div/div/div/table/tbody/tr[" + row + "])["+TableNumber+"]";
//                        Thread.sleep(3000);
//
//                        for (Map.Entry<String, String> cellOfTable : tableData1.get(rowOfTable).entrySet()) {
//                            String cellval = "(//div[1]/div/div/div/table/thead/tr/th[" + i + "]/../../../tbody/tr[" + rowOfTable + "]/td[" + cell + "])["+TableNumber+"]";
//                            WebElement cellinweb = driver.findElement(By.xpath(cellval));
//                            if (element.getText().contains(cellOfTable.getKey())) {
//                                Thread.sleep(1000);
//                                clickAndEnterValueInTextBox(cellinweb, cellOfTable.getValue());
//                                Robot robot = new Robot();
//                                robot.keyPress(KeyEvent.VK_ENTER);
//                                robot.keyRelease(KeyEvent.VK_ENTER);
//                                System.out.println(rowOfTable + " " + element.getText());
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//
//    }


    public void checkBoxClick(List<WebElement> elements, String value) throws InterruptedException {
        //    <WebElement> elements=driver.findElements(By.xpath("//input[@name='$PpyWorkPage$pApplicant$pApplOnBehalfOfCompany']//following-sibling::label"));

        if (value.contains(",")) {
            String a[] = value.split(",");
            for (String values : a) {
                values = values.trim();
                for (WebElement element : elements) {
                    Thread.sleep(1000);
                    if (element.getText().equalsIgnoreCase(values)) {
                        if (!element.isSelected()) {
                            element.click();
                        }
                        break;
                    }
                }
            }
        } else {
            for (WebElement element : elements) {
                if (element.getText().equalsIgnoreCase(value)) {
                    if (!element.isSelected()) {
//                        System.out.println(element.getText());
                        element.click();
                    }
                    break;
                }
            }
        }
    }


    public static void windowScroll(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        while (!element.isDisplayed()) {
            js.executeScript("window.scrollBy(0,200)");
        }

    }


    public static HashMap<Integer, HashMap<String, String>> DataForTable(String ratingSheetName, WebDriver driver, String tableName) throws IOException, InvalidFormatException {
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        FileInputStream fis = new FileInputStream(dataSheetPathAndName);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(ratingSheetName);


        /*code start for table name reding*/
        HashMap<Integer, String> hmTableNameRead = new HashMap();
        for (int v = 0; v <= sheet.getLastRowNum(); v++) {
            Row sheetRow = sheet.getRow(v);
            try {
                Cell sheetCell = sheetRow.getCell(0);

                if (!sheetCell.toString().equalsIgnoreCase("")) {
                    hmTableNameRead.put(v, sheetCell.toString());
                }
            } catch (Exception e) {
            }
        }
        /*code end for table name reading*/


        int rowNumberIs = findRowPositionInExcel(dataSheetPathAndName, ratingSheetName, 0, tableName);

        HashMap<Integer, HashMap<String, String>> hashMapData = new HashMap();
        Integer noOfRowsInTable = 0;
        String key_Main_Page = null;
        int rowNumberIsforKey = rowNumberIs;
        do {


            Row row = sheet.getRow(rowNumberIs + 1);
            Row rowKey = sheet.getRow(rowNumberIsforKey);
            HashMap<String, String> executionPerameters = new HashMap<>();

            for (int cell = 1; cell < rowKey.getLastCellNum(); cell++) {
                try {
                    Cell cellvalue = row.getCell(cell);
                    String value_Main_Page = cellvalue.toString();


                    if (isValidFormat("dd-MMM-yyyy", value_Main_Page)) {
                        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date inputDate = inputFormat.parse(value_Main_Page);
                        value_Main_Page = outputFormat.format(inputDate);
                    }

                    Cell keyValue = rowKey.getCell(cell);
                    key_Main_Page = keyValue.toString();
                    executionPerameters.put(key_Main_Page, value_Main_Page);
                } catch (Exception e) {
                    break;
                }

            }
            System.out.println(executionPerameters);
            noOfRowsInTable++;
            hashMapData.put(noOfRowsInTable, executionPerameters);
//            System.out.println(hashMapData);
            rowNumberIs++;
            row = sheet.getRow(rowNumberIs + 1);
            Cell keyValue = row.getCell(1);
            key_Main_Page = keyValue.toString();
//            if(key_Main_Page.isEmpty()){
//                break;
//            }
        } while (!key_Main_Page.isEmpty());


        return hashMapData;
    }

    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
        }
        return date != null;
    }
    public static void clickenterValueInTextBox(WebElement element, String inputText) throws InterruptedException {
        element.click();
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(inputText);
        }
    }

    public static void UploadFile(WebElement element, String path) throws AWTException, InterruptedException {
        WebElement file = element;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", file);
        Robot r = new Robot();
        r.delay(2000);
        StringSelection ss = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);

        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);

        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(8000);
        driver.findElement(By.xpath("//span[text()='Done']")).click();
    }
}
