package pages;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.modals.ShoppingCartProductModalPage;
import utils.WaitUtils;

public class ShoppingCartPage extends HeaderPage {

    protected final String CART_AREA = "//*[@id=\"cart\"]";
    protected final String PRODUCT_NAME = "_title_vz3xt_24";
    protected final String PRODUCTS_DESCRIPTION = "//*[@class='_image_57380_33']";
    protected final String QUANTITY_OF_PRODUCTS = "//*[contains(text(),'товара')]";


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ShoppingCartPage isPageLoaded() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(CART_AREA));
        return this;
    }

    public ShoppingCartProductModalPage goToProductDescription() {
        Button productDescription = new Button(PRODUCTS_DESCRIPTION, "productDescription", driver);
        productDescription.actionClickOn();
        return new ShoppingCartProductModalPage(driver);
    }

    public String getQuantityOfProducts() {
        String quantity = driver.findElement(By.xpath(QUANTITY_OF_PRODUCTS)).getText();
        return quantity.replace(" товара", "");
    }
}
