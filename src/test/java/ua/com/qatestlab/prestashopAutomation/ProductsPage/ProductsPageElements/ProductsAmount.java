package ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.qatestlab.prestashopAutomation.PageElements;

import java.util.List;

public class ProductsAmount extends PageElements {

    public ProductsAmount(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "#js-product-list > div.products.row > article > div > div.product-description > div > span.price")
    private List<WebElement> listOfProducts;

    @FindBy(css = "#js-product-list-top > div.col-md-6.hidden-sm-down.total-products > p")
    private WebElement verifyAmount;

    public int getNumberOfProductsAmount(WebElement webElement) {
        String verifyAmountString = webElement.getText();
        return Character.getNumericValue(verifyAmountString.charAt(verifyAmountString.length() - 2));
    }

    public WebElement getVerifyAmount() {
        return verifyAmount;
    }

    public List<WebElement> getListOfProducts() {
        return listOfProducts;
    }

    public int getSizeListOfProducts() {
        return listOfProducts.size();
    }

}

