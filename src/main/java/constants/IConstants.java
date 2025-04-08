package constants;

import org.openqa.selenium.By;

public interface IConstants {
    String PASSWORD = "password";
    String EMAIL = "email";
    String HOME_URL = "https://www.lamoda.by/";
    By CATEGORIES = By.xpath("//a[contains(@class, '_title_kp2et_6')]");
    String SECTION_BUTTONS = "//a[normalize-space()='%s']";
    }
