package tests;

import objects.Gender;
import org.testng.annotations.Test;

public class FullFlowTest extends BaseTest {
    @Test
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
                .addProductsToCart(3)
                .isProductListPageLoaded()
                .goToShoppingCart();
        shoppingCartSteps
                .checkQuantityOfAddedProducts(6)
                .checkFullPrice();
    }
}
