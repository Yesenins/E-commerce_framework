package steps;

import org.openqa.selenium.WebDriver;
import pages.HeaderPage;
import pages.HomePage;
import pages.ShoppingCartPage;
import pages.modals.LoginModalPage;
import pages.modals.PasswordModalPage;
import pages.modals.RegistrationModalPage;

public class BaseSteps {
    WebDriver driver;
    LoginModalPage loginModalPage;
    PasswordModalPage passwordModalPage;
    RegistrationModalPage registrationModalPage;
    HeaderPage headerPage;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;

    public BaseSteps (WebDriver driver) {
        loginModalPage = new LoginModalPage(driver);
        passwordModalPage = new PasswordModalPage(driver);
        registrationModalPage = new RegistrationModalPage(driver);
        headerPage = new HeaderPage(driver);
        homePage = new HomePage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

    }
}
