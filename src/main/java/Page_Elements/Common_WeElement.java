package Page_Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Common_WeElement {

    @FindBy(xpath="//label[text()='Select rating type(s)']//following-sibling::div/div")
    public List<WebElement> element_RatingDetails_Type;
    @FindBy(xpath="//input[@name='smallShoppingCentreGroup']//following-sibling::label")
    public List<WebElement> element_Select_shopping_centre_size;
    @FindBy(xpath="//input[@name='shoppingCentreStoreyGroup']//following-sibling::label")
    public List<WebElement> element_Is_the_shopping_centre_single_or_multi_storey;
    @FindBy(xpath = "//div/label[text()='Starting date']//following-sibling::lightning-input/lightning-datepicker/div/div/input")
    public WebElement element_StartingDate;
    @FindBy(xpath = "//button[@class='slds-button slds-button_brand slds-float_right slds-m-left_x-small']")
    public WebElement element_SaveButton;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Premises']")
    public WebElement element_ClickOnPremises;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Grid electricity']")
    public WebElement element_ClickOnGridElect;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Gas and LPG']")
    public  WebElement element_ClickOnGasAndLPG;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Diesel']")
    public  WebElement element_ClickOnDiesel;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Water']")
    public  WebElement element_ClickOnWaternav;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Review summary']")
    public  WebElement element_ClickOnReviewSummary;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Certification process']")
    public  WebElement element_CertificationProcess;

    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Rating period']")
    public WebElement element_ClickOnRatingPeriod;



    @FindBy(xpath="//button[text()='Search by previous rating number']")
    public WebElement element_Search_rating_number;
    //To enter some value in the Type a ratin gnumber
    @FindBy(xpath = "//input[@placeholder='Type a rating number']")
    public WebElement element_Type_rating_number;
    //To search rating number from dropdown
    @FindBy(xpath = "//div[@class='slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta']")
    public WebElement element_Rating_dropdown;
    //To click on save
    @FindBy(xpath = "//button[text()='Save']")
    public WebElement element_Save;



    @FindBy(xpath = "//a[@data-page-name='Customer_details']")
    public WebElement element_ClickOnContact;
    @FindBy(xpath = "//p[text()='Customer contacts']/..//input[@inputmode='email']")
    public WebElement element_EnterEmail;
    @FindBy(xpath = "//div/lightning-base-combobox-item")
    public List<WebElement> element_Select_theRoll;

    @FindBy(xpath = "//button[@aria-label='Select role']")
    public WebElement element_Select_role;
    @FindBy(xpath = "//span[@class=\"slds-radio_faux\"]")
    public WebElement element_Approve_Radio_Button;
    @FindBy(xpath = "//small[text()='Send approver invite']")
    public WebElement element_Send_invite;
    @FindBy(xpath = "//input[@placeholder='Type ABN or company name']")
    public WebElement element_Customer_organisation;
    @FindBy(xpath = "(//div[@class=\"slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta\"])[1]")
    public WebElement element_Select_Customer_organisation;


//    salman diya ha
@FindBy(xpath = "//div/div[1]/lightning-radio-group/fieldset/div/span/label")
public List<WebElement> Does_the_premises_Have_OREG_system_YesOrNo;
    @FindBy(xpath = "//span[text()='Renewable electricity']")
    public WebElement element_RenewableElectricity;
    @FindBy(xpath = "//span[@class='slds-file-selector__text slds-medium-show']")
    public WebElement element_clickOn_Upload_File;
    @FindBy(xpath = "//div[text()='If known, what is the total capacity of the OREG system (kW)?']//lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_Total_capacity_OREG_System;
    @FindBy(xpath = "//div[text()='If the OREG system is connected to a Remote Meter Reading System (RMRS), has the meter been validated?']//lightning-radio-group//fieldset//div//span//label")
    public List<WebElement> element_OREG_System_connected_To_Remote;
    @FindBy(xpath = "//div/div[6]/lightning-input/lightning-datepicker/div[1]/div/input")
    public WebElement element_Date_Of_Validation;
    @FindBy(xpath = "//b[text()='total onsite renewable electricity consumption']/../../lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_total_capacity_of_the_OREG_system;
    @FindBy(xpath = "//b[text()='LGCs were created and sold']/../lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_LGCs_were_created_and_sold;
    @FindBy(xpath = "//b[text()='LGCs were voluntarily surrendered']/../lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_LGCs_were_voluntarily_surrendered;
    @FindBy(xpath = "//label[text()='Has the onsite renewable consumption entered on this page been included within a parent meter’s consumption under Grid electricity (e.g., in the case of an embedded network)?']/../lightning-radio-group//label")
    public List<WebElement> element_Has_the_onsite_renewable_consumption_YesOrNo;
    @FindBy(xpath = "//div[text()='What is the total amount of GreenPower ']//lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_total_amount_of_GreenPower_purchased;
    @FindBy(xpath = "//div[text()='What is the quantity of ']//lightning-input//lightning-primitive-input-simple//input")
    public WebElement element_offsite_Large_scale_Generation_Certificates;












    public List<WebElement> getDoes_the_premises_Have_OREG_system_YesOrNo(){return Does_the_premises_Have_OREG_system_YesOrNo;}
    public WebElement getelement_RenewableElectricity(){return element_RenewableElectricity;}
    public WebElement getelement_clickOn_Upload_File(){return element_clickOn_Upload_File;}
    public WebElement getelement_Total_capacity_OREG_System(){return element_Total_capacity_OREG_System;}
    public List<WebElement> getelement_OREG_System_connected_To_Remote(){return element_OREG_System_connected_To_Remote;}
    public WebElement getelement_Date_Of_Validation(){return element_Date_Of_Validation;}
    public WebElement getelement_total_capacity_of_the_OREG_system(){return element_total_capacity_of_the_OREG_system;}
    public WebElement getelement_LGCs_were_created_and_sold(){return element_LGCs_were_created_and_sold;}
    public WebElement getelement_LGCs_were_voluntarily_surrendered(){return element_LGCs_were_voluntarily_surrendered;}
    public List<WebElement> getelement_Has_the_onsite_renewable_consumption_YesOrNo(){return element_Has_the_onsite_renewable_consumption_YesOrNo;}
    public WebElement getelement_total_amount_of_GreenPower_purchased(){return element_total_amount_of_GreenPower_purchased;}
    public WebElement getelement_offsite_Large_scale_Generation_Certificates(){return element_offsite_Large_scale_Generation_Certificates;}






//    yaha khatam hua










    public Common_WeElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> get_element_RatingDetails_Type() {
        return element_RatingDetails_Type;
    }
    public List<WebElement> get_element_Select_shopping_centre_size() {
        return element_Select_shopping_centre_size;
    }
    public List<WebElement> get_element_Is_the_shopping_centre_single_or_multi_storey() {
        return element_Is_the_shopping_centre_single_or_multi_storey;
    }
    public WebElement get_element_StartingDate() {
        return element_StartingDate;
    }
    public WebElement getelement_SaveButton() {
        return element_SaveButton;
    }
    public WebElement getelement_ClickOnRatingPeriod() {
        return element_ClickOnRatingPeriod;
    }
    public WebElement getelement_ClickOnPremises() {
        return element_ClickOnPremises;
    }
    public  WebElement getelement_ClickOnGridElect(){
        return element_ClickOnGridElect;
    }
    public  WebElement getelement_ClickOnGasAndLPG(){
        return element_ClickOnGasAndLPG;
    }
    public  WebElement getelement_ClickOnDiesel(){
        return element_ClickOnDiesel;
    }
    public  WebElement getelement_ClickOnWaternav(){
        return element_ClickOnWaternav;
    }
    public  WebElement getelement_ClickOnReviewSummary(){
        return element_ClickOnReviewSummary;
    }
    public  WebElement getelement_CertificationProcess(){
        return element_CertificationProcess;
    }
    public WebElement get_element_Search_rating_number() {
        return element_Search_rating_number;
    }
    public WebElement get_element_Type_rating_number() {
        return element_Type_rating_number;
    }
    public WebElement get_element_Rating_dropdown() {
        return element_Rating_dropdown;
    }
    public WebElement get_element_Save(){return element_Save;}
    public WebElement getelement_ClickOnContact() {
        return element_ClickOnContact;
    }
    public WebElement getelement_EnterEmail() {
        return element_EnterEmail;
    }

    public List<WebElement> get_element_Select_theRoll() {
        return element_Select_theRoll;
    }

    public WebElement get_element_Select_role(){
        return element_Select_role;
    }
    public WebElement get_element_Approve_Radio_Button(){return element_Approve_Radio_Button;}
    public WebElement get_element_Send_invite(){return element_Send_invite;}
    public WebElement get_element_Customer_organisation(){return element_Customer_organisation;}
    public WebElement get_element_Select_Customer_organisation(){return element_Select_Customer_organisation;}

}



