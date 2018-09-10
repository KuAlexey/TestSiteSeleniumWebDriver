package ua.com.qatestlab.prestashopAutomation.HomePage.HomePageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.qatestlab.prestashopAutomation.PageElements;


public class SearchInput extends PageElements {

    public SearchInput(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "ui-autocomplete-input")
    private WebElement searchDress;

    @FindBy(css = "#search_widget > form > button")
    private WebElement submitSearch;

    public void makeSearch(String searchText) {
        searchDress.clear();
        searchDress.sendKeys(searchText);
        submitSearch.submit();
    }

}
