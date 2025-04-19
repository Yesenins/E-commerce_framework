package tests;

import objects.Gender;
import org.testng.annotations.Test;
import retryAnalyzer.RetryAnalyzer;

public class FullFlowTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class, description = "add multiple items to cart, search by item name, add to cart, check if all items are displayed in cart, check price")
    public void fullUserFlowFromAddProductToCheckoutTest() {
        shoppingCartSteps
                .openPage()
                .chooseGenderAndOpenProductsList(Gender.MEN, "Одежда", "Верхняя одежда")
                .filter("Размер", "52")
                .addProductsToCart("52", 3);
        searchSteps
                .search("рюкзак");
        shoppingCartSteps
                .isProductListPageLoaded()
                .addProductsToCart(null,3)
                .isProductListPageLoaded()
                .goToShoppingCart();
        shoppingCartSteps
                .checkQuantityOfAddedProducts(6)
                .checkFullPrice();
    }
}
