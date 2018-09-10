package ua.com.qatestlab.prestashopAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageElements {

    protected WebDriver driver;

    public PageElements(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
