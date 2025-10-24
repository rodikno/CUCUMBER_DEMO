package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecureAreaPage {
    private final WebDriver driver;

    private final By secureAreaSubtitle = By.cssSelector(".subheader");
    private final By flashMessage = By.cssSelector("#flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeText() {
        return driver.findElement(secureAreaSubtitle).getText().trim();
    }

    public String getFlashText() {
        WebElement el = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(flashMessage));
        return el.getText().trim();
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("/secure");
    }
}
