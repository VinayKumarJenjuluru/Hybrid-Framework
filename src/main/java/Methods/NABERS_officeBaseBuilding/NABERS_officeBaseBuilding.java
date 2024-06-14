package Methods.NABERS_officeBaseBuilding;

import Methods.NABERS_Common_Methods.NABERS_Common_Methods;
import Page_Elements.Common_WeElement;
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

import static Methods.NABERS_Common_Methods.NABERS_Common_Methods.hashMapReturn;

public class NABERS_officeBaseBuilding {

    public static boolean RatingDetails(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1[text()='Rating details']")));
            NABERS_Common_Methods commonMethodsObject=new NABERS_Common_Methods();
            Common_WeElement CommonWebElement=new Common_WeElement(driver);
            HashMap<String,String> hmMethodDataFill=hashMapReturn( rowNumber,executionSheetName);
            Thread.sleep(3000);
            commonMethodsObject.checkBoxClick(CommonWebElement.get_element_RatingDetails_Type(),hmMethodDataFill.get("RatingDetails"));
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[text()='Save']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lable[text()='Data is saved and validated']")));
            strMethodsStatus=true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            strMethodsStatus=false;
        }
        System.out.println("RatingPeriod");
        return strMethodsStatus;
    }



















    public static boolean vinay(int rowNumber, WebDriver driver, String executionSheetName, HashMap executionPerameters) throws IOException, InterruptedException, AWTException, InvalidFormatException {

        boolean strMethodsStatus = false;
        System.out.println("NABERS_officeBaseBuilding_vinay");
        return strMethodsStatus;
    }

}
