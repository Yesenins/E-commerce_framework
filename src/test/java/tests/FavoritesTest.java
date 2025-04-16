package tests;

import objects.Gender;
import org.testng.annotations.Test;

public class FavoritesTest extends BaseTest {

    @Test
    public void addProductToFavoritesAndCheckQuantity() {
        loginSteps
                .loginAndCheckPageIsOpened(PropertyReader.getProperty(EMAIL),PropertyReader.getProperty(PASSWORD));
        favoritesSteps
                .addProductsToFavorites(Gender.MEN, "Одежда", "Брюки", 2)
                .goToFavorites()
                .checkQuantityOfProducts(2)
                .deleteAllProductsInFavorites();
    }
}
