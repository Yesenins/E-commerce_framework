package tests;

import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void autosuggestInTheSearchFieldTest() {
        searchSteps
                .openPage()
                .autosuggestSearch("hansen")
                .checkAutosuggest("hansen");
    }

    @Test
    public void searchProductTest() {
        searchSteps
                .openPage()
                .searchProduct("puma suede classic")
                .checkProductIsDisplayedInTheSearchResult("puma","suede classic");
    }

    @Test
    public void invalidSearchTest() {
        searchSteps
                .openPage()
                .searchProduct("aasdfgs")
                .checkSearchErrorMessage("aasdfgs");
    }

    @Test
    public void searchFilterTest() {
        searchSteps
                .openPage()
                .goToHoverMenu("Спорт","Кеды")
                .filter("Вид спорта", "волейбол")
                .filterInspection("Вид спорта", "волейбол");
    }
}
