package pages.modals;

import elements.Button;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;
import pages.ShoppingCartPage;
import utils.WaitUtils;

public class ShoppingCartModalPage extends ProductPage {

    protected final Button goToShoppingCartButton = new Button("//*[@id=\"modals\"]//*[@href=\"/checkout/cart/\"]", "goToShoppingCart", driver);
    protected final Button continueShoppingButton = new Button("//*[normalize-space()='Продолжить покупки']", "continueShopping", driver);

    public ShoppingCartModalPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartModalPage isPageLoaded() {
        WaitUtils.waitForElementToBeClickable(driver, goToShoppingCartButton.getLocator());
        return this;
    }

    public ShoppingCartPage goToCart() {
        isPageLoaded();
        goToShoppingCartButton.clickOn();
        return new ShoppingCartPage(driver);
    }

    public ProductPage continueShopping() {
        isPageLoaded();
        continueShoppingButton.clickOn();
        return new ProductPage(driver);
    }
}
