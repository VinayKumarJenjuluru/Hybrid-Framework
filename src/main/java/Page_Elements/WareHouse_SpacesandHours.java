package Page_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WareHouse_SpacesandHours {
    //to click on Spaces and Hours
    @FindBy(xpath = "//span[text()='Spaces and hours']")
    public WebElement element_Space_and_Hours;
    //To click on Space and Hours of No, I don't want to pre-fill
    @FindBy(xpath="//div[@class='slds-m-top_small slds-col']/button[2]")
    public WebElement element_Space_and_Hours_Pre_Fill_click;
    //To click on Does the site include refrigerated spaces (e.g. cold room or cool room)
    @FindBy(xpath="//span[@class='slds-radio']//label")
    public List<WebElement> element_refrigerated_space;
    // To enter Explain why estimation was used for refrigerated area and/or height
    @FindBy(xpath="//textarea[@placeholder='For each row where estimation has been used, enter the description of the zone and the reason for the estimation']")
    public WebElement element_refrigerated_area_height;
    //To enter Explain why estimation was used for non-refrigerated area and/or hours
    @FindBy(xpath="//textarea[@placeholder='For each row where estimation has been used, enter the description of the operational area and the reason for the estimation']")
    public WebElement element_non_refrigerated_area_hours;
    //To enter When the total area entered on this page is not equal to the rated site area, explain the reason for the difference
    @FindBy(xpath="(//div[@class='slds-form-element__control slds-grow textarea-container'])[3]")
    public WebElement element_area_comfirmation_textbox1;
    // to enter Would you like to add any other information about anything on this screen?
    @FindBy(xpath="(//div[@class='slds-form-element__control slds-grow textarea-container'])[4]")
    public WebElement element_area_comfirmation_textbox2;
    //To click on space and hours save page
    @FindBy(xpath="//button[text()='Save']")
    public WebElement element_space_hours_save;
    public WareHouse_SpacesandHours(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement get_element_Space_and_Hours() {
        return element_Space_and_Hours;
    }
        public WebElement get_element_Space_and_Hours_Pre_Fill_click()
        {
            return element_Space_and_Hours_Pre_Fill_click;
        }
    public List<WebElement> get_element_refrigerated_space()
    {
        return element_refrigerated_space;
    }
    public WebElement get_element_refrigerated_area_height()
    {
        return element_refrigerated_area_height;
    }
    public WebElement get_element_non_refrigerated_area_hours()
    {
        return element_non_refrigerated_area_hours;
    }
    public WebElement get_element_area_comfirmation_textbox1()
    {
        return element_area_comfirmation_textbox1;
    }
    public WebElement get_element_area_comfirmation_textbox2()
    {
        return element_area_comfirmation_textbox2;
    }
    public WebElement get_element_space_hours_save(){
        return element_space_hours_save;
    }

    }

