package pages;

import constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class BasePage implements IConstants {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract BasePage openPage();
    public abstract BasePage isPageLoaded();
}
