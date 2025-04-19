package pages;

import elements.Button;
import elements.Hover;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import objects.Gender;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.modals.LoginModalPage;
import utils.ActionUtils;
import utils.WaitUtils;

import java.util.List;

@Log4j2
public class HeaderPage extends BasePage {
    protected final Button loginButton = new Button("//*[normalize-space()='Войти']", "LoginButton", driver);
    protected final Button searchButton = new Button("(//*[@type=\"button\"])[2]", "searchButton", driver);
    protected final Hover profileHover = new Hover(SECTION_BUTTONS, "Профиль", "Profile", driver);
    protected final Input searchField = new Input("//input[@type=\"text\"]", "searchField", driver);
    protected final Button genderButton = new Button("//*[@data-genders='%s']", "genderButton", driver);
    protected final Button shoppingCartButton = new Button("//*[@href=\"/checkout/cart/\"]", "shoppingCartButton", driver);
    protected final Button favoritesButton = new Button("//a[normalize-space()='Избранное']", "favorites", driver);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        return this;
    }

    @Override
    public HeaderPage isPageLoaded() {
        WaitUtils.waitForPageLoaded(driver);
        return this;
    }

    public LoginModalPage goToLogin() {
        WaitUtils.waitForElementToBeVisible(driver, loginButton.getLocator());
        loginButton.clickOn();
        return new LoginModalPage(driver);
    }

    public ProductListPage searchTextInput(String text) {
        searchField.writeTextInSearchField(text);
        searchButton.clickOn();
        return new ProductListPage(driver);
    }

    public HeaderPage autosuggestSearch(String text) {
        searchField.writeTextInSearchField(text);
        return this;
    }

    public ProductListPage goToHoverMenu(String menuLabel, String subMenuLabel) {
        By elementLocator = By.xpath(String.format(SECTION_BUTTONS, menuLabel));
        By elementMenuLocator = By.xpath(String.format(HOVER_SUB_SECTIONS_BUTTON, subMenuLabel));
        ActionUtils.hoverClickElement(elementLocator, elementMenuLocator, driver);
        return new ProductListPage(driver);
    }

    public List<WebElement> getAutosuggestList(String label) {
        By locator = By.xpath(String.format(SEARCH_FIELD_LIST, label));
        WaitUtils.waitForElementToBeVisible(driver, locator);
        return driver.findElements(locator);
    }

    public HeaderPage chooseGender(Gender gender) {
        WaitUtils.waitForElementToBeClickable(driver, genderButton.getLocatorWithLabel(gender.getName()));
        genderButton.getElementWithLabel(gender.getName()).click();
        log.info("Выбор гендера: {}", gender.getName());
        WaitUtils.waitForElementToBeClickable(driver, shoppingCartButton.getLocator());
        return this;
    }

    public ProductListPage clickOnSectionButton(String locator) {
        Button sectionButton = new Button(SECTION_BUTTONS, "sectionButton", driver);
        sectionButton.getElementWithLabel(locator).click();
        log.info("click on element --> {}", locator);
        return new ProductListPage(driver);
    }

    public ShoppingCartPage goToShoppingCartPage() {
        ActionUtils.scrollToTopWithActions(driver);
        shoppingCartButton.clickOn();
        return new ShoppingCartPage(driver);
    }

    public FavoritesPage goToFavoritesPage() {
        favoritesButton.clickOn();
        return new FavoritesPage(driver);
    }

}
