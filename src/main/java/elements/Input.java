package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class Input extends BaseElement {

    public Input(String locator, String nameElement, WebDriver driver) {
        super(locator, nameElement, driver);
    }

    public Input clearInput() {
        getElement().sendKeys(Keys.CONTROL + "a");
        getElement().sendKeys(Keys.DELETE);
        return this;
    }

    public Input writeText(String text) {
        WaitUtils.waitForElementToBeVisible(driver,getElement());
        getElement().sendKeys(text);
        return this;
    }

    public Input writeText(String label, String text) {
        WaitUtils.waitForElementToBeVisible(driver,locator,label);
        driver.findElement(By.xpath(String.format(locator,label))).sendKeys(text);
        return this;
    }
}
