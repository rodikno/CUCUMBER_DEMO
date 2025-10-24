package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import pages.SecureAreaPage;

public class InternetLoginSteps {

    private WebDriver driver() {
        return Hooks.driver;
    }

    private LoginPage loginPage() {
        return new LoginPage(driver());
    }

    private SecureAreaPage securePage() {
        return new SecureAreaPage(driver());
    }

    @Given("I am on the Internet Herokuapp login page")
    public void iAmOnTheInternetLoginPage() {
        loginPage().open();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage().setUsername(username);
        loginPage().setPassword(password);
        loginPage().clickLogin();
    }

    @Then("I should see a success message containing {string}")
    public void iShouldSeeASuccessMessageContaining(String expected) {
        String text = securePage().getFlashText();
        Assert.assertTrue(text.contains(expected),
                "Expected success message to contain: '" + expected + "' but was: '" + text + "'");
        Assert.assertTrue(securePage().isLoaded(), "User is not on the secure area page");
    }

    @Then("I should see an error message containing {string}")
    public void iShouldSeeAnErrorMessageContaining(String expected) {
        String text = loginPage().getFlashText();
        Assert.assertTrue(text.contains(expected),
                "Expected error message to contain: '" + expected + "' but was: '" + text + "'");
    }

    @And("the URL should contain {string}")
    public void theURLShouldContain(String part) {
        Assert.assertTrue(driver().getCurrentUrl().contains(part),
                "Expected URL to contain '" + part + "' but was '" + driver().getCurrentUrl() + "'");
    }
}
