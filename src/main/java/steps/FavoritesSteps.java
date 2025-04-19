package steps;

import io.qameta.allure.Step;
import objects.Gender;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FavoritesSteps extends BaseSteps {

    public FavoritesSteps(WebDriver driver) {
        super(driver);
    }

    @Step("add products to favorites")
    public FavoritesSteps addProductsToFavorites(Gender gender, String locatorLabel, String menuLabel, int quantity) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded();
        for(int i = 1; i <= quantity; i++) {
            productListPage
                    .goToFavoritesRandom(i*2)
                    .isPageLoaded();
        }
        return this;
    }

    @Step("open favorites page")
    public FavoritesSteps goToFavorites() {
        headerPage
                .goToFavoritesPage()
                .isPageLoaded();
        return this;
    }

    @Step("check quantity of products")
    public FavoritesSteps checkQuantityOfProducts(int quantity) {
        Assert.assertEquals(favoritesPage.getQuantityOfProducts(), quantity);
        return this;
    }

    @Step("deleting all items from favorites")
    public FavoritesSteps deleteAllProductsInFavorites() {
        favoritesPage.deleteFromFavorites();
        return this;
    }
}
