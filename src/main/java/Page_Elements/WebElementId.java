package Page_Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class WebElementId {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Nabers_FrameWork\\src\\test\\java\\Driver\\chromedriver.exe");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage
        driver.get("https://www.flipkart.com/");

        // Find all web elements on the webpage
        List<WebElement> allElements = driver.findElements(By.xpath("//*[contains(@text,'')]"));
int n=0;
        // Iterate through each web element and print its ID
        for (WebElement element : allElements) {
            String elementID = element.getAttribute("id");
            if (elementID != null && !elementID.isEmpty()) {
                n++;
                Thread.sleep(100);
                String v=element.getText();

                System.out.println(n+". "+v+": Element ID: " + elementID);
            }
        }

        // Close the browser
        driver.quit();
    }
}
