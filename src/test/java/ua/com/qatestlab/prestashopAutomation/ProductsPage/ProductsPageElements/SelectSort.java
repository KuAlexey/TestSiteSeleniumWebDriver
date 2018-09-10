package ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.qatestlab.prestashopAutomation.PageElements;

public class SelectSort extends PageElements {

    public SelectSort(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "#js-product-list-top > div:nth-child(2) > div > div")
    private WebElement selectSort;

    @FindBy(css = "#js-product-list-top > div:nth-child(2) > div > div > div > a:nth-child(5)")
    private WebElement selectOptionSort;

    public WebElement getSelectSort(){
        return selectSort;
    }

    public WebElement getSelectOptionSort(){
        return selectOptionSort;
    }


}
