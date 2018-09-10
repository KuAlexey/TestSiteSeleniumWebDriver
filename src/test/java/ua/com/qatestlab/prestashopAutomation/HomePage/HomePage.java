package ua.com.qatestlab.prestashopAutomation.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements.ProductsPrice;
import ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements.SearchInput;
import ua.com.qatestlab.prestashopAutomation.Utility.Log;
import ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomePage {

    private WebDriver driver;
    private ProductsPrice productsPrice;
    private SearchInput search;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        productsPrice = new ProductsPrice(driver);
        search  = new SearchInput (driver);
    }

    public char getCurrencySymbolInHead() {
        return productsPrice.getCurrencySymbol(productsPrice.getProductsPriceInHead());
    }

    public List<Character> getListOfCurrencySymbols() {
        List<Character> list = productsPrice.getProductsPrice().stream().map(product -> productsPrice.getCurrencySymbol(product)).
                collect(Collectors.toList());
        Log.info("Get the currency symbols ->("+list+") of all of the product which are showed on the site page");
        return list;

    }

    public ProductsPage makeSearchProducts(String searchText) {
        search.makeSearch(searchText);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Log.info("Search '"+ searchText +"' using search bar input");
        return new ProductsPage(driver);
    }

    public void chooseUSDoption(){
        productsPrice.chooseUSD();
        Log.info("Choose the currency symbol -> ($) for the products");
    }
}






