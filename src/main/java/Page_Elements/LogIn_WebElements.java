package Page_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogIn_WebElements {
    @FindBy(xpath=" //input[@placeholder=\"Username\"]")
    public WebElement element_UserName_TextBox;
    @FindBy(xpath = "//input[@placeholder=\"Password\"]")
    public WebElement element_Password_TextBox;
    @FindBy(xpath = "//button/span[text()='Log in']")
    public WebElement element_LogIn_Button;
    @FindBy(xpath = "//a[text()='Create a new rating']")
    public WebElement element_Create_a_new_rating;
    @FindBy(xpath ="//button[@aria-label='Select building type']")
    public WebElement element_Select_building_type;
    @FindBy(xpath = "//div[@part=\"options\"]//following-sibling::label")
    public List<WebElement> element_select_Scope;



    public LogIn_WebElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public WebElement get_element_UserName_TextBox() {
        return element_UserName_TextBox;
    }
    public WebElement get_element_Password_TextBox() {
        return element_Password_TextBox;
    }
    public WebElement get_element_LogIn_Button() {
        return element_LogIn_Button;
    }
    public WebElement get_element_Create_a_new_rating(){return element_Create_a_new_rating;}
    public WebElement get_element_Select_building_type(){return element_Select_building_type;}
    public List<WebElement> get_element_select_Scope(){return element_select_Scope;}

}
