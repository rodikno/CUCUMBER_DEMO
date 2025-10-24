package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    private final WebDriver driver;

    private final By flashMessage = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFlashText() {
        return driver.findElement(flashMessage).getText().trim();
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("/secure");
    }
}
