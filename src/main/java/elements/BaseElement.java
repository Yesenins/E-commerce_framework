package elements;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

import java.time.Duration;
@Data
@Log4j2
public abstract class BaseElement {
    protected String locator;
    protected String nameElement;
    WebDriver driver;

    public BaseElement(String locator, String nameElement, WebDriver driver) {
        this.locator = locator;
        this.nameElement = nameElement;
        this.driver = driver;
    }

    public String getText() {
        return driver.findElement(By.xpath(locator)).getText();
    }

    public void clickOn() {
        log.info("click on element --> {}", nameElement);
        WaitUtils.waitForElementToBeClickable(driver, getLocator());
        getElement().click();
    }

    public void actionClickOn() {
        log.info("click on element with action --> {} ", nameElement);
        WaitUtils.waitForElementToBeClickable(driver, getLocator());
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        actions.moveToElement(getElement()).pause(Duration.ofSeconds(1)).click().perform();
    }

    public WebElement getElement() {
        return driver.findElement(By.xpath(locator));
    }

    public WebElement getElementWithLabel(String label) {
        return driver.findElement(By.xpath(String.format(locator, label)));
    }

    public By getLocator() {
        return By.xpath(locator);
    }

    public By getLocatorWithLabel(String label) {
        return By.xpath(String.format(locator, label));
    }
}
