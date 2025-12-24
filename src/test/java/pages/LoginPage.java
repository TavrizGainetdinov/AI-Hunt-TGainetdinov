package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object - объект страницы для доступа к
 * элементам интерфейса SwagLabs
 */
public class LoginPage {

    private static final By USERNAME = By.cssSelector("#user-name");
    private static final By PASSWORD = By.cssSelector("#password");
    private static final By LOGIN_BUTTON = By.cssSelector("#login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.navigate().to(url);
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME).clear();
        driver.findElement(USERNAME).sendKeys(username);

        driver.findElement(PASSWORD).clear();
        driver.findElement(PASSWORD).sendKeys(password);

        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }
}

