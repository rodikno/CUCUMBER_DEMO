package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class AlcoholDrinkerSteps {

    private int bottlesOwned;
    private int bottlesDrunk;
    private boolean isDrunk = false;


    @Given("user has {int} bottle(s) of whiskey")
    public void userHasBottleOfWhiskey(int bottles) {
        this.bottlesOwned = bottles;
    }

    @When("user drinks {int} bottle(s)")
    public void userDrinksBottle(int bottlesDrinked) {
        this.bottlesOwned -= bottlesDrinked;
        this.bottlesDrunk += bottlesDrinked;
        this.isDrunk = bottlesDrinked > 0;
    }

    @Then("user is drunk")
    public void userIsDrunk() {
        Assert.assertTrue(this.isDrunk, "User should be drunk after drinking");
    }

    @And ("user has {int} bottles remaining")
    public void userHasBottlesRemaining(int bottlesRemaining) {
        Assert.assertEquals(this.bottlesOwned, bottlesRemaining, "Incorrect number of bottles remaining");
    }
}
