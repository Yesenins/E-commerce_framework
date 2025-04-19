package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionUtils {

    public static void hoverClickElement(By elementLocator, By elementMenuLocator, WebDriver driver) {
        goToElement(elementLocator, driver);
        goToElementAndClick(elementMenuLocator, driver);
    }

    public static void dropdownClickElement(By elementLocator, By elementMenuLocator, WebDriver driver) {
        goToElementAndClick(elementLocator, driver);
        goToElementAndClick(elementMenuLocator, driver);
    }

    public static void fillInputForm(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver, Duration.ofSeconds(5));
        actions.moveToElement(element).click().pause(Duration.ofSeconds(1)).perform();
    }

    public static void goToElement(By elementLocator, WebDriver driver) {
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        WaitUtils.waitForElementToBeVisible(driver, elementLocator);
        actions.moveToElement(driver.findElement(elementLocator)).pause(Duration.ofSeconds(1)).perform();
    }

    public static void goToElementAndClick(By elementMenuLocator, WebDriver driver) {
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        WaitUtils.waitForElementToBeClickable(driver, elementMenuLocator);
        actions.moveToElement(driver.findElement(elementMenuLocator)).click().perform();
    }

    public static void scrollToTopWithActions(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).keyUp(Keys.CONTROL).perform();
    }
}
