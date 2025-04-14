package pages.modals;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.ActionUtils;
import utils.WaitUtils;

@Log4j2
public class RegistrationModalPage extends LoginModalPage {

    protected final Button loginButton = new Button("//a[normalize-space()='Войдите']", "loginButton", driver);
    protected final Checkbox checkboxRegistration = new Checkbox("//*[@role='checkbox']//*[normalize-space()='Создать аккаунт']", "checkbox", driver);
    protected final Button registrationButton = new Button("//button[normalize-space()='Зарегистрироваться']", "registration", driver);

    public RegistrationModalPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationModalPage isPageLoaded() {
        WaitUtils.waitForElementToBeVisible(driver, loginButton.getLocator());
        return this;
    }

    public RegistrationModalPage fillRegistrationForm(User user) {
        fillField("имя", user.getName());
        fillField("Введите свой email", user.getEmail());
        fillField("Придумайте пароль", user.getPassword());
        fillField("Повторите пароль", user.getReplyPassword());

        checkboxRegistration.clickOn();
        return this;
    }

    private void fillField(String label, String value) {
        Input input = new Input(REGISTRATION_INPUTS,"input", driver);
        ActionUtils.fillInputForm(driver, input.getElementWithLabel(label));
        input.writeText(label, value);
    }

    public HomePage registration(User user) {
        fillRegistrationForm(user);
        registrationButton.actionClickOn();
        return new HomePage(driver);
    }

    public String getRegistrationFieldErrorMessage(String label) {
        try {
            log.info("Getting error message from registration field.");
            return driver.findElement(By.xpath(String.format(REGISTRATION_ERROR_MESSAGE, label))).getText();
        }
        catch (Exception e) {
            log.error("Failed to get registration field error message.", e);
            return "";
        }
    }
}
