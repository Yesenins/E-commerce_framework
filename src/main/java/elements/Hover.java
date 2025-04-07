package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Hover extends BaseElement {
    String label;
    public Hover(String locator,String label, String nameElement, WebDriver driver) {
        super(locator, nameElement, driver);
        this.label = label;
    }
}
