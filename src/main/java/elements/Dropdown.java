package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionUtils;

public class Dropdown extends BaseElement {

    public Dropdown(String locator, String nameElement, WebDriver driver) {
        super(locator, nameElement, driver);
    }

    public void selectFromDropdown(String label, String subMenu, String subMenuLabel) {
        By subMenuElement = By.xpath(String.format(subMenu, subMenuLabel));
        ActionUtils.dropdownClickElement(getLocatorWithLabel(label), subMenuElement, driver);
    }
}
