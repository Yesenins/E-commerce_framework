package pages;

import elements.Button;
import elements.Hover;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.modals.LoginModalPage;
import utils.ActionUtils;
import utils.WaitUtils;

import java.util.List;

public class HeaderPage extends BasePage {
    protected final Button loginButton = new Button("//*[normalize-space()='Войти']", "LoginButton", driver);
    protected final Button searchButton = new Button("(//*[@type=\"button\"])[2]", "searchButton", driver);
    protected final Hover profileHover = new Hover(SECTION_BUTTONS, "Профиль", "Profile", driver);
    protected final Input searchField = new Input("//input[@type=\"text\"]", "searchField", driver);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        return this;
    }

    @Override
    public HeaderPage isPageOpened() {
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

    public ProductListPage goToHoverMenu(String locator, String locatorLabel, String locatorMenu, String menuLabel) {
        By elementLocator = By.xpath(String.format(locator,locatorLabel));
        By elementMenuLocator = By.xpath(String.format(locatorMenu,menuLabel));
        ActionUtils.hoverClickElement(elementLocator,elementMenuLocator,driver);
        return new ProductListPage(driver);
    }

    public List<WebElement> getAutosuggestList(String label) {
        By locator = By.xpath(String.format(SEARCH_FIELD_LIST, label));
        WaitUtils.waitForElementToBeVisible(driver,locator);
        return driver.findElements(locator);
    }
}
