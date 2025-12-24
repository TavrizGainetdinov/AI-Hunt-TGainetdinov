package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * Экстрактор логинов и паролей со страницы
 */
public class LoginCredentialsExtractor {

    private static final By USERNAMES = By.cssSelector("[data-test='login-credentials']");
    private static final By PASSWORD = By.cssSelector("[data-test='login-password']");

    private final WebDriver driver;

    public LoginCredentialsExtractor(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Извлекаем все логины игнорируя первую запись
     */
    public List<String> getUsernames() {
        String text = driver.findElement(USERNAMES).getText();
        String [] lines = text.split("\n");

        List<String> result = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String u = lines[i].trim();
            if (!u.isEmpty()) {
                result.add(u);
            }
        }
        return result;
    }

    /**
     * Извлекаем пароль - один для всех, игнорируя первую запись
     */
    public String getPassword() {
        String text = driver.findElement(PASSWORD).getText();
        String[] lines = text.split("\n");

        return (lines.length > 1) ? lines[1].trim() : "";
    }

    /**
     *
     * @param index (0 = standard_user, 1 = locked_out_user итд.
     *
     */
    public String getUsername (int index) {
        return getUsernames().get(index);
    }
}

