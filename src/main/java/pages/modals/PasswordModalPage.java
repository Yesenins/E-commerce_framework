package pages.modals;

import elements.Button;
import elements.Input;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.WaitUtils;

public class PasswordModalPage extends LoginModalPage {

    protected final Input passwordInput = new Input("//input[@name=\"password\"]","passwordInput",driver);
    protected final Button passwordEnterButton = new Button("(//*[@aria-label=\"Войти\"])[2]","enterButton",driver);

    public PasswordModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PasswordModalPage isPageLoaded() {
        WaitUtils.waitForPageLoaded(driver);
        return this;
    }

    public HomePage fillPasswordForm(String password) {
        passwordInput.writeText(password);
        WaitUtils.customWait(driver, enterButton.getLocator());
        passwordEnterButton.clickOn();
        return new HomePage(driver);
    }

    public PasswordModalPage passwordEntry(String password) {
        passwordInput.writeText(password);
        passwordEnterButton.clickOn();
        return this;
    }
}
