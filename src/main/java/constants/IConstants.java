package constants;

import org.openqa.selenium.By;

public interface IConstants {
    String HOME_URL = "https://www.lamoda.by/";
    By CATEGORIES = By.xpath("//a[contains(@class, '_title_kp2et_6')]");
    String SECTION_BUTTONS = "//a[normalize-space()='%s']";
    By LOGIN_ERROR_MESSAGE = By.xpath("//*[@aria-label=\"Электронная почта\"]/ancestor::div[@class=\"input-material__wrapper\"]//p");
}
