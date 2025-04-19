package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.WaitUtils;

public class LoginSteps extends BaseSteps {

    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    @Step("entering data in the email field")
    public LoginSteps emailEntry(String email) {
        homePage.openPage();
        headerPage.goToLogin()
                .isPageLoaded()
                .fillEmailForm(email);
        return this;
    }

    @Step("entering data in the password field")
    public LoginSteps passwordEntry(String email, String password) {
        homePage.openPage();
        headerPage.goToLogin()
                .isPageLoaded()
                .emailEntry(email)
                .isPageLoaded()
                .passwordEntry(password);
        return this;
    }

    @Step("login and check that you have logged in successfully")
    public LoginSteps loginAndCheckPageIsOpened(String email, String password) {
        homePage.openPage();
        headerPage.goToLogin()
                .isPageLoaded()
                .emailEntry(email)
                .isPageLoaded()
                .fillPasswordForm(password)
                .closeModal()
                .isPageLoadedAfterLogin();
        return this;
    }

    @Step("error message output check")
    public LoginSteps checkErrorMessageOutput(String errorMessage) {
        WaitUtils.waitForElementToBeVisible(driver, LOGIN_ERROR_MESSAGE);
        Assert.assertEquals(loginModalPage.getLoginFieldErrorMessage(), errorMessage);
        return this;
    }

    @Step("logout")
    public LoginSteps logout() {
        headerPage.logout();
        return this;
    }

    @Step("check that the logout was successful")
    public LoginSteps logoutCheck() {
        Assert.assertTrue(headerPage.checkLogout());
        return this;
    }
}
