package tests;

import objects.Gender;
import org.testng.annotations.Test;
import retryAnalyzer.RetryAnalyzer;

public class ShoppingCartTest extends BaseTest {

    @Test(description = "adding an item to the cart and checking if it is displayed in the cart")
    public void addProductToShoppingCartTest() {
        shoppingCartSteps
                .openPage()
                .addProductToCartAndCheckProductName(Gender.MEN,"Обувь", "Ботинки", "42");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Check that the cart page shows the added quantity of goods")
    public void displayTheNumberOfGoodsOnCartAddTest() {
        shoppingCartSteps
                .openPage()
                .chooseGenderAndOpenProductsList(Gender.MEN,"Обувь", "Ботинки")
                .addProductsToCart("42", 3)
                .goToShoppingCart()
                .checkQuantityOfAddedProducts(3);
    }
}
