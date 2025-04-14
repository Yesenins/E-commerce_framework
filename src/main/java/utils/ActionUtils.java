package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionUtils {

    public static void hoverClickElement(By elementLocator, By elementMenuLocator, WebDriver driver) {
        ActionUtils actionUtils = new ActionUtils();
        actionUtils.goToElement(elementLocator, driver);
        actionUtils.goToElementAndClick(elementMenuLocator, driver);
    }

    public static void dropdownClickElement(By elementLocator, By elementMenuLocator, WebDriver driver) {
        ActionUtils actionUtils = new ActionUtils();
        actionUtils.goToElementAndClick(elementLocator, driver);
        actionUtils.goToElementAndClick(elementMenuLocator, driver);
    }

    public static void fillInputForm(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver, Duration.ofSeconds(5));
        actions.moveToElement(element).click().pause(Duration.ofSeconds(1)).perform();
    }

    private void goToElement(By elementLocator, WebDriver driver) {
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        WaitUtils.waitForElementToBeVisible(driver, elementLocator);
        actions.moveToElement(driver.findElement(elementLocator)).pause(Duration.ofSeconds(1)).perform();
    }

    private void goToElementAndClick(By elementMenuLocator, WebDriver driver) {
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        WaitUtils.waitForElementToBeClickable(driver, elementMenuLocator);
        actions.moveToElement(driver.findElement(elementMenuLocator)).click().perform();
    }
}
