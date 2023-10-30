package waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    private final WebDriver driver;
    public Waiters(WebDriver driver) {
        this.driver = driver;
    }
    public boolean waitAppearance(ExpectedCondition condition) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }
}
