package tests;

import browser.BrowserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.DriverFactory;
import utils.TestLoggerExtension;
import utils.LoginCredentialsExtractor;
import io.qameta.allure.Description;

@ExtendWith(TestLoggerExtension.class)
public class SwagLabLoginTests {

    public static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";

    @Description("Standard user")
    @ParameterizedTest(name = "{0} - standardUserTest")
    @EnumSource(BrowserType.class)
    void standardUserTest(BrowserType browser) {
        WebDriver driver = DriverFactory.createDriver(browser);

        try {
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.open(SAUCE_DEMO_URL);
            loginPage.login(creds(driver).getUsername(0), creds(driver).getPassword());

            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("inventory.html"),
                    "Expected inventory in " + browser + " but was: " + driver.getCurrentUrl()
            );
        } finally {
            driver.quit();
        }
    }

    @Description("Incorrect password")
    @ParameterizedTest(name = "{0} - incorrectPasswordTest")
    @EnumSource(BrowserType.class)
    void incorrectPasswordTest(BrowserType browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        try {
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
//            LoginCredentialsExtractor creds = new LoginCredentialsExtractor(driver);

            loginPage.open(SAUCE_DEMO_URL);
            loginPage.login(creds(driver).getUsername(0), "wrong_pass");

            Assertions.assertEquals("Epic sadface: " +
                            "Username and password do not match any user in this service",
                    loginPage.getErrorText());
        } finally {
            driver.quit();
        }
    }

    @Description("Locked-out user")
    @ParameterizedTest(name = "{0} - lockedOutUserTest")
    @EnumSource(BrowserType.class)
    void lockedOutUserTest(BrowserType browser) {
        WebDriver driver = DriverFactory.createDriver(browser);

        try {
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
//            LoginCredentialsExtractor creds = new LoginCredentialsExtractor(driver);

            loginPage.open(SAUCE_DEMO_URL);
            loginPage.login(creds(driver).getUsername(1), creds(driver).getPassword());

            Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.",
                            loginPage.getErrorText());
        } finally {
            driver.quit();
        }
    }

    @Description("Empty credentials")
    @ParameterizedTest(name = "{0} - emptyCredentialsTest")
    @EnumSource(BrowserType.class)
    void emptyCredentialsTest(BrowserType browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        try {
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.open(SAUCE_DEMO_URL);
            loginPage.login("", "");

            Assertions.assertEquals("Epic sadface: Username is required", loginPage.getErrorText());
        } finally {
            driver.quit();
        }
    }

    @Description("Performance glitch")
    @ParameterizedTest(name = "{0} - performanceGlitchUserTest")
    @EnumSource(BrowserType.class)
    void performanceGlitchUserTest(BrowserType browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        try {
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
//            LoginCredentialsExtractor creds = new LoginCredentialsExtractor(driver);

            loginPage.open(SAUCE_DEMO_URL);
            loginPage.login(creds(driver).getUsername(3), creds(driver).getPassword());

            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("inventory.html"),
                    "Expected inventory in " + browser + " but was: " + driver.getCurrentUrl()
            );
        } finally {
            driver.quit();
        }
    }

    private LoginCredentialsExtractor creds(WebDriver driver) {
        return new LoginCredentialsExtractor(driver);
    }
}

