package constants;

import org.openqa.selenium.By;

public interface IConstants {
    String PASSWORD = "password";
    String EMAIL = "email";
    String HOME_URL = "https://www.lamoda.by/";
    By CATEGORIES = By.xpath("//*[text()='Категории']");
    String SECTION_BUTTONS = "//a[normalize-space()='%s']";
    String HOVER_SUB_SECTIONS_BUTTON = "//*[@category=\"[object Object]\"]//*[text()='%s']";
    String REGISTRATION_INPUTS = "//*[contains(text(),'%s')]/..//input";

    By LOGIN_ERROR_MESSAGE = By.xpath("//div[@class=\"input-material__wrapper\"]//p");

    String REGISTRATION_ERROR_MESSAGE = REGISTRATION_INPUTS + "/ancestor::div[@class=\"input-material__wrapper\"]//p";
    String NAME_ERROR_MESSAGE = "В поле введены некорректные символы. Можно вводить буквы и символы: ` ~ - '";
    String EMAIL_ERROR_MESSAGE = "Пожалуйста, проверьте, правильно ли указан адрес";
    String PASSWORD_ERROR_MESSAGE = "Пароль должен содержать не менее 8 символов.";
    String REPLAY_PASSWORD_ERROR_MESSAGE = "Пожалуйста, убедитесь, что пароли совпадают.";

    String SEARCH_FIELD_LIST = "//*[@class='_root_mh0i8_2']//*[contains(text(), '%s')]";
    String BRAND_NAME = "//*[contains(@class, \"x-product-card-description__brand-name_faded\")]";
    String PRODUCT_NAME = "//*[contains(@class, \"x-product-card-description__product-name_faded\")]";

    By SEARCH_ERROR_MESSAGE_LOCATOR = By.xpath("//h1");
    String SEARCH_ERROR_MESSAGE = "Ничего не нашли по запросу «%s»";

    String DROPDOWN_SUB_MENU = "//*[@class=\"popover-content\"]//span[text()='%s']";
    String DROPDOWN = "//span[contains(text(),'%s')]";
    String DROPDOWN_INPUT_MIN = "//input[@name=\"minRange\"]";
    String DROPDOWN_INPUT_MAX = "//input[@name=\"maxRange\"]";
    String DROPDOWN_APPLY_BUTTON = "//*[@class=\"popover-content\"]//button";

    String PRODUCT = "(//*[contains(@class, 'x-product-card__link')])[%s]";
    String FAVORITES = PRODUCT + "//*[contains(@class, 'icon_heart-recommendations')]";
    String PRODUCT_DESCRIPTION_ABOUT_THE_PRODUCT = "//*[normalize-space()='%s']/ancestor::p//*[contains(@class, '_value_ajirn_27')]";
    String RESET_FILTER_BUTTON = "//*[contains(@class, 'icon__filter_reset')]";
}
