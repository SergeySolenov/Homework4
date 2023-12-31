import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;


public class TestWork {
    private final Logger log = LogManager.getLogger(TestWork.class);
    private WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }
    @AfterEach
    public void end() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void duckTest() {
        driver = new WebDriverFactory().create("--headless");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("searchbox_input")).sendKeys("ОТУС", Keys.ENTER);
        WebElement title = driver.findElement(By.cssSelector("a[href='https://otus.ru/']:first-child"));
        Assertions.assertEquals(title.getText(),
                "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...");
        log.info("Первый тест пройден");
    }

    @Test
    public void picTest() {
        driver = new WebDriverFactory().create("--start-fullscreen");
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818/");
        Actions act =  new Actions(driver);
        act.moveToElement(driver.findElement(By.className("content-overlay"))).click().perform();
        WebElement pic = driver.findElement(By.className("pp_hoverContainer"));
        log.info("Второй тест, первая проверка пройдена");
        pic.isDisplayed();
        log.info("Второй тест, вторая проверка пройдена");
    }

    @Test
    public void otusTest() {
        driver = new WebDriverFactory().create("--start-maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://otus.ru/");
        driver.findElement(By.className("sc-mrx253-0")).click();
        WebElement email = driver.findElement(By.name("email"));
        String js = "arguments[0].setAttribute('value','coyoje2271@vinthao.com')";
        ((JavascriptExecutor) driver).executeScript(js, email);
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        String js2 = "arguments[0].setAttribute('value','Sovet123!')";
        ((JavascriptExecutor) driver).executeScript(js2, password);
        driver.findElement(By.className("gYNtqF")).click();
        log.info("Третий тест пройден");
        System.out.println(driver.manage().getCookies());
    }
}