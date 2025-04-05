package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionUtils {

    public static void goToAndClickElement(String locator, String locatorLabel, String locatorMenu, String menuLabel, WebDriver driver) {
        Actions actions = new Actions(driver, Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.xpath(String.format(locator,locatorLabel)));
        WaitUtils.waitForElementToBeVisible(driver,element);
        actions.moveToElement(element).perform();
        WebElement elementMenu = driver.findElement(By.xpath(String.format(locatorMenu,menuLabel)));
        WaitUtils.waitForElementToBeClickable(driver,elementMenu);
        elementMenu.click();
    }
}
