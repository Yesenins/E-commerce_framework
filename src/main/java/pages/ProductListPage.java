package pages;

import elements.Button;
import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public ProductListPage isPageOpenedAfterFilter() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(RESET_FILTER_BUTTON));
        return this;
    }

    public ProductPage goToProduct() {
        product.clickOn();
        return new ProductPage(driver);
    }

    public void goToProductWithRandom(String locator) {
        Random random = new Random();
        int item = random.nextInt(1, 25);
        String number = Integer.toString(item);
        Button product = new Button(locator, "product", driver);
        try {
            WaitUtils.waitForElementToBeClickable(driver, product.getLocatorWithLabel(number));
            product.getElementWithLabel(item + "").click();
        } catch (ElementClickInterceptedException e) {
            log.info("JS scroll: {} ", e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product.getElementWithLabel(number));
            WaitUtils.waitForElementToBeClickable(driver, product.getLocatorWithLabel(number));
            log.info("click on : {} ", product);
            product.getElementWithLabel(item + "").click();
        }
    }

    public ProductPage goToProductRandom() {
        goToProductWithRandom(PRODUCT);
        return new ProductPage(driver);
    }

    public ProductListPage goToFavoritesRandom() {
        goToProductWithRandom(FAVORITES);
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
        return this;
    }

    public List<WebElement> getProductList() {
        return driver.findElements(By.xpath(PRODUCT));
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
}
