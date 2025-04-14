package pages;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class HomePage extends HeaderPage {

    protected final Button superButton = new Button("//button[normalize-space()='Супер!']", "super",driver);
    protected final String MODAL = "//div[@id=\"modals\"]";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOME_URL);
        return this;
    }

    @Override
    public HomePage isPageLoaded() {
        WaitUtils.waitForPageLoaded(driver);
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
