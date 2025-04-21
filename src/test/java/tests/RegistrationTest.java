package tests;

import objects.User;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(description = "success registration")
    public void successRegistrationTest() {
        User user = User.builder()
                .name("Serega")
                .email("sdfsd@asd.com")
                .password("sdfsdfsd")
                .build();
        registrationSteps.registerAndCheckPageIsOpened(user);
    }

    @Test(groups = {"smoke"}, description = "entering incorrect data in the registration fields")
    public void registrationWithIncorrectFieldsTest() {
        User user = User.builder()
                .name("!£$")
                .email("sdfsd@asdom")
                .password("sdf")
                .replyPassword("s")
                .build();
        registrationSteps
                .fillRegistrationForm(user)
                .checkErrorMessageOutput(NAME_ERROR_MESSAGE)
                .checkErrorMessageOutput(EMAIL_ERROR_MESSAGE)
                .checkErrorMessageOutput(PASSWORD_ERROR_MESSAGE);
    }

    @Test(groups = {"smoke"}, description = "entering an incorrect password in the repeated password field")
    public void registrationWithWrongReplyPasswordTest() {
        User user = User.builder()
                .name("Serega")
                .email("sdfsd@gfd.dom")
                .password("sdf123hdfhb")
                .replyPassword("ssdf2sxff")
                .build();
        registrationSteps
                .fillRegistrationForm(user)
                .checkErrorMessageOutput(REPLAY_PASSWORD_ERROR_MESSAGE);
    }
}
