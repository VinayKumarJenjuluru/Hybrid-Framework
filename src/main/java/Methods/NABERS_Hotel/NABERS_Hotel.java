package Methods.NABERS_Hotel;

import Framework_Methods.Generic_Methods;
import Methods.NABERS_Common_Methods.NABERS_Common_Methods;
import Page_Elements.Common_WeElement;
import Page_Elements.Hotel_WebElements;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.*;

public class NABERS_Hotel extends Generic_Methods {
    public static boolean RatingDetails(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1[text()='Rating details']")));
            NABERS_Common_Methods commonMethodsObject = new NABERS_Common_Methods();
            Common_WeElement CWE = new Common_WeElement(driver);
            Hotel_WebElements hWE = new Hotel_WebElements(driver);
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            Thread.sleep(2000);
            commonMethodsObject.checkBoxClick(CWE.get_element_RatingDetails_Type(), hmMethodDataFill.get("RatingDetails"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");
            Thread.sleep(3000);
            dropDownOptionSelection(driver, hWE.getelement_ClickOnHotelType(), hWE.getelement_SelectHotelFromDropDown(), hmMethodDataFill.get("Select hotel type"));

            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand slds-float_right slds-m-left_x-small']")).click();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lable[text()='Data is saved and validated']")));
            strMethodsStatus = true;
        } catch (Exception e) {
            System.out.println("Error in Ratingdetails"+e);
            strMethodsStatus = false;
        }
        System.out.println("Ratingdetails");
        return strMethodsStatus;
    }

    public static boolean SizeAndServicing(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        NABERS_Common_Methods comm =new NABERS_Common_Methods();
        Hotel_WebElements hwe=new Hotel_WebElements(driver);

        try {
            Thread.sleep(2000);
            clickOnButton(hwe.getelement_clickOnSizeAndServicing());
            HashMap<String,String> hmMethodDataFill=hashMapReturn( rowNumber,executionSheetName);
         Hotel_WebElements hWE = new Hotel_WebElements(driver);
            Thread.sleep(2000);
        if (hmMethodDataFill.get("Button").equalsIgnoreCase("Yes,pre-fill")){
            radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));}

        else if (hmMethodDataFill.get("Button").equalsIgnoreCase("No, I don't want to pre-fill")) {
            System.out.println("No, I don't want to pre-fill");
            Thread.sleep(2000);
            radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));
            String value = hmMethodDataFill.get("provides services to the Rated Hotel?");
            js.executeScript("window.scrollBy(0,400)");
            switch (value) {
                case "Yes, to the rated hotel only":
                    Thread.sleep(3000);
                    radioButtonClick(hWE.getelement_ProvidesServicesToTheRatedHotel(),value);
                    Thread.sleep(1000);
                    js.executeScript("window.scrollBy(0,400)");
                    Thread.sleep(5000);
                    enterValueInTextBox(hWE.getelement_LaundryOperationalDays(),hmMethodDataFill.get("How many laundry operational days during the rating period?"));
                    Thread.sleep(3000);
                    comm.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));
                    break;
                case "Yes, to the rated hotel and external hotel(s)":
                    Thread.sleep(2000);
                    radioButtonClick(hWE.getelement_ProvidesServicesToTheRatedHotel(),value);
                    Thread.sleep(1000);
                    js.executeScript("window.scrollBy(0,400)");
                    Thread.sleep(5000);
                    comm.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));

                    break;
                case "No":
                    Thread.sleep(2000);
                    radioButtonClick(hWE.getelement_ProvidesServicesToTheRatedHotel(),value);
                    Thread.sleep(1000);
                    js.executeScript("window.scrollBy(0,400)");
                    Thread.sleep(5000);
                    comm.DataFillingIntoTheWebTable(driver,executionSheetName,hmMethodDataFill.get("Table"));
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        } else {}
             Thread.sleep(2000);
            clickOnButton(hWE.getelement_ClickOnSave());

            Thread.sleep(3000);
            System.out.println("SizeAndServicing");
            strMethodsStatus=true;
        }

        catch (Exception e){
            System.out.println("exception in sizeAndServicing"+e);
        }
        System.out.println("SizeAndServicing method completed");

        return strMethodsStatus;
    }

    public static boolean HotelQuality(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Hotel_WebElements hWE = new Hotel_WebElements(driver);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            System.out.println("HotelQuality me enter ho raha hai");
            clickOnButton(hWE.getelement_clickOnHotelQuality());
            System.out.println("HotelQuality click ho raha hai");
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            if (hmMethodDataFill.get("Button").equalsIgnoreCase("Yes,pre-fill")) {
                System.out.println("Yes,pre-fill");
                radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));
                Thread.sleep(5000);
            } else if (hmMethodDataFill.get("Button").equalsIgnoreCase("No, I don't want to pre-fill")) {
                System.out.println("No, I don't want to pre-fill");
                radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));
                Thread.sleep(3000);
                radioButtonClick(hWE.getelement_HotelRatingselectPrefil(), hmMethodDataFill.get("Button"));
                Thread.sleep(4000);
                js.executeScript("window.scrollBy(0,400)");
                dropDownOptionSelection(driver, hWE.getelement_SelectHotelRating(), hWE.getelement_SelectHotelRatingDropDown(), hmMethodDataFill.get("Star quality rating?"));
                Thread.sleep(3000);
                clickOnButton(hWE.getelement_ClickOnSave());
            } else {
            }
            strMethodsStatus = true;
        } catch (Exception e) {
            strMethodsStatus = false;
            System.out.println("error in HotelQuality" + e);
        }
        System.out.println("HotelQuality");
        return strMethodsStatus;

    }

    public static boolean Facilities(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {
        boolean strMethodsStatus = false;
        Hotel_WebElements hWE = new Hotel_WebElements(driver);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        try {Thread.sleep(3000);
            System.out.println("Facilities me ja raha hai");
            HashMap<String, String> hmMethodDataFill = hashMapReturn(rowNumber, executionSheetName);
            clickOnButton(hWE.getlement_ClickOnFacilities());
            Thread.sleep(5000);
            if (hmMethodDataFill.get("Button").equalsIgnoreCase("Yes,pre-fill")) {
                System.out.println("Yes,pre-fill");
                radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));
                Thread.sleep(5000);
                js.executeScript("window.scrollBy(0,400)");
            } else if (hmMethodDataFill.get("Button").equalsIgnoreCase("No, I don't want to pre-fill")) {
                radioButtonClick(hWE.getelement_SelectPrefill(),hmMethodDataFill.get("Button"));
                js.executeScript("window.scrollBy(0,400)");
                if (hmMethodDataFill.get("Are there any heated pools or spas?").equalsIgnoreCase("No")) {
                    Thread.sleep(3000);
                    radioButtonClick(hWE.getelement_heatedpools_yesOrNo(), hmMethodDataFill.get("Are there any heated pools or spas?"));
                } else if (hmMethodDataFill.get("Are there any heated pools or spas?").equalsIgnoreCase("Yes")) {
                } else {}
                if (hmMethodDataFill.get("Does the hotel have any function rooms?").equalsIgnoreCase("No")) {
                    Thread.sleep(3000);
                    radioButtonClick(hWE.getelement_functionRooms_YesOrNo(), hmMethodDataFill.get("Does the hotel have any function rooms?"));
                } else if (hmMethodDataFill.get("Does the hotel have any function rooms?").equalsIgnoreCase("Yes")) {
                } else {}
            }
            js.executeScript("window.scrollBy(0,400)");
            clickOnButton(hWE.getelement_ClickOnSave());
            strMethodsStatus=true;
            System.out.println("Facilities");
        } catch (Exception e) {
            strMethodsStatus=false;
            System.out.println("error in Facilities" + e);
        }

        return strMethodsStatus;
    }



    public static boolean Logout(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        System.out.println("NABERS_officeBaseBuilding_vinay");
        return strMethodsStatus;
    }

}
