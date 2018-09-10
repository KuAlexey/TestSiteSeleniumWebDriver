package ua.com.qatestlab.prestashopAutomation.ProductsPage.ProductsPageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements.ProductsPrice;
import ua.com.qatestlab.prestashopAutomation.Utility.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class ActionsWithEachProduct {

    private WebDriver driver;

    private ProductsPrice productPrice;
    private ProductsAmount productsAmount;
    private LinkedList<Double> listForPriceProducts = new LinkedList<Double>(); //list for sort verifying test
    private LinkedList<Boolean> listVerifyCalculatedValue = new LinkedList<Boolean>();
    private boolean safeCondition = false; //helping boolean for containing false condition
    private boolean textPercentageSymbol = true; //main boolean for testing

    public ActionsWithEachProduct(WebDriver driver) {
        this.driver = driver;
        productPrice = new ProductsPrice(driver);
        productsAmount = new ProductsAmount(driver);
    }

    public boolean getTextPercentageSymbol() {
        return textPercentageSymbol;
    }

    public void addSortedPrice(Double sortedPrice) {
        listForPriceProducts.add(sortedPrice);
    }

    public void addConditionCalculatedValue(Boolean condition) {
        listVerifyCalculatedValue.add(condition);
    }

    public LinkedList<Double> getListOfPrice() {
        return listForPriceProducts;
    }

    public LinkedList<Boolean> getListOfCondition() {
        return listVerifyCalculatedValue;
    }


    private double parseToDouble(WebElement webElement) {
        String priceText = webElement.getText();
        String priceTextForDouble = priceText.replaceAll(",", ".");
        return Double.parseDouble(priceTextForDouble.substring(0, priceTextForDouble.length() - 2));
    }

    private By getByElement(WebDriver driver, int iteration, String attachment) {
        return By.cssSelector("#js-product-list > div.products.row > article:nth-child(" + iteration + ") > div >" +
                " div.product-description > div > " + attachment); //price with discount
    }

    public void doOperationsWithEachProduct(WebDriver driver) {
        for (int i = 1; i <= productsAmount.getSizeListOfProducts(); i++) {
            List<WebElement> spans = driver.findElements(getByElement(driver, i, "span")); //getting spans

            //WebElement of product price with discount (for calculation)
            WebElement priceProduct = driver.findElement(getByElement(driver, i, "span.price"));

            //Double value of this element
            double price = parseToDouble(priceProduct);

            if (spans.size() > 1) {
                WebElement regularPriceProduct = driver.findElement(getByElement(driver, i, "span.regular-price"));
                double regularPrice = parseToDouble(regularPriceProduct);//parsing to double

                addSortedPrice(regularPrice); //add sorted price to the list

                WebElement percentageValue = driver.findElement(getByElement(driver, i, "span.discount-percentage"));
                verifyConditionOfTextPercentageSymbol(percentageValue);


                if (!safeCondition) { //fetching violation of the condition
                    textPercentageSymbol = false;
                }

                calculateValueForVerifying(percentageValue, regularPrice, price); //calculate value

            } else {
                addSortedPrice(price); //proceed if product don't have discount
            }
        }
    }

    private void verifyConditionOfTextPercentageSymbol(WebElement webElement) {
        char symbol = productPrice.getCurrencySymbol(webElement);
        if (symbol == '%') {
            safeCondition = true;
        }
    }

    private void calculateValueForVerifying(WebElement discountPercentage, double regularPrice, double price) {
        boolean areValuesEqual = false;
        String percentageValueText = discountPercentage.getText();
        int percentageValue = Integer.parseInt(percentageValueText.substring(1, percentageValueText.length() - 1));

        BigDecimal priceValueAccurate = new BigDecimal(price).setScale(2, RoundingMode.CEILING);

        BigDecimal calculatedValueAccurate = new BigDecimal(regularPrice * (100 - percentageValue) / 100)
                .setScale(2, RoundingMode.CEILING); //calculating

        if (calculatedValueAccurate.equals(priceValueAccurate)) {
            areValuesEqual = true;
        }
        addConditionCalculatedValue(areValuesEqual);
    }
}
