package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

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
        return driver.findElement(By.xpath(String.format(locator))).getText();
    }

    public void clickOn() {
        log.info("click on element --> " + nameElement);
        WaitUtils.waitForElementToBeClickable(driver,locator);
        getElement().click();
    }

    public boolean isVisible() {
        return getElement().isDisplayed();
    }

    public WebElement getElement() {
        return driver.findElement(By.xpath(String.format(locator)));
    }
}
