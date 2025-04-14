package elements;

import org.openqa.selenium.WebDriver;

public class Hover extends BaseElement {
    String label;
    public Hover(String locator, String label, String nameElement, WebDriver driver) {
        super(locator, nameElement, driver);
        this.label = label;
    }
}
