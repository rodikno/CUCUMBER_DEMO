package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    // Locators
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void setUsername(String username) {
        WebElement el = driver.findElement(usernameInput);
        el.clear();
        el.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement el = driver.findElement(passwordInput);
        el.clear();
        el.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getFlashText() {
        WebElement el = driver.findElement(flashMessage);
        // The site appends an 'x' close button and trailing spaces/newlines; normalize it
        return el.getText().trim();
    }
}
