package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@Log4j2
public class JSUtils {

    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        log.info("Scrolled to top of the page");
    }

    public static void jsScroll(WebDriver driver, By element) {
        log.info("use JS scroll to element --> " + element);
        WaitUtils.waitForElementToBeClickable(driver, element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }
}
