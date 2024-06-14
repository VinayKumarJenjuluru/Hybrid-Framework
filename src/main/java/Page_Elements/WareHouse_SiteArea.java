package Page_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WareHouse_SiteArea {
    //To click on Site Area
    @FindBy(xpath = "//span[text()='Site area']")
    public WebElement element_SiteArea_Click;
    //To click on No, I don't want to pre-fill
    @FindBy(xpath="//div[@class='slds-m-top_small slds-col']/button[2]")
    public WebElement element_Site_Area_Pre_fill_click;
    //To click on Configuration of this rating

    @FindBy(xpath = "//lightning-radio-group[@class='noLabelRadio slds-p-top_small slds-form-element']//div//span//label")
    public List<WebElement> element_Configuration_Rating;
    //To enter a value for  total site GLA (m²)
    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-grow'])[1]")
    public WebElement element_total_GLA ;
    //To click on Site Area Save
    @FindBy(xpath = "//button[text()='Save']")
    public WebElement element_SiteArea_Save;
    public WareHouse_SiteArea(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public WebElement get_element_SiteArea_Click() {
        return element_SiteArea_Click;
    }
    public WebElement get_element_Site_Area_Pre_fill_click() {
        return element_Site_Area_Pre_fill_click;
    }
    public List<WebElement> get_element_Configuration_Rating() {
        return element_Configuration_Rating;
    }
    public WebElement get_element_total_GLA(){return element_total_GLA;}

   public WebElement get_element_SiteArea_Save(){return element_SiteArea_Save;}


}
