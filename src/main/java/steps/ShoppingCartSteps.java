package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import objects.Gender;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
@Log4j2
public class ShoppingCartSteps extends BaseSteps {

    public ShoppingCartSteps(WebDriver driver) {
        super(driver);
    }

    @Step("open page")
    public ShoppingCartSteps openPage() {
        homePage
                .openPage()
                .isPageLoaded();
        return this;
    }

    @Step("check that the page is loaded")
    public ShoppingCartSteps isProductListPageLoaded() {
        productListPage.isPageLoaded();
        return this;
    }

    @Step("add product to cart and check product name")
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

    @Step("choose gender")
    public ShoppingCartSteps chooseGenderAndOpenProductsList(Gender gender, String locatorLabel, String menuLabel) {
        headerPage
                .chooseGender(gender)
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded();
        return this;
    }

    @Step
    public ShoppingCartSteps addProductsToCart(String mySize, int quantity) {
        int checkedQuantity = productListPage.checkQuantityOfProducts(quantity);

        for(int i = 1; i <= checkedQuantity; i++) {
            int attempts = 0;
            boolean success = false;
            while(!success && attempts < 3){
                try {
                    productListPage.goToProductRandom(i*2);
                    if(mySize != null){
                        productPage.chooseSize(mySize);
                    }
                    productPage
                            .goToShoppingCart()
                            .continueShopping();
                    driver.navigate().back();
                    productListPage.isPageLoaded();
                    success = true;
                } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
                    log.info("Expected condition failed");
                    attempts++;
                }
            }
        }
        return this;
    }

//    @Step("adding items to cart that are not sized")
//    public ShoppingCartSteps addProductsToCart(int quantity) {
//        productListPage.addProductsToCart(quantity);
//        return this;
//    }

    @Step("move to shopping cart")
    public ShoppingCartSteps goToShoppingCart() {
        headerPage.goToShoppingCartPage()
                .isPageLoaded();
        return this;
    }

    @Step("check quantity of added products")
    public ShoppingCartSteps checkQuantityOfAddedProducts(int quantity) {
        Assert.assertEquals(shoppingCartPage.getQuantityOfProducts(), quantity);
        return this;
    }

    @Step("filter search")
    public ShoppingCartSteps filter(String locator, String subMenuLocator) {
        productListPage.filter(locator, subMenuLocator);
        return this;
    }

    @Step("check full price")
    public ShoppingCartSteps checkFullPrice() {
        Assert.assertEquals(shoppingCartPage.getPrice(), shoppingCartPage.getFullPrice());
        return this;
    }
}
