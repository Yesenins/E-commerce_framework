package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

@Log4j2
public class BaseElement {
    protected By locator;
    protected String nameElement;
    WebDriver driver;

    public BaseElement(By locator, String nameElement, WebDriver driver) {
        this.locator = locator;
        this.nameElement = nameElement;
        this.driver = driver;
    }

    public String getText() {
        return driver.findElement(locator).getText();
    }

    public void click() {
        log.info("click on element --> " + nameElement);
        WaitUtils.waitForElementToBeClickable(driver,locator);
        driver.findElement(locator).click();
    }

    public boolean isVisible() {
        return driver.findElement(locator).isDisplayed();
    }

}
