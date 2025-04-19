package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ActionUtils;
import utils.WaitUtils;

import java.util.List;
@Log4j2
public class FavoritesPage extends HeaderPage {

    protected final String FAVORITES_PRODUCTS = "//*[contains(@class,'_area_552z7_8')]";
    protected final String DELETE_FROM_FAVORITES = "//*[contains(@class, 'icon_heart-catalog-added')]";

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public FavoritesPage isPageLoaded() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(FAVORITES_PRODUCTS));
        return this;
    }

    public int getQuantityOfProducts() {
        return driver.findElements(By.xpath(FAVORITES_PRODUCTS)).size();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(By.xpath(FAVORITES_PRODUCTS));
    }

    public FavoritesPage deleteFromFavorites() {
        if(getQuantityOfProducts() == 0) {
            return this;
        } else {
            int quantityOfProducts = getQuantityOfProducts();
            for (int i =0; i < quantityOfProducts; i++) {
                ActionUtils.hoverClickElement(By.xpath(FAVORITES_PRODUCTS), By.xpath(DELETE_FROM_FAVORITES), driver);
                log.info("Click on heart item");
                driver.navigate().refresh();
            }
            return deleteFromFavorites();
        }
    }
}
