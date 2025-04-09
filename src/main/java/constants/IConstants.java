package constants;

import org.openqa.selenium.By;

public interface IConstants {
    String PASSWORD = "password";
    String EMAIL = "email";
    String HOME_URL = "https://www.lamoda.by/";
    By CATEGORIES = By.xpath("//a[contains(@class, '_title_kp2et_6')]");
    String SECTION_BUTTONS = "//a[normalize-space()='%s']";
    String REGISTRATION_INPUTS = "//*[contains(text(),'%s')]/..//input";
    String REGISTRATION_ERROR_MESSAGE = REGISTRATION_INPUTS + "/ancestor::div[@class=\"input-material__wrapper\"]//p";

    String NAME_ERROR_MESSAGE = "В поле введены некорректные символы. Можно вводить буквы и символы: ` ~ - '";
    String EMAIL_ERROR_MESSAGE = "Пожалуйста, проверьте, правильно ли указан адрес";
    String PASSWORD_ERROR_MESSAGE = "Пароль должен содержать не менее 8 символов.";
    String REPLAY_PASSWORD_ERROR_MESSAGE = "Пожалуйста, убедитесь, что пароли совпадают.";
    }
