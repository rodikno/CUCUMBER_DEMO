package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",     // Path to the .feature files
        glue = {"stepdefinitions"},                   // Package name for step definitions
        plugin = {"pretty", "html:target/cucumber-reports"},  // Plugins for report generation
        monochrome = true                             // Makes console output readable
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}