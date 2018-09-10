package ua.com.qatestlab.prestashopAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ua.com.qatestlab.prestashopAutomation.HomePage.HomePage;
import ua.com.qatestlab.prestashopAutomation.Utility.Log;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    protected WebDriver driver;
    private final String APP_URL = "http://prestashop-automation.qatestlab.com.ua/ru/";

    protected HomePage homePage;

    @BeforeTest
    public void setUp() {
        DOMConfigurator.configure("log4j.xml");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        Log.startTestLog();
        driver.get(APP_URL);
        Log.info("Load url of the site ->(" + APP_URL + ")");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Log.info("Wait for home page load");
        homePage = new HomePage(driver); //loading home page object
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Log.endTestLog();
        }
    }
}
