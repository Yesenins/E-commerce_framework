package pages.modals;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import utils.ActionUtils;
import utils.WaitUtils;

import java.time.Duration;
@Log4j2
public class RegistrationModalPage extends LoginModalPage {

    protected final Button loginButton = new Button("//a[normalize-space()='Войдите']", "loginButton", driver);
    protected final Checkbox checkboxRegistration = new Checkbox("//*[@role='checkbox']//*[normalize-space()='Создать аккаунт']", "checkbox", driver);
    protected final Button registrationButton = new Button("//button[normalize-space()='Зарегистрироваться']", "registration", driver);

    public RegistrationModalPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationModalPage isPageOpened() {
        WaitUtils.waitForElementToBeVisible(driver,loginButton.getLocator());
        return this;
    }

    public RegistrationModalPage fillRegistrationForm(User user) {
        Input nameInput = new Input(REGISTRATION_INPUTS,"nameInput", driver);
        ActionUtils.fillInputForm(driver,nameInput.getElementWithLabel("имя"));
        nameInput.writeText("имя",user.getName());

        Input emailInput = new Input(REGISTRATION_INPUTS,"emailInput", driver);
        ActionUtils.fillInputForm(driver,emailInput.getElementWithLabel("Введите свой email"));
        nameInput.writeText("Введите свой email",user.getEmail());

        Input passwordInput = new Input(REGISTRATION_INPUTS,"passwordInput", driver);
        ActionUtils.fillInputForm(driver,passwordInput.getElementWithLabel("Придумайте пароль"));
        passwordInput.writeText("Придумайте пароль",user.getPassword());

        Input replyPasswordInput = new Input(REGISTRATION_INPUTS,"replyPasswordInput", driver);
        ActionUtils.fillInputForm(driver,replyPasswordInput.getElementWithLabel("Повторите пароль"));
        passwordInput.writeText("Повторите пароль",user.getReplyPassword());

        checkboxRegistration.clickOn();
        return this;
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

//    public RegistrationModalPage fillRegistrationForm(User user) {
//        new Input(REGISTRATION_INPUTS,"nameInput", driver).writeText("имя",user.getName());
//        new Input(REGISTRATION_INPUTS,"emailInput", driver).writeText("Введите свой email",user.getEmail());
//        new Input(REGISTRATION_INPUTS,"passwordInput", driver).writeText("Придумайте пароль",user.getPassword());
//        new Input(REGISTRATION_INPUTS,"passwordInput", driver).writeText("Повторите пароль",user.getPassword());
//        checkboxRegistration.clickOn();
//        return this;
//    }
}
