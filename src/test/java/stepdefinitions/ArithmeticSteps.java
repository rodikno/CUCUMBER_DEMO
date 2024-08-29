package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ArithmeticSteps {

    private int firstNumber;
    private int secondNumber;
    private int result;

    @Given("the first number is {int}")
    public void theFirstNumberIs(Integer number) {
        this.firstNumber = number;
        System.out.println("First number is set to: " + number);
    }

    @Given("the second number is {int}")
    public void theSecondNumberIs(Integer number) {
        this.secondNumber = number;
        System.out.println("Second number is set to: " + number);
    }

    @When("the user adds the two numbers")
    public void theUserAddsTheTwoNumbers() {
        this.result = firstNumber + secondNumber;
        System.out.println("The sum of the two numbers is: " + result);
    }

    @When("the user subtracts the second number from the first")
    public void theUserSubtractsTheSecondNumberFromTheFirst() {
        this.result = firstNumber - secondNumber;
        System.out.println("The difference between the two numbers is: " + result);
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(Integer expectedResult) {
        Assert.assertEquals(result, expectedResult, "The result does not match the expected value.");
        System.out.println("The result matches the expected value: " + expectedResult);
    }
}
