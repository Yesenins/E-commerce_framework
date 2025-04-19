package pages;

import elements.Button;
import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import utils.ActionUtils;
import utils.JSUtils;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProductListPage extends HeaderPage {

    protected final Dropdown filter = new Dropdown(DROPDOWN, "filter", driver);
    protected final Button product = new Button(PRODUCT, "product", driver);
    protected final Button applyButton = new Button(DROPDOWN_APPLY_BUTTON, "applyButton", driver);

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductListPage isPageLoaded() {
        WaitUtils.waitForPageLoaded(driver);
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(BRAND_NAME));
        return this;
    }

    public ProductListPage isPageLoadedAfterFilter() {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(String.format(PRODUCT, 1)));
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(RESET_FILTER_BUTTON));
        return this;
    }

    public ProductPage goToProduct() {
        product.clickOn();
        return new ProductPage(driver);
    }

    protected void goToProductWithName(String locator, int item) {
        String number = Integer.toString(item);
        Button product = new Button(locator, "product", driver);
        int attempts = 0;
        while (attempts < 3) {
            try {
                JSUtils.jsScroll(driver, product.getLocatorWithLabel(number));
                log.info("Scrolled to product with index: {}", number);
                WaitUtils.waitForElementToBeClickable(driver, product.getLocatorWithLabel(number));
                product.getElementWithLabel(number).click();
                log.info("click on : {} ", product.getNameElement());
                return;
            } catch (ElementClickInterceptedException e) {
                log.warn("Attempt {} failed for product {}", attempts + 1, number);
                attempts++;
            }
        }
        throw new RuntimeException("Failed to click product with index " + number + " after 3 attempts");
    }

    public ProductPage goToProductRandom(int item) {
        goToProductWithName(PRODUCT, item);
        return new ProductPage(driver);
    }

    public ProductListPage goToFavoritesRandom(int item) {
        String number = Integer.toString(item);
        try {
            ActionUtils.hoverClickElement(By.xpath(String.format(PRODUCT, number)), By.xpath(FAVORITES), driver);
        } catch (ElementClickInterceptedException e) {
            JSUtils.jsScroll(driver, By.xpath(String.format(PRODUCT, number)));
            log.info("Scrolled to product");
            ActionUtils.hoverClickElement(By.xpath(String.format(PRODUCT, number)), By.xpath(FAVORITES), driver);
        }
        return this;
    }

    public List<String> getProductNames(String locator) {
        List<WebElement> list = driver.findElements(By.xpath(locator));
        List<String> names = new ArrayList<>();
        for(WebElement item : list) {
            names.add(item.getText().toLowerCase());
        }
        return names;
    }

    public ProductListPage filter(String label, String subMenuLabel) {
        WaitUtils.waitForElementToBeClickable(driver, filter.getLocatorWithLabel(label));
        filter.selectFromDropdown(label, DROPDOWN_SUB_MENU, subMenuLabel);
        applyButton.clickOn();
        isPageLoadedAfterFilter();
        return new ProductListPage(driver);
    }

    public List<WebElement> getProductList() {
        return driver.findElements(By.xpath(PRODUCTS_IN_PAGE));
    }

    public int getProductsQuantity() {
        isPageLoaded();
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(PRODUCTS_IN_PAGE));
        int size = driver.findElements(By.xpath(PRODUCTS_IN_PAGE)).size();
        log.info("Quantity of products in page is: {}", size);
        return size;
    }

    public String getSearchErrorMessage() {
        try {
            log.info("Getting error message from search field.");
            return driver.findElement(SEARCH_ERROR_MESSAGE_LOCATOR).getText();
        }
        catch (Exception e) {
            log.error("Failed to get search field error message.", e);
            return "";
        }
    }

    public int checkQuantityOfProducts(int quantity) {
        int productsCount = getProductsQuantity();
        if (quantity > productsCount) {
            log.warn("Requested quantity ({}) exceeds available products ({})", quantity, productsCount);
            quantity = productsCount;
            return quantity;
        } else {
            return quantity;
        }
    }
}
