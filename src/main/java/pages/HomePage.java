package pages;

import elements.Button;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;
@Log4j2
public class HomePage extends HeaderPage {

    protected final Button superButton = new Button("//button[normalize-space()='Супер!']", "super",driver);
    protected final String MODAL = "//div[@id=\"modals\"]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOME_URL);
        log.info("Open home page");
        return this;
    }

    @Override
    public HomePage isPageLoaded() {
        WaitUtils.waitForPageLoaded(driver);
        WaitUtils.waitForElementToBeClickable(driver, searchField.getLocator());
        return this;
    }

    public HomePage isPageLoadedAfterLogin() {
        WaitUtils.waitForElementToBeVisible(driver, profileHover.getLocatorWithLabel("Профиль"));
        return this;
    }

    public HomePage closeModal() {
        WaitUtils.waitForElementToBeVisible(driver, By.xpath(MODAL));
        superButton.clickOn();
        return this;
    }

}
