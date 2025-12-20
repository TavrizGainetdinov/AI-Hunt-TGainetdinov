package utils;

import browser.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserType browser) {

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        log.info("OS: {} {} ({})", osName, osVersion, osArch);
        log.info("Creating driver for {}", browser);

        WebDriver driver = switch (browser) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
        };
        var caps = ((RemoteWebDriver) driver).getCapabilities();
        log.info("Driver started: {} {}",
                caps.getBrowserName(),
                caps.getBrowserVersion());
        return driver;

    }
}

