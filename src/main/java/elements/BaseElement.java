package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

import java.time.Duration;

@Log4j2
public class BaseElement {
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
        log.info("click on element --> {}",nameElement);
        WaitUtils.waitForElementToBeClickable(driver,getLocator());
        getElement().click();
    }
    public void actionClickOn() {
        log.info("click on element with action --> {} ", nameElement);
        WaitUtils.waitForElementToBeClickable(driver,getLocator());
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        actions.moveToElement(getElement()).pause(Duration.ofSeconds(1)).click().perform();
    }

    public WebElement getElement() {
        return driver.findElement(By.xpath(locator));
    }

    public WebElement getElementWithLabel(String label) {
        return driver.findElement(By.xpath(String.format(locator,label)));
    }

    public boolean isVisible() {
        return getElement().isDisplayed();
    }

    public By getLocator() {
        return By.xpath(locator);
    }

    public By getLocatorWithLabel(String label) {
        return By.xpath(String.format(locator,label));
    }
}
