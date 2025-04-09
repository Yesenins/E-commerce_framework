package steps;

import io.qameta.allure.Step;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.modals.RegistrationModalPage;

import static constants.IConstants.*;

public class RegistrationSteps extends BaseSteps {

    public RegistrationSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public RegistrationSteps registration(User user) {
        homePage.openPage();
        headerPage.goToLogin()
                .goToRegistration()
                .registration(user)
                .closeModal()
                .isPageOpenedAfterLogin();
        return this;
    }

    @Step
    public RegistrationSteps fillRegistrationForm(User user) {
        homePage.openPage();
        headerPage.goToLogin()
                .goToRegistration()
                .fillRegistrationForm(user);
        return this;
    }

//    @Step("error message output check")
//    public RegistrationSteps checkErrorMessageOutput(String label, String errorMessage) {
//        Assert.assertEquals(registrationModalPage.getRegistrationFieldErrorMessage(label), errorMessage);
//        return this;
//    }

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
