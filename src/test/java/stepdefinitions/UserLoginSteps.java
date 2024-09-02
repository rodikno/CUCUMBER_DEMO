package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoginSteps {

    private String username;
    private String password;
    private String email;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        // Code to navigate to the login page
        System.out.println("User navigates to the login page");
    }

    @When("the user enters a valid username and password")
    public void theUserEntersAValidUsernameAndPassword() {
        // Code to enter valid username and password
        System.out.println("User enters a valid username and password");
    }


    @And("clicks the \"Login\" button")
    public void clicksTheLoginButton() {
        // Code to click the login button
        System.out.println("User clicks the Login button");
    }

    @Then("the user should be redirected to the homepage")
    public void theUserShouldBeRedirectedToTheHomepage() {
        // Code to verify that the user is redirected to the homepage
        System.out.println("User is redirected to the homepage");
    }

    @And("a welcome message should be displayed")
    public void aWelcomeMessageShouldBeDisplayed() {
        // Code to verify that a welcome message is displayed
        System.out.println("Welcome message is displayed");
    }

    @When("the user enters the username {string} and the password {string}")
    public void theUserEntersTheUsernameAndThePassword(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("User enters the username: " + username + " and password: " + password);
    }

    @When("the user enters the username {string} and the password {string} and the email {string}")
    public void theUserEntersTheUsernameAndThePasswordAndTheEmail(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        System.out.println("User enters the username: " + username + " and password: " + password + " and email: " + email);
    }
}
