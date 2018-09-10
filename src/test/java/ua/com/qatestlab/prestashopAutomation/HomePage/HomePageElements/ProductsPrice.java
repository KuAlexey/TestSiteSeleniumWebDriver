package ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.com.qatestlab.prestashopAutomation.PageElements;

import java.util.List;

public class ProductsPrice extends PageElements {

    public ProductsPrice(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span.expand-more._gray-darker.hidden-sm-down")
    private WebElement productPriceInHead;

    @FindBy(css = "span.price")
    private List<WebElement> productsPrice;

    private By optionUSDinput = By.cssSelector("#_desktop_currency_selector > div > ul > li:nth-child(3) > a");


    public WebElement getProductsPriceInHead() {
        return productPriceInHead;
    }

    public List<WebElement> getProductsPrice() {
        return productsPrice;
    }

    public char getCurrencySymbol(WebElement webElement) {
        String priceText = webElement.getText();
        return priceText.charAt(priceText.length() - 1);
    }

    public void chooseUSD() {
        productPriceInHead.click();
        WebElement optionUSDinputElement = (new WebDriverWait(driver, 60).
                until(ExpectedConditions.visibilityOfElementLocated(optionUSDinput)));
        optionUSDinputElement.click();
    }

}
