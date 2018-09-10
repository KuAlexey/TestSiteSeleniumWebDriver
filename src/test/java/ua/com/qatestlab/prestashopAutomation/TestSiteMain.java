package ua.com.qatestlab.prestashopAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPage;

/**
 * Created by Алексей on 04.09.2018.
 */
public class TestSiteMain extends FunctionalTest {

    ProductsPage productsPage;

    @Test
    public void testAreCurrencySymbolsEqual() {
        for (char currencySymbol : homePage.getListOfCurrencySymbols()) {            // Home Page object was initialized
            Assert.assertEquals(currencySymbol, homePage.getCurrencySymbolInHead()); // in the class FunctionalTest
        }
    }

    @Test(priority = 1)
    public void chooseUSDcurrency() {
        homePage.chooseUSDoption();
    }

    @Test(priority = 2)
    private void searchProducts() {
        productsPage = homePage.makeSearchProducts("dress"); //loading search page object
    }

    @Test(dependsOnMethods = {"searchProducts"}, priority = 3) //making chain of test methods
    public void testAreNumbersOfAmountEqual() {
        int exactNumberOfAmount = productsPage.getExactNumberOfAmount();
        int sizeListProducts = productsPage.getSizeListProducts();
        Assert.assertEquals(exactNumberOfAmount, sizeListProducts);
    }

    @Test(dependsOnMethods = {"searchProducts"}, priority = 4)
    public void testAreCurrencySymbolsEqualToDollar() {
        Assert.assertTrue(productsPage.areCurrencySymbolEqualToDollar());
    }

    @Test(dependsOnMethods = {"searchProducts"}, priority = 5)
    public void selectSortingInput() {
        productsPage.selectSorting(driver);
    }

    @Test(dependsOnMethods = {"searchProducts"}, priority = 6)
    public void proceedMainActions() {
        productsPage.proceedChainActions(driver);
    }

    @Test(dependsOnMethods = {"searchProducts", "proceedMainActions"}, priority = 7)
    //produce test method due to actions that
    public void testArePricesSorted() {                                             //have been made before
        Assert.assertTrue(productsPage.arePricesSorted());
    }


    @Test(dependsOnMethods = {"searchProducts", "proceedMainActions"}, priority = 7)
    public void testIsPriceValueEqualToCalculated() {
        Assert.assertTrue(productsPage.isPriceValueEqualToCalculated());
    }

    @Test(dependsOnMethods = {"searchProducts", "proceedMainActions"}, priority = 7)
    public void testIsTextPercentageSymbol() {
        Assert.assertTrue(productsPage.isTextPercentageSymbol());
    }
}
