package tests;

import constants.IConstants;
import drivers.BrowserTypes;
import drivers.WebDriverFactory;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HeaderPage;
import pages.HomePage;
import pages.ShoppingCartPage;
import pages.modals.LoginModalPage;
import pages.modals.PasswordModalPage;
import pages.modals.RegistrationModalPage;
import steps.LoginSteps;
@Data
public class BaseTest implements IConstants {
    WebDriver driver;
    LoginModalPage loginModalPage;
    PasswordModalPage passwordModalPage;
    RegistrationModalPage registrationModalPage;
    HeaderPage headerPage;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    LoginSteps loginSteps;

    @BeforeMethod
    public void setUpDriver() {
        driver = WebDriverFactory.createDriver(BrowserTypes.CHROME);
        initPagesAndSteps();
    }

    public void initPagesAndSteps() {
        loginModalPage = new LoginModalPage(driver);
        passwordModalPage = new PasswordModalPage(driver);
        registrationModalPage = new RegistrationModalPage(driver);
        headerPage = new HeaderPage(driver);
        homePage = new HomePage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        loginSteps = new LoginSteps(driver);
    }

    @AfterMethod
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
}
