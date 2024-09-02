package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AleksCucumberSteps {

    private int cukesAmount;


    @Given("Aleks has {int} cucumber(s)")
    public void aleksHasCucumbers(int initialCucumbersAmount) {
        this.cukesAmount = initialCucumbersAmount;
    }

    @When("Aleks eats {int} cucumber(s)")
    public void aleksEatsCucumber(int cucumbersEaten) {
        this.cukesAmount -= cucumbersEaten;
    }

    @Then("Aleks should have {int} cucumber remaining")
    public void aleksShouldHaveCucumberRemaining(int expectedCukesRemaining) {
        Assert.assertEquals(this.cukesAmount, expectedCukesRemaining);
    }
}
