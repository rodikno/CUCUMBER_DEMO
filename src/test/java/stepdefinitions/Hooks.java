package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @After(order = 1, value = "@browser")
    public void takeScreenshotAndAttach(Scenario scenario) {
        if (driver == null) return;

        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Attach to Cucumber report
                String attachName = (scenario.isFailed() ? "FAILED" : "PASSED") + " - " + scenario.getName();
                scenario.attach(screenshot, "image/png", attachName);

                // Also save to disk under target/screenshots
                saveScreenshotToFile(screenshot, scenario);
            }
        } catch (Exception e) {
            // Do not fail the test session because of screenshot issues
            System.out.println("[DEBUG_LOG] Screenshot capture failed: " + e.getMessage());
        }
    }

    @After(order = 0, value = "@browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void saveScreenshotToFile(byte[] pngBytes, Scenario scenario) throws IOException {
        Path dir = Paths.get("target", "screenshots");
        Files.createDirectories(dir);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        String safeName = sanitizeFilename(scenario.getName());
        String fileName = String.format("%s__%s__%s.png", timestamp, status, safeName);
        Path file = dir.resolve(fileName);
        Files.write(file, pngBytes);
        System.out.println("[DEBUG_LOG] Saved screenshot: " + file.toAbsolutePath());
    }

    private String sanitizeFilename(String input) {
        // Replace any character that is not alphanumeric, dot, underscore or dash
        return input.replaceAll("[^a-zA-Z0-9._-]", "_")
                .replaceAll("_+", "_")
                .replaceAll("^_+|_+$", "");
    }
}
