package Page_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WareHouse_SiteActivity {
    //to click on Site Activity
    @FindBy(xpath = "//span[text()='Site activity']")
    public WebElement element_SiteActivity_Click;
    //To click on What measure of site activity is being used for this rating?
    @FindBy(xpath = "//span[text()='Full-time equivalent workers (FTE workers)']")
    public WebElement element_FTE_Click;
    // To enter What is the number of full-time workers at the site?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[1]")
    public WebElement element_Full_time_workers;
    //to enter What is the total number of full-time equivalent part-time workers at the site?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[2]")
    public WebElement element_Part_time_workers;
    //to enter What is the total number of full-time equivalent casual and shift workers at the site?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[3]")
    public WebElement element_Casual_and_shift_workers;
    //to enter What is the total number of full-time equivalent aggregated workers at the site?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[4]")
    public WebElement element_Aggregated_workers;
    // to enter Would you like to add any other information about anything on this screen?
    @FindBy(xpath = "//textarea[@placeholder='Please add explanations here']")
    public WebElement element_Rated_FTE_workers_Information_text;
    // to click on Annual turnover ratio
    @FindBy(xpath = "//span[text()='Annual turnover ratio']")
    public WebElement element_Annual_turnover_ratio;
    // to enter What was the annual product throughput?

    @FindBy(xpath="//div[@class='slds-m-top_small slds-col']/button[2]")
    public WebElement element_Space_and_Hours_Pre_Fill_click;

    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[1]")
    public WebElement element_Atr_textbox_1;
    //to enter What was the maximum monthly average inventory level for the rating period?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[2]")
    public WebElement element_Atr_textbox_2;
    //to enter What units were used from all streams of the site to calculate the above numbers?
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[3]")
    public WebElement element_Atr_textbox_3;
    //to enter Rated annual turnover ratio //Would you like to add any other information about anything on this screen?
    @FindBy(xpath = "//div[@class='slds-form-element__control slds-grow textarea-container']")
    public WebElement element_Rated_annual_turnover_ratio_textbox;
    //to click on SiteActivity save
    @FindBy(xpath = "//button[text()='Save']")
    public WebElement element_SiteActivity_Save;
    public WareHouse_SiteActivity(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public WebElement get_element_SiteActivity_Click()
    {
        return element_SiteActivity_Click;
    }
    public WebElement get_element_FTE_Click()
    {
        return element_FTE_Click;
    }
    public WebElement get_element_Space_and_Hours_Pre_Fill_click()
    {
        return element_Space_and_Hours_Pre_Fill_click;
    }
    public WebElement get_element_Full_time_workers()
    {
        return element_Full_time_workers ;
    }

    public WebElement get_element_Part_time_workers()
    {
        return element_Part_time_workers ;
    }
    public WebElement get_element_Casual_and_shift_workers()
    {
        return element_Casual_and_shift_workers ;
    }
    public WebElement get_element_Aggregated_workers()
    {
        return element_Aggregated_workers ;
    }
    public WebElement get_element_Rated_FTE_workers_Information_text()
    {
        return element_Rated_FTE_workers_Information_text ;
    }
    public WebElement get_element_Annual_turnover_ratio()
    {
        return element_Annual_turnover_ratio ;
    }
    public WebElement get_element_Atr_textbox_1()
    {
        return element_Atr_textbox_1 ;
    }
    public WebElement get_element_Atr_textbox_2()
    {
        return element_Atr_textbox_2 ;
    }
    public WebElement get_element_Atr_textbox_3()
    {
        return element_Atr_textbox_3 ;
    }
    public WebElement get_element_Rated_annual_turnover_ratio_textbox()
    {
        return element_Rated_annual_turnover_ratio_textbox ;
    }
    public WebElement get_element_SiteActivity_Save()
    {
        return element_SiteActivity_Save ;
    }

}
