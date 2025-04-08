package elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
        WaitUtils.waitForElementToBeVisible(driver,getLocator());
        getElement().clear();
        getElement().sendKeys(text);
        getElement().sendKeys(Keys.TAB);
        return this;
    }

    public Input writeText(String label, String text) {
        WaitUtils.waitForElementToBeVisible(driver, getLocatorWithLabel(label));
        getElementWithLabel(label).sendKeys(text);
        return this;
    }
}
