package pages;

import elements.Button;
import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.modals.ShoppingCartModalPage;
import utils.WaitUtils;

import java.util.List;
@Log4j2
public class ProductPage extends HeaderPage {

    protected final String PRODUCT_INFORMATION = "//*[normalize-space()='О товаре']";
    protected final String SIZE_SUB_MENU = "//*[contains(@class, '_colspan_14ecl_152')]";
    protected final Dropdown size = new Dropdown("//*[@class=\"_select_14ecl_10\"]", "size", driver);
    protected final Button addToCartButton = new Button("//*[@aria-label=\"Добавить в корзину\"]", "addToCartButton", driver);
    protected final String SIZE_TABLE = "//*[contains(@class, '_root_sez4k_2')]/div[contains(@class, '_title_sez4k_22')]";
    protected final String MODEL_NAME_LOCATOR = "//*[@class='_modelName_mnqvr_21']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductPage isPageLoaded() {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(PRODUCT_INFORMATION));
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(MODEL_NAME_LOCATOR));
        return this;
    }

    public ProductPage chooseSize(String mySize) {
        isPageLoaded();
        if(isElementPresent(size.getLocator())) {
            chooseSizeFromDropdown(mySize);
            return this;
        } else if (isElementPresent(By.xpath(SIZE_TABLE))) {
            chooseSizeFromTable(mySize);
            return this;
        }
        return this;
    }

    public List<WebElement> getAllSizeInDropdown() {
        size.actionClickOn();
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(SIZE_SUB_MENU));
        List<WebElement> sizes = driver.findElements(By.xpath(SIZE_SUB_MENU));
        log.info("Found sizes in the dropdown: {}", sizes.size());
        return sizes;
    }

    public List<WebElement> getAllSizeInTable() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(SIZE_TABLE));
        List<WebElement> sizes = driver.findElements(By.xpath(SIZE_TABLE));
        log.info("Found sizes in the table: {}", sizes.size());
        return sizes;
    }

    public ProductPage chooseSizeFromDropdown(String mySize) {
        List<WebElement> size = getAllSizeInDropdown();
        getSize(size, mySize, "_colspanDisabled_14ecl_171");
        return this;
    }


    public ProductPage chooseSizeFromTable(String mySize) {
        List<WebElement> size = getAllSizeInTable();
        getSize(size, mySize, "_disabled_sez4k_36");
        return this;
    }

    public ProductPage getSize(List<WebElement> sizes, String mySize, String attribute) {
        if (sizes.isEmpty()) {
            log.warn("The size list is empty");
            return this;
        }
        for(WebElement item : sizes) {
            String fullClassAttribute = item.getDomAttribute("class");
            if(item.getText().contains(mySize)) {
                if(fullClassAttribute.contains(attribute)) {
                    log.info("Size {} is not available", mySize);
                    return this;
                } else {
                    WaitUtils.waitForElementToBeClickable(driver, item);
                    item.click();
                    log.info("Size {} selected", mySize);
                    return this;
                }
            }
        }
        log.warn("Size {} not found in the list", mySize);
        return this;
    }

    public String getProductName() {
        return driver.findElement(By.xpath(MODEL_NAME_LOCATOR)).getText();
    }

    public ShoppingCartModalPage goToShoppingCart() {
        addToCartButton.actionClickOn();
        return new ShoppingCartModalPage(driver);
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
