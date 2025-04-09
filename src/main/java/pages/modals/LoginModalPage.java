package pages.modals;

import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HeaderPage;
import utils.WaitUtils;
@Log4j2
public class LoginModalPage extends HeaderPage {

    protected final Button registrationButton = new Button("//a[contains(@class, 'ui-login-register-link')]","registrationButton", driver);
    protected final Button enterButton = new Button("//*[@aria-label=\"Войти\"]", "enterButton", driver);
    protected final Input loginInput = new Input("//*[@aria-label=\"Электронная почта\"]", "inputPhoneOrEmail", driver);
    protected final  By LOGIN_ERROR_MESSAGE = By.xpath("//div[@class=\"input-material__wrapper\"]//p");
    protected final  By MODAL_LOCATOR = By.xpath("//div[contains(@class, 'modal')]");

    public LoginModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginModalPage openPage() {
        return this;
    }

    @Override
    public LoginModalPage isPageOpened() {
        WaitUtils.waitForPageLoaded(driver);
        WaitUtils.waitForElementToBeVisible(driver, MODAL_LOCATOR);
        WaitUtils.waitForElementToBeClickable(driver, registrationButton.getLocator());
        return this;
    }

    public LoginModalPage fillEmailForm(String email) {
        loginInput.writeText(email);
        return this;
    }

    public PasswordModalPage emailEntry(String email) {
        fillEmailForm(email);
        WaitUtils.customWait(driver,enterButton.getLocator());
        enterButton.actionClickOn();
        return new PasswordModalPage(driver);
    }

    public String getLoginFieldErrorMessage() {
        try {
            log.info("Getting error message from login field.");
            return driver.findElement(LOGIN_ERROR_MESSAGE).getText();
        }
        catch (Exception e) {
            log.error("Failed to get login field error message.", e);
            return "";
        }
    }

    public RegistrationModalPage goToRegistration() {
        WaitUtils.waitForElementToBeVisible(driver, registrationButton.getLocator());
        registrationButton.clickOn();
        return new RegistrationModalPage(driver);
    }
}
