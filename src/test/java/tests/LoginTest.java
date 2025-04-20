package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    public static final String INCORRECT_DATA_IN_LOGIN_FIELDS = "Пожалуйста, проверьте, правильно ли указан адрес";
    public static final String INCORRECT_DATA_IN_PASSWORD_FIELDS = "Пароль должен содержать не менее 6 символов.";

    @Test(description = "successful login")
    public void successLoginTest() {
        loginSteps.loginAndCheckPageIsOpened(PropertyReader.getProperty(EMAIL),PropertyReader.getProperty(PASSWORD));
    }

    @Test(groups = {"smoke"}, description = "entering incorrect data in the login field")
    public void loginWithIncorrectFieldsTest() {
        loginSteps.emailEntry("testnetqwermail.com")
                .checkErrorMessageOutput(INCORRECT_DATA_IN_LOGIN_FIELDS);
    }

    @Test(description = "incorrect password")
    public void loginWithWrongPasswordTest() {
        loginSteps.passwordEntry("testnetqwer@mail.com","q")
                .checkErrorMessageOutput(INCORRECT_DATA_IN_PASSWORD_FIELDS);
    }

    @Test(description = "logging out of personal account")
    public void logoutTest() {
        loginSteps
                .loginAndCheckPageIsOpened(PropertyReader.getProperty(EMAIL),PropertyReader.getProperty(PASSWORD))
                .logout()
                .logoutCheck();
    }
}
