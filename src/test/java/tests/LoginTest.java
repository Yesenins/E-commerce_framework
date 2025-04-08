package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    public static final String INCORRECT_DATA_IN_LOGIN_FIELDS = "Пожалуйста, проверьте, правильно ли указан адрес";
    public static final String INCORRECT_DATA_IN_PASSWORD_FIELDS = "Пароль должен содержать не менее 6 символов.";

    @Test(description = "")
    public void successLoginTest() {
        loginSteps.login(PropertyReader.getProperty(EMAIL),PropertyReader.getProperty(PASSWORD));
    }

    @Test(description = "")
    public void loginWithIncorrectFieldsTest() {
        loginSteps.emailEntry("testnetqwermail.com")
                .checkErrorMessageOutput(INCORRECT_DATA_IN_LOGIN_FIELDS);
    }

    @Test(description = "")
    public void loginWithEmptyPasswordTest() {
        loginSteps.passwordEntry("testnetqwer@mail.com","q")
                .checkErrorMessageOutput(INCORRECT_DATA_IN_PASSWORD_FIELDS);
    }
}
