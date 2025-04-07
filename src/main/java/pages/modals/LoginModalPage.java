package pages.modals;

import elements.Button;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.HeaderPage;
import utils.WaitUtils;

public class LoginModalPage extends HeaderPage {

    protected final Button registrationButton = new Button("//a[contains(@class, 'ui-login-register-link')]","registrationButton",driver);
    protected final Button enterButton = new Button("//*[@aria-label=\"Войти\"]","enterButton",driver);
    protected final Input loginInput = new Input("//*[@aria-label=\"Электронная почта\"]","inputPhoneOrEmail",driver);
    protected final  By LOGIN_ERROR_MESSAGE = By.xpath("//div[@class=\"input-material__wrapper\"]//p");

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
        WaitUtils.waitForElementToBeVisible(driver, By.xpath("//div[contains(@class, 'modal')]"));
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
        enterButton.clickOn();
        return new PasswordModalPage(driver);
    }

    public String getErrorMessageText(){
        return driver.findElement(LOGIN_ERROR_MESSAGE).getText();
    }
}
