package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            // Uncomment to run headless in CI
            // options.addArguments("--headless=new");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
