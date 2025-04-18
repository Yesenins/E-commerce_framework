package tests;

import constants.IConstants;
import drivers.BrowserTypes;
import drivers.WebDriverFactory;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.*;

@Data
public class BaseTest implements IConstants {
    WebDriver driver;
    LoginSteps loginSteps;
    RegistrationSteps registrationSteps;
    SearchSteps searchSteps;
    ShoppingCartSteps shoppingCartSteps;
    FavoritesSteps favoritesSteps;

    @BeforeMethod
    public void setUpDriver() {
        driver = WebDriverFactory.createDriver(BrowserTypes.CHROME);
        initSteps();
    }

    public void initSteps() {
        loginSteps = new LoginSteps(driver);
        registrationSteps = new RegistrationSteps(driver);
        searchSteps = new SearchSteps(driver);
        shoppingCartSteps = new ShoppingCartSteps(driver);
        favoritesSteps = new FavoritesSteps(driver);
    }

    @AfterMethod
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
}
