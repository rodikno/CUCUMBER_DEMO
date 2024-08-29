package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatureAdoptionSteps {

    private Map<String, Creature> availableCreatures = new HashMap<>();
    private Map<String, String> adoptions = new HashMap<>();

    @Given("the magical creatures are available for adoption")
    public void theMagicalCreaturesAreAvailableForAdoption() {
        System.out.println("Magical creatures are available for adoption.");
    }

    @Given("the following magical creatures are available for adoption:")
    public void theFollowingMagicalCreaturesAreAvailableForAdoption(DataTable dataTable) {
        List<Map<String, String>> creaturesList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> creatureData : creaturesList) {
            String name = creatureData.get("Name");
            String type = creatureData.get("Type");
            int age = Integer.parseInt(creatureData.get("Age"));
            String specialAbility = creatureData.get("SpecialAbility");
            availableCreatures.put(name, new Creature(name, type, age, specialAbility));
        }
        System.out.println("Available creatures: " + availableCreatures);
    }

    @When("the following creatures are adopted:")
    public void theFollowingCreaturesAreAdopted(DataTable dataTable) {
        List<Map<String, String>> adoptionList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> adoptionData : adoptionList) {
            String name = adoptionData.get("Name");
            String adopter = adoptionData.get("Adopter");
            if (availableCreatures.containsKey(name)) {
                adoptions.put(name, "Adopted by " + adopter);
            }
        }
        System.out.println("Adoption records: " + adoptions);
    }

    @Then("the adoption records should be updated as follows:")
    public void theAdoptionRecordsShouldBeUpdatedAsFollows(DataTable dataTable) {
        List<Map<String, String>> expectedAdoptionRecords = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> expectedRecord : expectedAdoptionRecords) {
            String name = expectedRecord.get("Name");
            String expectedAdopter = expectedRecord.get("Adopter");
            String expectedStatus = expectedRecord.get("Status");
            String actualStatus = adoptions.get(name);
            Assert.assertNotNull(actualStatus, "Adoption record not found for " + name);
            Assert.assertEquals(actualStatus, expectedStatus + " by " + expectedAdopter, "Adoption record for " + name + " does not match.");
        }
        System.out.println("All adoption records are correct.");
    }

    private static class Creature {
        private String name;
        private String type;
        private int age;
        private String specialAbility;

        Creature(String name, String type, int age, String specialAbility) {
            this.name = name;
            this.type = type;
            this.age = age;
            this.specialAbility = specialAbility;
        }

        @Override
        public String toString() {
            return "Creature{name='" + name + "', type='" + type + "', age=" + age + ", specialAbility='" + specialAbility + "'}";
        }
    }
}