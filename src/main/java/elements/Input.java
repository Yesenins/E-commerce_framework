package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
@Log4j2
public class Input extends BaseElement {

    String label;
    public Input(String locator, String nameElement, WebDriver driver) {
        super(locator, nameElement, driver);
    }

    public Input clearInput() {
        getElement().sendKeys(Keys.CONTROL + "a");
        getElement().sendKeys(Keys.DELETE);
        return this;
    }

    public Input writeText(String text) {
        writeTextInSearchField(text);
        getElement().sendKeys(Keys.TAB);
        return this;
    }

    public Input writeText(String label, String text) {
        WaitUtils.waitForElementToBeVisible(driver, getLocatorWithLabel(label));
        log.info("Fill in the field: {} with the value: {}", nameElement, text);
        getElementWithLabel(label).sendKeys(text);
        return this;
    }

    public Input writeTextInSearchField(String text) {
        WaitUtils.waitForElementToBeVisible(driver,getLocator());
        log.info("Fill in the field: {} with the value: {}", nameElement, text);
        getElement().clear();
        getElement().sendKeys(text);
        return this;
    }
}
