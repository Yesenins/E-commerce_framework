package steps;

import io.qameta.allure.Step;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegistrationSteps extends BaseSteps {

    public RegistrationSteps(WebDriver driver) {
        super(driver);
    }

    @Step("register and check page is opened")
    public RegistrationSteps registerAndCheckPageIsOpened(User user) {
        homePage
                .openPage();
        headerPage
                .goToLogin()
                .goToRegistration()
                .registration(user)
                .closeModal()
                .isPageLoadedAfterLogin();
        return this;
    }

    @Step("fill registration form")
    public RegistrationSteps fillRegistrationForm(User user) {
        homePage
                .openPage();
        headerPage
                .goToLogin()
                .goToRegistration()
                .isPageLoaded()
                .fillRegistrationForm(user);
        return this;
    }

    @Step("error message output check")
    public RegistrationSteps checkErrorMessageOutput(String errorMessage) {
        switch (errorMessage){
            case NAME_ERROR_MESSAGE:
                Assert.assertEquals(registrationModalPage.getRegistrationFieldErrorMessage("имя"), NAME_ERROR_MESSAGE);
                break;
            case EMAIL_ERROR_MESSAGE:
                Assert.assertEquals(registrationModalPage.getRegistrationFieldErrorMessage("Введите свой email"), EMAIL_ERROR_MESSAGE);
                break;
            case PASSWORD_ERROR_MESSAGE:
                Assert.assertEquals(registrationModalPage.getRegistrationFieldErrorMessage("Придумайте пароль"), PASSWORD_ERROR_MESSAGE);
                break;
            case REPLAY_PASSWORD_ERROR_MESSAGE:
                Assert.assertEquals(registrationModalPage.getRegistrationFieldErrorMessage("Повторите пароль"), REPLAY_PASSWORD_ERROR_MESSAGE);
                break;
            default:
                throw new RuntimeException("Incorrect validate");
        }
        return this;
    }
}
