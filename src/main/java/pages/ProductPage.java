package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class ProductPage extends HeaderPage {

    protected final String PRODUCT_INFORMATION = "//*[normalize-space()='О товаре']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductPage isPageOpened() {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(PRODUCT_INFORMATION));
        return this;
    }
}
