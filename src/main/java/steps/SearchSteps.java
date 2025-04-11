package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class SearchSteps extends BaseSteps {

    public SearchSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public SearchSteps openPage() {
        homePage
                .openPage()
                .isPageOpened();
        return this;
    }
    @Step
    public SearchSteps autosuggestSearch(String text) {
        headerPage.autosuggestSearch(text);
        return this;
    }

    @Step
    public SearchSteps searchProduct(String productName) {
        headerPage
                .searchTextInput(productName)
                .isPageOpened();
        return this;
    }

    @Step
    public SearchSteps checkAutosuggest(String text) {

        List<WebElement> list = headerPage.getAutosuggestList(text);
        for(WebElement item : list) {
            Assert.assertTrue(item.getText().contains(text));
        }
        return this;
    }

    @Step
    public SearchSteps checkProductIsDisplayedInTheSearchResult(String brand, String product) {
        productListPage.isPageOpened();
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

    @Step
    public SearchSteps checkSearchErrorMessage(String text) {
        Assert.assertEquals(productListPage.getSearchErrorMessage(),String.format(SEARCH_ERROR_MESSAGE,text));
        return this;
    }

    @Step
    public SearchSteps goToHoverMenu(String locatorLabel, String menuLabel) {
        headerPage
                .goToHoverMenu(SECTION_BUTTONS, locatorLabel, HOVER_SUB_SECTIONS_BUTTON, menuLabel)
                .isPageOpened();
        return this;
    }

    @Step
    public SearchSteps filter(String label, String subMenuLocator) {
        productListPage
                .filter(label, subMenuLocator)
                .isPageOpenedAfterFilter();
        return this;
    }

    @Step
    public SearchSteps filterInspection(String label, String expectedResult) {
        int productListSize = productListPage.getProductList().size();
        for (int i = 0; i < productListSize; i++) {
            List<WebElement> products = productListPage.getProductList();
            WebElement item = products.get(i);
            item.click();
            productPage.isPageOpened();
            String text = driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_ABOUT_THE_PRODUCT,label))).getText();
            Assert.assertEquals(text, expectedResult);
            driver.navigate().back();
            productListPage.isPageOpenedAfterFilter();
        }
        return this;
    }
}
