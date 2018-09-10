package ua.com.qatestlab.prestashopAutomation.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements.ProductsPrice;
import ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements.ActionsWithEachProduct;
import ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements.ProductsAmount;
import ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements.SelectSort;
import ua.com.qatestlab.prestashopAutomation.Utility.Log;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


public class ProductsPage {

    private WebDriver driver;

    private ProductsAmount productsAmount;
    private SelectSort sort;
    private ActionsWithEachProduct actionWithEachProduct;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        productsAmount = new ProductsAmount(driver);
        sort = new SelectSort(driver);
        actionWithEachProduct = new ActionsWithEachProduct(driver);
    }

    public int getExactNumberOfAmount() {
        Log.info("Get the amount of the products that is showed on the page");
        return productsAmount.getNumberOfProductsAmount(productsAmount.getVerifyAmount());
    }

    public int getSizeListProducts() {
        Log.info("Get the real amount of the products on the page");
        return productsAmount.getSizeListOfProducts();
    }

    public boolean areCurrencySymbolEqualToDollar() {
        Log.info("Getting char of the currency symbol of price products and compare them it with the symbol that must" +
                " be -> ($)");
        return productsAmount.getListOfProducts().stream().allMatch(a -> {
            char currSymbol = new ProductsPrice(driver).getCurrencySymbol(a); //use method getting char from WebElement
            return currSymbol == '$';
        });
    }

    public void selectSorting(WebDriver driver) {
        new Actions(driver).click(sort.getSelectSort()).click(sort.getSelectOptionSort()).build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Log.info("Select sorting price for products");
    }

    public void proceedChainActions(WebDriver driver) {
        Log.info("Make operations with the price of products which will help producing the tests");
        actionWithEachProduct.doOperationsWithEachProduct(driver);
    }


    public boolean arePricesSorted() {
        boolean productsAreSorted = false;
        LinkedList<Double> listOfPriceProduct = actionWithEachProduct.getListOfPrice();
        for (int i = 1; i < listOfPriceProduct.size(); i++) {
            if (listOfPriceProduct.get(i - 1) >= listOfPriceProduct.get(i)) {
                productsAreSorted = true;
            }
        }
        Log.info("Getting price of products after sorting and making sure that they are sorted ASC");
        return productsAreSorted;
    }


    public boolean isTextPercentageSymbol() {
        Log.info("Making sure that discount number include ->(%) symbol");
        return actionWithEachProduct.getTextPercentageSymbol();
    }

    public boolean isPriceValueEqualToCalculated() {
        LinkedList<Boolean> linkedList = actionWithEachProduct.getListOfCondition();
        Log.info("Сhecking that the price before and after the discount coincides with the specified discount amount");
        return linkedList.stream().allMatch(booleanElement -> {
            String string = booleanElement.toString();
            return string.equals("true");
        });
    }
}
