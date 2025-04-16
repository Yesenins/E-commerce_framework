package steps;

import constants.IConstants;
import org.openqa.selenium.WebDriver;
import pages.*;
import pages.modals.LoginModalPage;
import pages.modals.PasswordModalPage;
import pages.modals.RegistrationModalPage;
import pages.modals.ShoppingCartModalPage;

public class BaseSteps implements IConstants {
    WebDriver driver;
    LoginModalPage loginModalPage;
    PasswordModalPage passwordModalPage;
    RegistrationModalPage registrationModalPage;
    HeaderPage headerPage;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    ProductListPage productListPage;
    ProductPage productPage;
    ShoppingCartModalPage shoppingCartModalPage;
    FavoritesPage favoritesPage;

    public BaseSteps (WebDriver driver) {
        this.driver = driver;
        loginModalPage = new LoginModalPage(driver);
        passwordModalPage = new PasswordModalPage(driver);
        registrationModalPage = new RegistrationModalPage(driver);
        headerPage = new HeaderPage(driver);
        homePage = new HomePage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        productListPage = new ProductListPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartModalPage = new ShoppingCartModalPage(driver);
        favoritesPage = new FavoritesPage(driver);
    }
}
