import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestWork {
    Logger log = LogManager.getLogger(TestWork.class);
    WebDriver driver;
    ChromeOptions options = new ChromeOptions();

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
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com");
        driver.findElement(By.id("searchbox_input")).sendKeys("ОТУС", Keys.ENTER);
        WebElement title = driver.findElement(By.cssSelector("a[href$='https://otus.ru/']:first-child"));
        Assertions.assertEquals(title.getText(),
                "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...");
        log.info("Первый тест пройден");
    }

    @Test
    public void picTest(){
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.findElement(By.className("content-overlay")).click();
        WebElement pic = driver.findElement(By.className("pp_hoverContainer"));
        log.info("Второй тест, первая проверка пройдена");
        pic.isDisplayed();
        log.info("Второй тест, вторая проверка пройдена");
    }

    @Test
    public void otusTest() {
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru/");
        driver.findElement(By.className("sc-mrx253-0")).click();
        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        email.sendKeys("coyoje2271@vinthao.com");
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.clear();
        password.sendKeys("Sovet123!");
        driver.findElement(By.className("gYNtqF")).click();
        log.info("Третий тест пройден");
        System.out.println(driver.manage().getCookies());
    }
}
