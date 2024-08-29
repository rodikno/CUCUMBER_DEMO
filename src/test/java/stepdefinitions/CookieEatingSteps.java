package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CookieEatingSteps {

    private int cookiesAvailable;
    private String participant;
    private String message;

    @Given("the contest has started")
    public void theContestHasStarted() {
        System.out.println("The cookie-eating contest has begun!");
    }

    @Given("there are {int} cookies available on the table")
    public void thereAreCookiesAvailableOnTheTable(int cookies) {
        this.cookiesAvailable = cookies;
        System.out.println(cookies + " cookies are now available on the table.");
    }

    @When("{word} eats {int} cookies")
    public void participantEatsCookies(String participant, int cookiesEaten) {
        this.participant = participant;
        if (cookiesEaten > cookiesAvailable) {
            message = "Not enough cookies!";
            System.out.println(message);
        } else {
            cookiesAvailable -= cookiesEaten;
            System.out.println(participant + " eats " + cookiesEaten + " cookies.");
        }
    }

    @Then("{int} cookies should be remaining")
    public void cookiesShouldBeRemaining(int expectedCookies) {
        Assert.assertEquals(cookiesAvailable, expectedCookies, "The number of remaining cookies is incorrect.");
        System.out.println(expectedCookies + " cookies are remaining.");
    }

    @Then("{word} should feel happy")
    public void participantShouldFeelHappy(String participant) {
        System.out.println(participant + " feels happy after eating cookies.");
    }

    @Then("{word} should feel even happier")
    public void participantShouldFeelEvenHappier(String participant) {
        System.out.println(participant + " feels even happier after eating more cookies.");
    }

    @Then("an error message should be displayed saying {string}")
    public void anErrorMessageShouldBeDisplayedSaying(String expectedMessage) {
        Assert.assertEquals(message, expectedMessage, "The error message is incorrect.");
        System.out.println("Error message displayed: " + expectedMessage);
    }
}