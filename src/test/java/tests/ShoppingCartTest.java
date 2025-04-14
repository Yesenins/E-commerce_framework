package tests;

import objects.Gender;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void addProductToShoppingCartTest() {
        shoppingCartSteps
                .openPage()
                .addProductToCartAndCheckProductName(Gender.MEN,"Обувь", "Ботинки", "42");
    }

    @Test(description = "Check that the cart page shows the added quantity of goods")
    public void displayTheNumberOfGoodsOnCartAddTest() {
        shoppingCartSteps
                .openPage()
                .addProductsToCart(Gender.MEN,"Обувь", "Ботинки", "42", 3)
                .checkQuantityOfAddedProducts(3);
    }
}
