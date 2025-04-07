package pages;

import elements.Button;
import elements.Hover;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import pages.modals.LoginModalPage;
import utils.WaitUtils;

public class HeaderPage extends BasePage {
    protected final Button loginButton = new Button("//*[normalize-space()='Войти']","LoginButton",driver);
    protected final Hover profileHover = new Hover(SECTION_BUTTONS,"Профиль","Profile",driver);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        return this;
    }

    @Override
    public HeaderPage isPageOpened() {
        return this;
    }

    public LoginModalPage goToLogin() {
        WaitUtils.waitForElementToBeVisible(driver,loginButton.getLocator());
        loginButton.clickOn();
        return new LoginModalPage(driver);
    }
}
