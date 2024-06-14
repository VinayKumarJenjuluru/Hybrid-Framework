import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class demo {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the chromedriver executable
        ChromeOptions option = new ChromeOptions();
        option.addArguments("incognito");
        option.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        driver = new ChromeDriver(option);

        // Open Facebook URL
        driver.get("https://nabers--pathtest.sandbox.my.salesforce.com/");
        Thread.sleep(2000);
//        vinay.jenjuluru@dpie.nsw.gov.au.pathtest
//        P@th@1234
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("vinay.jenjuluru@dpie.nsw.gov.au.pathtest");
        driver.findElement(By.xpath("//input[@name='pw']")).sendKeys("P@th@1234");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='Login']")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
        wait.until(ExpectedConditions.titleIs("Home | Salesforce"));
        System.out.println("vinay");
        Thread.sleep(10000);
        System.out.println("v");
        WebElement companiesTab = driver.findElement(By.xpath("//span[text()='Companies']"));
        System.out.println("salman");
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Companies']/../..")));
        jsClick(companiesTab);

        WebElement newButtonClick=driver.findElement(By.xpath("//div[text()='New']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='New']")));

        jsClick(newButtonClick);
    }
    public static void jsClick(WebElement element) throws InterruptedException {
        Thread.sleep(2000);
        execJavascript("arguments[0].click();", element);
    }
    public static synchronized Object execJavascript(String script, Object... args) {
        JavascriptExecutor scriptExe = ((JavascriptExecutor) driver);
        return scriptExe.executeScript(script, args);
    }
}
