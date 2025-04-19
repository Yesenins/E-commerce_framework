package steps;

import io.qameta.allure.Step;
import objects.Gender;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FavoritesSteps extends BaseSteps {

    public FavoritesSteps(WebDriver driver) {
        super(driver);
    }

    @Step
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

    @Step
    public FavoritesSteps goToFavorites() {
        headerPage
                .goToFavoritesPage()
                .isPageLoaded();
        return this;
    }

    @Step
    public FavoritesSteps checkQuantityOfProducts(int quantity) {
        Assert.assertEquals(favoritesPage.getQuantityOfProducts(), quantity);
        return this;
    }

    @Step
    public FavoritesSteps deleteAllProductsInFavorites() {
        favoritesPage.deleteFromFavorites();
        return this;
    }
}
