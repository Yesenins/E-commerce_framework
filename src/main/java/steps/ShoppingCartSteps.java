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
    public ShoppingCartSteps addProductToCartAndCheckProductName(Gender gender, String locatorLabel, String menuLabel, String mySize) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded()
                .goToProductRandom()
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
    public ShoppingCartSteps addProductsToCart(Gender gender, String locatorLabel, String menuLabel, String mySize, int quantity) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded();
        for(int i = 0; i < quantity; i++) {
            productListPage
                    .goToProductRandom()
                    .chooseSize(mySize)
                    .goToShoppingCart()
                    .continueShopping()
                    .isPageLoaded();
            headerPage
                    .clickOnSectionButton(locatorLabel)
                    .isPageLoaded();
        }
        headerPage.goToShoppingCartPage()
                .isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps checkQuantityOfAddedProducts(int quantity) {
        Assert.assertEquals(shoppingCartPage.getQuantityOfProducts(), quantity + "");
        return this;
    }

}
