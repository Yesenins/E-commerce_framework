package tests;

import objects.Gender;
import org.testng.annotations.Test;

public class FavoritesTest extends BaseTest {

    @Test(description = "adding several products to favorites and checking their quantity in the cart, clearing favorites")
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
