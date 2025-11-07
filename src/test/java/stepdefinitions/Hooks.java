package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    @Before("@browser")
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            // Detect CI environment (GitHub Actions sets CI=true and GITHUB_ACTIONS=true)
            boolean isCi = Boolean.parseBoolean(String.valueOf(System.getenv("CI")))
                    || Boolean.parseBoolean(String.valueOf(System.getenv("GITHUB_ACTIONS")));

            ChromeOptions options = new ChromeOptions();
            // In CI use headless and CI-safe flags; locally run with a regular window
            if (isCi) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);

            // Maximize only when not headless (not supported in some CI/container setups)
            if (!isCi) {
                driver.manage().window().maximize();
            }
        }
    }

    @After("@browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
