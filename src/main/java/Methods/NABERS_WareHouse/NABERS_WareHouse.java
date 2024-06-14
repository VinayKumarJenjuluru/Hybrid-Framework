package Methods.NABERS_WareHouse;

import Methods.NABERS_Common_Methods.NABERS_Common_Methods;
import Page_Elements.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static Framework_Methods.Generic_Methods.*;
import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.*;

public class NABERS_WareHouse {
    public static boolean RatingDetails(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1[text()='Rating details']")));
            NABERS_Common_Methods commonMethodsObject = new NABERS_Common_Methods();
            Common_WeElement CommonWebElement = new Common_WeElement(driver);
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[text()='Save']")).click();
            strMethodsStatus=true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            strMethodsStatus=false;
        }

        return strMethodsStatus;
    }
    public static boolean SiteArea(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus=false;
        System.out.println("siteArea Enter ho raha hai");
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        WareHouse_SiteArea ws=new WareHouse_SiteArea(driver);
        try{
            HashMap<String,String> hmMethodDataFill=hashMapReturn( rowNumber,executionSheetName);
            Thread.sleep(3000);
            clickOnButton(ws.get_element_SiteArea_Click());
            Thread.sleep(6000);
            try
            {
                clickOnButton(ws.get_element_Site_Area_Pre_fill_click());
            }
            catch (Exception e)
            {
                System.out.println("pre fill nahi hai bhai issliye aaya"+e);
            }
            Thread.sleep(3000);
            radioButtonClick(ws.get_element_Configuration_Rating(),hmMethodDataFill.get("Configuration rating"));
            Thread.sleep(3000);
            clickAndEnterValueInTextBox(ws.get_element_total_GLA(),hmMethodDataFill.get("Total site GLA (m²)"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(3000);
            clickOnButton(ws.get_element_SiteArea_Save());
            strMethodsStatus=true;
        }
        catch (Exception e)
        {
            strMethodsStatus=false;
        }
        return strMethodsStatus;
    }
    public static boolean SpacesandHours(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus=false;
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        WareHouse_SpacesandHours spacehours=new WareHouse_SpacesandHours(driver);
        try{
            System.out.println("SpacesandHours enter ho raha hai");
            JavascriptExecutor js = (JavascriptExecutor)driver;
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            HashMap<String,String> hmMethodDataFill=hashMapReturn( rowNumber,executionSheetName);
            Thread.sleep(3000);
            clickOnButton(spacehours.get_element_Space_and_Hours());
            Thread.sleep(6000);

            try{
                clickOnButton(spacehours.get_element_Space_and_Hours_Pre_Fill_click());
            }
            catch (Exception e)
            {
                System.out.println("pre fill nahi hai bhai issliye aaya"+e);
            }
            Thread.sleep(3000);
            js.executeScript("window.scrollBy(0,200)");
            Thread.sleep(3000);
            radioButtonClick(spacehours.get_element_refrigerated_space(),hmMethodDataFill.get("Refrigerated space"));
            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(3000);
            commonMethods.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));
            //clickandsendtext(spacehours.get_element_refrigerated_area_height(),hmMethodDataFill.get("Refrigerated area and/or height"));
            js.executeScript("window.scrollBy(0,500)");
            Thread.sleep(3000);
            commonMethods.DataFillingIntoTheWebTable2(driver,executionSheetName,hmMethodDataFill.get("Table2"),2);

            Thread.sleep(3000);
            clickAndEnterValueInTextBox(driver.findElement(By.xpath("//div/label[text()='When the total area entered on this page is not equal to the rated site area, explain the reason for the difference.']//following-sibling::lightning-textarea/div/textarea")),"vinay");
            Thread.sleep(2000);
            clickAndEnterValueInTextBox(driver.findElement(By.xpath("//div/label[text()='Would you like to add any other information about anything on this screen?']//following-sibling::lightning-textarea/div/textarea")),"salman");

            // Thread.sleep(3000);
            //js.executeScript("window.scrollBy(0,400)");
           // clickandsendtext(spacehours.get_element_non_refrigerated_area_hours(),hmMethodDataFill.get("Nonrefrigerated area and/or hours"));
           // Thread.sleep(2000);
            //js.executeScript("window.scrollBy(0,400)");
           // Thread.sleep(3000);
           /* clickandsendtext(spacehours.get_element_area_comfirmation_textbox1(),hmMethodDataFill.get("AC1"));
            clickandsendtext(spacehours.get_element_area_comfirmation_textbox2(),hmMethodDataFill.get("AC2"));
            js.executeScript("window.scrollBy(0,400)");*/
            Thread.sleep(3000);
            clickOnButton(spacehours.get_element_space_hours_save());
            strMethodsStatus=true;

        }
        catch (Exception e)
        {
            strMethodsStatus=false;
        }
        return strMethodsStatus;
    }
    public static boolean SiteActivity(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus=false;
        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");
        WareHouse_SiteActivity siteactivity=new WareHouse_SiteActivity(driver);
        try{
            JavascriptExecutor js = (JavascriptExecutor)driver;
            NABERS_Common_Methods commonMethods=new NABERS_Common_Methods();
            HashMap<String,String> hmMethodDataFill=hashMapReturn( rowNumber,executionSheetName);
            Thread.sleep(3000);
            clickOnButton(siteactivity.get_element_SiteActivity_Click());
            Thread.sleep(6000);
            try{
                clickOnButton(siteactivity.get_element_Space_and_Hours_Pre_Fill_click());
            }
            catch (Exception e)
            {
                System.out.println("pre fill nahi hai bhai issliye aaya"+e);
            }
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,400)");
            Thread.sleep(3000);
            clickOnButton(siteactivity.get_element_FTE_Click());
            Thread.sleep(3000);
            clickAndEnterValueInTextBox(siteactivity.get_element_Full_time_workers(),hmMethodDataFill.get("FullTime workers"));
            clickAndEnterValueInTextBox(siteactivity.get_element_Part_time_workers(),hmMethodDataFill.get("PartTime workers"));
            clickAndEnterValueInTextBox(siteactivity.get_element_Casual_and_shift_workers(),hmMethodDataFill.get("CasualandShift workers"));
            js.executeScript("window.scrollBy(0,400)");
           Thread.sleep(3000);
            clickAndEnterValueInTextBox(siteactivity.get_element_Aggregated_workers(),hmMethodDataFill.get("Aggregated workers"));
           System.out.println("aggregate out ");
           js.executeScript("window.scrollBy(0,400)");
            clickAndEnterValueInTextBox(siteactivity.get_element_Rated_FTE_workers_Information_text(),hmMethodDataFill.get("RTEInformation"));
           clickOnButton(siteactivity.get_element_SiteActivity_Save());
            strMethodsStatus=true;

        }
        catch (Exception e)
        {
            strMethodsStatus=false;
        }
        return strMethodsStatus;
    }


}
