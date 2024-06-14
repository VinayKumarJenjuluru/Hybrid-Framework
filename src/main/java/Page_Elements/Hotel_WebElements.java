package Page_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class Hotel_WebElements {
    @FindBy(xpath = "//button[@class='slds-combobox__input slds-input_faux']")
    public WebElement element_ClickOnHotelType;
    @FindBy(xpath = "//div//lightning-base-combobox-item")
    public List<WebElement> element_SelectHotelFromDropDown;

    @FindBy(xpath = "//div[@class='slds-m-top_small slds-col']//button")
    public List<WebElement> element_SelectPrefill;
    @FindBy(xpath = "//span[@class='slds-form-element__label']")
    public List<WebElement> element_ProvidesServicesToTheRatedHotel;

    @FindBy(xpath="//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Hotel Quality']")
    public WebElement element_clickOnHotelQuality;
    public Hotel_WebElements(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }


    public WebElement getelement_ClickOnHotelType() {
        return element_ClickOnHotelType;
    }
    public List<WebElement> getelement_SelectHotelFromDropDown() {
        return element_SelectHotelFromDropDown;
    }
    public List<WebElement> getelement_SelectPrefill() {return element_SelectPrefill;}
    public List<WebElement> getelement_ProvidesServicesToTheRatedHotel() {return element_ProvidesServicesToTheRatedHotel;}
    public WebElement getelement_clickOnHotelQuality(){return element_clickOnHotelQuality;}

    // Hotel rating xpaths

    @FindBy(xpath = "//div[@class='slds-m-top_small slds-col']//button")
    public List<WebElement> element_HotelRatingselectPrefil;
    @FindBy(xpath = "//div[@class='slds-combobox_container']//button")
    public WebElement element_SelectHotelRating;
    @FindBy(xpath = "//div[@class='slds-combobox_container']//button/../../div[2]//following-sibling::lightning-base-combobox-item")
    public List<WebElement> element_SelectHotelRatingDropDown;
    @FindBy(xpath = "//button[@class='slds-button slds-button_brand slds-float_right slds-m-left_x-small']")
    public WebElement element_ClickOnSave;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Facilities']")
    public WebElement element_ClickOnFacilities;

    public List<WebElement> getelement_HotelRatingselectPrefil() {return element_HotelRatingselectPrefil;}
    public WebElement getelement_SelectHotelRating(){return element_SelectHotelRating;}
    public List<WebElement> getelement_SelectHotelRatingDropDown() {return element_SelectHotelRatingDropDown;}
    public WebElement getelement_ClickOnSave(){return element_ClickOnSave;}
    public WebElement getlement_ClickOnFacilities(){return element_ClickOnFacilities;}


//--------------------------------------------------------------------------------
    @FindBy(xpath = "//div[@class='slds-m-top_small slds-col']//button")
    public WebElement element_SelectPrefil;
    @FindBy(xpath = "//input[@placeholder='Days laundry operational']")
    public WebElement element_LaundryOperationalDays;
    @FindBy(xpath = "//span[@class='slds-has-flexi-truncate menuItemClick slds-m-left_small menuContentHiddenOnCollapse'][text()='Size and servicing']")
    public WebElement element_clickOnSizeAndServicing1;
    @FindBy(xpath = "//div/div[1]/lightning-radio-group/fieldset/div/span/label")
    public List<WebElement> element_heatedpools_yesOrNo;
    @FindBy(xpath = "//div/div[3]/lightning-radio-group/fieldset/div/span/label")
    public List<WebElement> element_functionRooms_YesOrNo;
    @FindBy(xpath = "//span[@class='slds-file-selector__text slds-medium-show']")
    public WebElement element_Upload_FIle;
    public WebElement getelement_clickOnSizeAndServicing() {
        return element_clickOnSizeAndServicing1;}

    public WebElement getelement_SelectPrefil(){return element_SelectPrefil;}
    public WebElement getelement_LaundryOperationalDays(){return element_LaundryOperationalDays;}
    public List<WebElement> getelement_heatedpools_yesOrNo() {return element_heatedpools_yesOrNo;}
    public List<WebElement> getelement_functionRooms_YesOrNo() {return element_functionRooms_YesOrNo;}
    public WebElement getelement_Upload_FIle(){return element_Upload_FIle;}

    //=======================================================================================================
    @FindBy(xpath = "//label[text()='Does the premises have an Onsite Renewable Energy Generation (OREG) system?']/../lightning-radio-group/fieldset/div/span/label")
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
    public WebElement element_total_onsite_renewable_electricity_consumption;
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
    public WebElement getelement_total_onsite_renewable_electricity_consumption(){return element_total_onsite_renewable_electricity_consumption;}
    public WebElement getelement_LGCs_were_created_and_sold(){return element_LGCs_were_created_and_sold;}
    public WebElement getelement_LGCs_were_voluntarily_surrendered(){return element_LGCs_were_voluntarily_surrendered;}
    public List<WebElement> getelement_Has_the_onsite_renewable_consumption_YesOrNo(){return element_Has_the_onsite_renewable_consumption_YesOrNo;}
    public WebElement getelement_total_amount_of_GreenPower_purchased(){return element_total_amount_of_GreenPower_purchased;}
    public WebElement getelement_offsite_Large_scale_Generation_Certificates(){return element_offsite_Large_scale_Generation_Certificates;}

    }

