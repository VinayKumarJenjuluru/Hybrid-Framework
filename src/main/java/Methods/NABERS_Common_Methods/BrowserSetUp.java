package Methods.NABERS_Common_Methods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static Framework_Methods.Generic_Methods.ReadDataFromPropertiesFile;
import static Framework_Methods.Generic_Methods.globalPropertiesFileNameAndPath;

public class BrowserSetUp {
    public static WebDriver driver;

    // WebDriverManager driverManager = null;

    public static WebDriver browserLaunch(String browserName, String executionEnvironment) throws InterruptedException, IOException {

        String dataSheetPathAndName = System.getProperty("user.dir") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFilePath") + ReadDataFromPropertiesFile(globalPropertiesFileNameAndPath).getProperty("ExcelDataSheetFileName");

        switch (browserName.toUpperCase()) {
            case "CHROME":
//                System.setProperty("webdriver.chrome.driver","D:\\Nabers_FrameWork\\src\\test\\java\\Driver\\chromedriver.exe");
                ChromeOptions option = new ChromeOptions();
                option.addArguments("incognito");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(option);
                break;
            case "EDGE":
                EdgeOptions opt = new EdgeOptions();
                opt.addArguments("InPrivate");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(opt);
                break;
        }

        HashMap<String,String> loginDetails=loginLink(executionEnvironment,"Assessor",dataSheetPathAndName,globalPropertiesFileNameAndPath);
        driver.get(loginDetails.get("LoginLink"));
//        switch (executionEnvironment) {
//            case "SIT":
//                driver.get("https://nswpe-dt3.pegacloud.io/prweb/PRAuth/");
//                break;
//            case "UAT":
//                driver.get("https://apps-uat.planningportal.nsw.gov.au/prweb/PRAuth/PlanningSSO");
//                break;
//        }


        Thread.sleep(2000);
        driver.manage().window().maximize();
        return driver;

    }

    public static void zoomIn(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '1.2'");
    }

    public static void zoomOut(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '0.8'");
    }

    public void zoomOutBrowserWindowWithRobotClass() throws IOException, InterruptedException, AWTException {
        Robot robot = new Robot();
        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        Thread.sleep(10000);
    }
    public static HashMap loginLink(String Environment,String Logins, String dataSheetPathAndName,String globalPropertiesFileNameAndPath) throws IOException {
        FileInputStream fis = new FileInputStream(dataSheetPathAndName);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet xs = wb.getSheet("private_Data");
        HashMap<String, String> LoginDEHashMap = new HashMap<>();

       for(int i=1;i<=xs.getLastRowNum();i++)
       {
           Row row = xs.getRow(i);
           Row rowKey = xs.getRow(0);
           for (int cell = 0; cell < rowKey.getLastCellNum(); cell++) {
               Cell cellvalue = row.getCell(cell);
               String value_Main_Page = cellvalue.toString();

               Cell keyValue = rowKey.getCell(cell);
               String key_Main_Page = keyValue.toString();
               if(key_Main_Page.equalsIgnoreCase("password"))
               {
                   value_Main_Page= passwordCry(value_Main_Page);
               }

               LoginDEHashMap.put(key_Main_Page, value_Main_Page);
           }


           if(Environment.equalsIgnoreCase(LoginDEHashMap.get("Environment")) && Logins.equalsIgnoreCase(LoginDEHashMap.get("Logins")))
           {
//               System.out.println(LoginDEHashMap);
               break;
           }
       }
        return LoginDEHashMap;
    }












    public static String passwordCry(String passwordEncoded)
    {
        String cryptedValue="";
        String againValue="";
        char arr[]=passwordEncoded.toCharArray();

        for (int i=0;i< arr.length;i++)
        {
            for(int j=0;j<i+1;j++)
            {
                if(i%2==0)
                {
                    arr[i]++;
                }
                else {
                    arr[i]--;
                    arr[i]--;
                }
            }
            cryptedValue=cryptedValue+arr[i];
        }
        System.out.println(cryptedValue);
    return cryptedValue;
    }


}