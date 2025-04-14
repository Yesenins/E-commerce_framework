package pages.modals;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.ShoppingCartPage;
import utils.WaitUtils;

public class ShoppingCartProductModalPage extends ShoppingCartPage {

    protected final String MODEL_NAME_LOCATOR = "//*[@class='_modelName_mnqvr_21']";
    protected final Button closeModalButton = new Button("//*[@class=\"d-modal__close-button\"]", "closeModal", driver);

    public ShoppingCartProductModalPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartProductModalPage isPageLoaded() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(MODEL_NAME_LOCATOR));
        return this;
    }

    public String getProductName() {
        return driver.findElement(By.xpath(MODEL_NAME_LOCATOR)).getText();
    }

    public ShoppingCartPage closeModal() {
        closeModalButton.actionClickOn();
        return new ShoppingCartPage(driver);
    }
}
