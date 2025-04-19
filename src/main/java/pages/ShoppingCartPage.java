package pages;

import elements.Button;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.modals.ShoppingCartProductModalPage;
import utils.WaitUtils;

import java.util.List;
@Log4j2
public class ShoppingCartPage extends HeaderPage {

    protected final String CART_AREA = "//*[@id=\"cart\"]";
    protected final String PRODUCTS_DESCRIPTION = "//*[@class='_image_57380_33']";
    protected final String CURRENT_PRICE = "//*[@class = '_currentPrice_19ibk_50']";
    protected final String DISCOUNT_PRICE = "//*[@class = '_discountPrice_19ibk_54']/span";
    protected final String FULL_PRICE = "//*[@class = 'ui-cart-total-price']";


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

    public int getQuantityOfProducts() {
        int allQuantity = driver.findElements(By.xpath("//*[contains(@class, 'vue-recycle-scroller__item-view')]")).size();
        log.info("Quantity of all products: {}", allQuantity);
        return allQuantity;
    }

    public String getPrice() {
        double currentPrice = 0;
        double discountPrice = 0;
        List<WebElement> currentPriceElements = driver.findElements(By.xpath(CURRENT_PRICE));
        List<WebElement> discountPriceElements = driver.findElements(By.xpath(DISCOUNT_PRICE));
        if(!discountPriceElements.isEmpty()) {
            for(WebElement element: discountPriceElements){
                discountPrice += Double.parseDouble(element.getText().replace(" р.", ""));
            }
            log.info("Discount price: {}", discountPrice);
        }
        if (!currentPriceElements.isEmpty()) {
            for(WebElement item : currentPriceElements) {
                currentPrice += Double.parseDouble(item.getText().replace(" р.", ""));
            }
            log.info("Current price: {}", currentPrice);
        }
        double fullPrice = discountPrice + currentPrice;
        log.info("Full price: {}", fullPrice);
        return String.format("%.2f", fullPrice);
    }

    public String getFullPrice() {
        String fullPrice = driver.findElement(By.xpath(FULL_PRICE)).getText();
        log.info("Actual full price: {}", fullPrice);
        String fullPriceWithoutSpaces = fullPrice.replace(" ", "").replace(",", ".").replace("р.", "");
        log.info("Actual full price: {}", fullPriceWithoutSpaces);
        double parsedPrice = Double.parseDouble(fullPriceWithoutSpaces);
        String formattedPrice = String.format("%.2f", parsedPrice);
        log.info("Actual full price: {}", formattedPrice);
        return formattedPrice;
    }
}
