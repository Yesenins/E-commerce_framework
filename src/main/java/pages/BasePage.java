package pages;

import constants.IConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
@Log4j2
public abstract class BasePage implements IConstants {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract BasePage openPage();
    public abstract BasePage isPageLoaded();
}
