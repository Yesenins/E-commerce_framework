package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
@Log4j2
public class SearchSteps extends BaseSteps {

    public SearchSteps(WebDriver driver) {
        super(driver);
    }

    @Step("open page")
    public SearchSteps openPage() {
        homePage
                .openPage()
                .isPageLoaded();
        return this;
    }
    @Step("entering in the search field without pressing search")
    public SearchSteps autosuggestSearch(String text) {
        headerPage.autosuggestSearch(text);
        return this;
    }

    @Step("search field entry")
    public SearchSteps searchProduct(String productName) {
        headerPage
                .searchTextInput(productName)
                .isPageLoaded();
        return this;
    }

    @Step("autosuggest operation check")
    public SearchSteps checkAutosuggest(String text) {

        List<WebElement> list = headerPage.getAutosuggestList(text);
        for(WebElement item : list) {
            Assert.assertTrue(item.getText().contains(text));
        }
        return this;
    }

    @Step("check product is displayed in the search result")
    public SearchSteps checkProductIsDisplayedInTheSearchResult(String brand, String product) {
        productListPage.isPageLoaded();
        List<String> list = productListPage.getProductNames(PRODUCT_NAME);
        boolean flag = false;
        for(String item : list) {
            if(item.contains(product)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(productListPage.getProductNames(BRAND_NAME).contains(brand));
        Assert.assertTrue(flag);
        return this;
    }

    @Step("check search error message")
    public SearchSteps checkSearchErrorMessage(String text) {
        Assert.assertEquals(productListPage.getSearchErrorMessage(),String.format(SEARCH_ERROR_MESSAGE,text));
        return this;
    }

    @Step("switch to the hover menu and open the selected category")
    public SearchSteps goToHoverMenu(String locatorLabel, String menuLabel) {
        headerPage
                .goToHoverMenu(locatorLabel, menuLabel)
                .isPageLoaded();
        return this;
    }

    @Step("search filter setting")
    public SearchSteps filter(String label, String subMenuLocator) {
        productListPage
                .filter(label, subMenuLocator)
                .isPageLoadedAfterFilter();
        return this;
    }

//    @Step("check that the filter correctly displays the selected products")
//    public SearchSteps filterInspection(String label, String expectedResult) {
//        int productListSize = productListPage.getProductList().size();
//        for (int i = 0; i < productListSize; i++) {
//            List<WebElement> products = productListPage.getProductList();
//            WebElement item = products.get(i);
//            item.click();
//            productPage.isPageLoaded();
//            String text = driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_ABOUT_THE_PRODUCT,label))).getText();
//            Assert.assertEquals(text, expectedResult);
//            driver.navigate().back();
//            productListPage.isPageLoadedAfterFilter();
//        }
//        return this;
//    }

    @Step("check that the filter correctly displays the selected products")
    public SearchSteps filterInspection(String label, String expectedResult) {
        int productListSize = productListPage.getProductList().size();
        for (int i = 0; i < productListSize; i++) {
            int attempts = 0;
            boolean success = false;
            while(!success && attempts < 3){
                try {
                    List<WebElement> products = productListPage.getProductList();
                    WebElement item = products.get(i);
                    item.click();
                    productPage.isPageLoaded();
                    String text = driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_ABOUT_THE_PRODUCT,label))).getText();
                    Assert.assertEquals(text, expectedResult);
                    driver.navigate().back();
                    productListPage.isPageLoadedAfterFilter();
                    success = true;
                } catch (StaleElementReferenceException e) {
                    log.info("stale element not found in the current frame");
                    attempts++;
                }
            }
        }
        return this;
    }

    @Step("search")
    public SearchSteps search(String text) {
        headerPage.searchTextInput(text);
        return this;
    }
}
