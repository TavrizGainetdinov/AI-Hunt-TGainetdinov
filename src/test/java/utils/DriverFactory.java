package utils;

import browser.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * WebDriver для различных браузеров, при необходимости можно добавить здесь же.
 */
public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserType browser) {

        // Лог окружения
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");

        log.info("OS: {} {} ({})", osName, osVersion);
        log.info("Creating driver for {}", browser);

        // Выбор драйвера
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
        // Лог браузера и и билда
        var caps = ((RemoteWebDriver) driver).getCapabilities();
        log.info("Driver started: {} {}",
                caps.getBrowserName(),
                caps.getBrowserVersion());
        return driver;

    }
}

