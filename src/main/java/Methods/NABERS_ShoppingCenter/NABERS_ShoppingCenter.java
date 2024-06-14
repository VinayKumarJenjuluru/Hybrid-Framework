package Methods.NABERS_ShoppingCenter;

import Framework_Methods.Generic_Methods;
import Methods.NABERS_Common_Methods.NABERS_Common_Methods;
import Page_Elements.Common_WeElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Framework_Methods.Generic_Methods.*;
import static Framework_Methods.Generic_Methods.ReadDataFromPropertiesFile;
import static Methods.NABERS_Common_Methods.BrowserSetUp.driver;
import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.*;

public class NABERS_ShoppingCenter {
    public static boolean RatingDetails(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1[text()='Rating details']")));
            NABERS_Common_Methods commonMethodsObject = new NABERS_Common_Methods();
            Common_WeElement CommonWebElement = new Common_WeElement(driver);
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            commonMethodsObject.checkBoxClick(CommonWebElement.get_element_RatingDetails_Type(), hmMethodDataFill.get("RatingDetails"));
            commonMethodsObject.radioButtonClick(CommonWebElement.get_element_Select_shopping_centre_size(), hmMethodDataFill.get("Select shopping centre size_RatingDetails"));
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");
            if (hmMethodDataFill.get("RatingDetails").contains("Energy")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Is the shopping centre single or multi-storey?']")));
                commonMethodsObject.radioButtonClick(CommonWebElement.get_element_Is_the_shopping_centre_single_or_multi_storey(), hmMethodDataFill.get("Is the shopping centre single or multi-storey?"));
            }
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[text()='Save']")).click();
            System.out.println("NABERS_ShoppingCenter");
            System.out.println(hmMethodDataFill.get("RatingDetails"));
            System.out.println(hmMethodDataFill.get("Select shopping centre size_RatingDetails"));
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println(e);
            strMethodsStatus = false;
        }
        System.out.println("RatingPeriod");
        return strMethodsStatus;
    }

    public static boolean HouseAreaService(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            NABERS_Common_Methods commonMethods = new NABERS_Common_Methods();
            Generic_Methods genericMethods = new Generic_Methods();
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (!RatingDetails.get("RatingDetails").equalsIgnoreCase("Water")) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Area']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_House_Area_Service");
                Thread.sleep(5000);

                enterValueInTextBox(driver.findElement(By.xpath("//div[text()='How many trading days were there during the rating period?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                enterValueInTextBox(driver.findElement(By.xpath("//div[text()='What were the weekly core hours of service?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                js.executeScript("window.scrollBy(0,400)");
                genericMethods.zoomOutBrowserWindowWithRobotClass(2);
                commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));





                Thread.sleep(2000);
                clickAndEnterValueInTextBox(driver.findElement(By.xpath("//label[text()='Would you like to add an explanation for anything on this screen?']//following-sibling::lightning-textarea//textarea")), "vinayyyyyy");
                Thread.sleep(3000);
                driver.findElement(By.xpath("//button[text()='Save']")).click();
            }


            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
            System.out.println(e);
            return strMethodsStatus;
        }
        return strMethodsStatus;
    }
    public static boolean Area(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Generic_Methods genericMethods=new Generic_Methods();
            NABERS_Common_Methods commonMethods = new NABERS_Common_Methods();
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (RatingDetails.get("RatingDetails").equalsIgnoreCase("Water") && RatingDetails.get("Select shopping centre size_RatingDetails").equalsIgnoreCase("Large")) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Area']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_Area");
            Thread.sleep(5000);
                enterValueInTextBox(driver.findElement(By.xpath("//div[text()='How many trading days were there during the rating period?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                enterValueInTextBox(driver.findElement(By.xpath("//div[text()='What were the weekly core hours of service?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                js.executeScript("window.scrollBy(0,400)");
                genericMethods.zoomOutBrowserWindowWithRobotClass(2);
                commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));


                Thread.sleep(2000);
                clickAndEnterValueInTextBox(driver.findElement(By.xpath("//label[text()='Would you like to add an explanation for anything on this screen?']//following-sibling::lightning-textarea//textarea")), "vinayyyyyy");
                Thread.sleep(3000);
                driver.findElement(By.xpath("//button[text()='Save']")).click();
//            windowScroll(driver,comm.getelement_SaveButton());
//            clickOnButton(comm.getelement_SaveButton());
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }
    public static boolean HouseArea(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Generic_Methods genericMethods=new Generic_Methods();
            NABERS_Common_Methods commonMethods = new NABERS_Common_Methods();
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (RatingDetails.get("RatingDetails").equalsIgnoreCase("Water")) {
                if (RatingDetails.get("Select shopping centre size_RatingDetails").contains("Small") || RatingDetails.get("Select shopping centre size_RatingDetails").contains("small")) {
                    Thread.sleep(7000);
                    clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Area']")));
                    HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                    System.out.println("NABERS_officeBaseBuilding_House_Area");

                Thread.sleep(5000);
                    enterValueInTextBox(driver.findElement(By.xpath("//div[text()='How many trading days were there during the rating period?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                    enterValueInTextBox(driver.findElement(By.xpath("//div[text()='What were the weekly core hours of service?']//../input[@class='slds-input']")), hmMethodDataFill.get("How many trading days were there during the rating period?"));
                    js.executeScript("window.scrollBy(0,400)");
                    genericMethods.zoomOutBrowserWindowWithRobotClass(2);
                    commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));


                    Thread.sleep(2000);
                    clickAndEnterValueInTextBox(driver.findElement(By.xpath("//label[text()='Would you like to add an explanation for anything on this screen?']//following-sibling::lightning-textarea//textarea")), "vinayyyyyy");
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("//button[text()='Save']")).click();

//                windowScroll(driver,comm.getelement_SaveButton());
//                clickOnButton(comm.getelement_SaveButton());

                }
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }
    public static boolean Parkingspace(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (!RatingDetails.get("RatingDetails").equalsIgnoreCase("Water")) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Parking_spaces']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_Parking_space");
                Thread.sleep(5000);
                radioButtonClick(driver.findElements(By.xpath("//fieldset//following-sibling::label")), hmMethodDataFill.get("Are there any parking spaces in the shopping centre?"));
                Thread.sleep(5000);

                if(hmMethodDataFill.get("Are there any parking spaces in the shopping centre?").equalsIgnoreCase("yes"))
                {
                    js.executeScript("window.scrollBy(0,400)");
                    commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));

                }
                windowScroll(driver, comm.getelement_SaveButton());
                driver.findElement(By.xpath("//button[text()='Save']")).click();
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }
    public static boolean FoodCourt(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (!(RatingDetails.get("Select shopping centre size_RatingDetails").contains("Large") && RatingDetails.get("RatingDetails").equalsIgnoreCase("Energy"))) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Food_courts']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_Food_Court");
                Thread.sleep(5000);
                radioButtonClick(driver.findElements(By.xpath("//fieldset//following-sibling::label")), hmMethodDataFill.get("Are there any food courts in the shopping centre?"));
                Thread.sleep(5000);

                if(hmMethodDataFill.get("Are there any food courts in the shopping centre?").equalsIgnoreCase("yes"))
                {
                    js.executeScript("window.scrollBy(0,400)");
                    commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));

                }

                windowScroll(driver, comm.getelement_SaveButton());
                driver.findElement(By.xpath("//button[text()='Save']")).click();
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }
    public static boolean Cinemas(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (RatingDetails.get("RatingDetails").contains("Water") || RatingDetails.get("RatingDetails").contains("water")) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Cinemas']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_Cinemas");
                Thread.sleep(5000);
                radioButtonClick(driver.findElements(By.xpath("//fieldset//following-sibling::label")), hmMethodDataFill.get("Are there any cinemas in the shopping centre?"));
                Thread.sleep(5000);

                if(hmMethodDataFill.get("Are there any cinemas in the shopping centre?").equalsIgnoreCase("yes"))
                {
                    js.executeScript("window.scrollBy(0,400)");
                    commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));


                }

                windowScroll(driver, comm.getelement_SaveButton());
                driver.findElement(By.xpath("//button[text()='Save']")).click();
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }
    public static boolean GYM(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Common_WeElement comm = new Common_WeElement(driver);
            String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
            int rowOfFindRow = findRowPositionInExcel(dataSheetPathAndName, executionSheetName, 0, "ShoppingCenter_RatingDetails");
            HashMap<String, String> RatingDetails = hashMapReturn(rowOfFindRow, executionSheetName);
//        Thread.sleep(3000);
            if (!(RatingDetails.get("Select shopping centre size_RatingDetails").contains("Large") && RatingDetails.get("RatingDetails").equalsIgnoreCase("Energy"))) {
                Thread.sleep(7000);
                clickOnButton(driver.findElement(By.xpath("//a[@data-page-name='Gymnasiums']")));
                HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
                System.out.println("NABERS_officeBaseBuilding_GYM");
                Thread.sleep(5000);
                radioButtonClick(driver.findElements(By.xpath("//fieldset//following-sibling::label")), hmMethodDataFill.get("Are there any gyms in the shopping centre?"));
                Thread.sleep(5000);
                boolean IsPresent=true;
                if(hmMethodDataFill.get("Are there any gyms in the shopping centre?").equalsIgnoreCase("yes")&&RatingDetails.get("RatingDetails").equalsIgnoreCase("Energy"))
                {
                    Thread.sleep(3000);
                    if(hmMethodDataFill.get("Are any of the gyms centrally or partially serviced?").equalsIgnoreCase("yes")){
                        IsPresent=true;
                        radioButtonClick(driver.findElements(By.xpath("//input[@name='gymsServicedGroup']//following-sibling::label")),hmMethodDataFill.get("Are any of the gyms centrally or partially serviced?"));
                    }
                    else {
                        IsPresent=false;
                    }

                    Thread.sleep(3000);
                }
                if(IsPresent && hmMethodDataFill.get("Are there any gyms in the shopping centre?").equalsIgnoreCase("yes"))
                {
                    System.out.println("salman bhai in the gym");
                    js.executeScript("window.scrollBy(0,400)");
                    Thread.sleep(3000);
                    if (RatingDetails.get("RatingDetails").equalsIgnoreCase("Water")) {
                        commonMethods.DataFillingIntoTheWebTable(driver, executionSheetName, hmMethodDataFill.get("Table1"));

                    } else {
                        commonMethods.DataFillingIntoTheWebTable(driver, executionSheetName, hmMethodDataFill.get("Table"));

                    }

                }
            }
            System.out.println("gym achese run hua");
            Thread.sleep(2000);
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println("vinay "+e);
            strMethodsStatus = false;
        }

        return strMethodsStatus;
    }

}
