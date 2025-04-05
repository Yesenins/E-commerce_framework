package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    public static WebDriver createDriver(BrowserTypes browser) {
        switch(browser) {
            case CHROME:
                return createChromeDriver();
            case FIREFOX:
                return createFireFoxDriver();
            default:
                throw new RuntimeException("Incorrect browser initialization");
        }
    }

    private static ChromeDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "disable-gpu", "--incognito");
//        options.addArguments("--headless", "disable-gpu", "--incognito");
        return new ChromeDriver(options);
    }

    private static FirefoxDriver createFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized", "disable-gpu", "--incognito");
//        options.addArguments("--headless", "disable-gpu", "--incognito");
        return new FirefoxDriver(options);
    }
}
