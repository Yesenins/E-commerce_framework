package steps;

import io.qameta.allure.Step;
import objects.Gender;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartSteps extends BaseSteps {

    public ShoppingCartSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public ShoppingCartSteps openPage() {
        homePage
                .openPage()
                .isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps isProductListPageLoaded() {
        productListPage.isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps addProductToCartAndCheckProductName(Gender gender, String locatorLabel, String menuLabel, String mySize) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded()
                .goToProductRandom(1)
                .chooseSize(mySize);
        String actualName = productPage.getProductName();
        productPage
                .goToShoppingCart()
                .goToCart()
                .isPageLoaded()
                .goToProductDescription()
                .isPageLoaded();
        String expectedResult = shoppingCartModalPage.getProductName();
        Assert.assertEquals(actualName, expectedResult);
        return this;
    }

    @Step
    public ShoppingCartSteps chooseGenderAndOpenProductsList(Gender gender, String locatorLabel, String menuLabel) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps addProductsToCart(String mySize, int quantity) {
        productListPage.addProductsToCart(mySize, quantity);
        return this;
    }

    @Step
    public ShoppingCartSteps addProductsToCart(int quantity) {
        productListPage.addProductsToCart(quantity);
        return this;
    }

    @Step
    public ShoppingCartSteps goToShoppingCart() {
        headerPage.goToShoppingCartPage()
                .isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps checkQuantityOfAddedProducts(int quantity) {
        Assert.assertEquals(shoppingCartPage.getQuantityOfProducts(), quantity);
        return this;
    }

    @Step
    public ShoppingCartSteps filter(String locator, String subMenuLocator) {
        productListPage.filter(locator, subMenuLocator);
        return this;
    }

    @Step
    public ShoppingCartSteps checkFullPrice() {
        Assert.assertEquals(shoppingCartPage.getPrice(), shoppingCartPage.getFullPrice());
        return this;
    }
}
