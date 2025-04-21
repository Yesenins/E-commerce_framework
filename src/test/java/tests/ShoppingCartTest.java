package tests;

import objects.Gender;
import org.testng.annotations.Test;
import retryAnalyzer.RetryAnalyzer;

public class ShoppingCartTest extends BaseTest {

    @Test(groups = {"smoke"}, description = "adding an item to the cart and checking if it is displayed in the cart")
    public void addProductToShoppingCartTest() {
        shoppingCartSteps
                .openPage()
                .addProductToCartAndCheckProductName(Gender.MEN,"Обувь", "Ботинки", "Размер", "38");
    }

    @Test(groups = {"smoke"}, retryAnalyzer = RetryAnalyzer.class, description = "Check that the cart page shows the added quantity of goods")
    public void displayTheNumberOfGoodsOnCartAddTest() {
        shoppingCartSteps
                .openPage()
                .chooseGenderAndOpenProductsList(Gender.WOMEN,"Обувь", "Ботинки")
                .filter("Размер", "38")
                .addProductsToCart("38", 3)
                .goToShoppingCart()
                .checkQuantityOfAddedProducts(3);
    }
}
