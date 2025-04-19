package tests;

import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "check the operation of the autosuggest in the search field")
    public void autosuggestInTheSearchFieldTest() {
        searchSteps
                .openPage()
                .autosuggestSearch("hansen")
                .checkAutosuggest("hansen");
    }

    @Test(description = "search for a product by name and check its display in the search results")
    public void searchProductTest() {
        searchSteps
                .openPage()
                .searchProduct("puma suede classic")
                .checkProductIsDisplayedInTheSearchResult("puma","suede classic");
    }

    @Test(description = "invalid search")
    public void invalidSearchTest() {
        searchSteps
                .openPage()
                .searchProduct("aasdfgs")
                .checkSearchErrorMessage("aasdfgs");
    }

    @Test(description = "checking the operation of the product search filter")
    public void searchFilterTest() {
        searchSteps
                .openPage()
                .goToHoverMenu("Спорт","Кеды")
                .filter("Вид спорта", "волейбол")
                .filterInspection("Вид спорта", "волейбол");
    }
}
