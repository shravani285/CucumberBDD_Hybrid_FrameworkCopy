package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                //features = ".//Features/Login.feature",
                //features = ".//Features/Customers.feature",
                features = ".//Features/",
                glue = "stepDefinition",
                //dryRun = true,
                dryRun = false,
                monochrome = true,
                plugin = {"pretty",
                        "html:target/cucumber/cucumberreports.html", "json:target/cucumber/cucumberreports.json",
                        "junit:target/cucumber-results.xml",
                        },
                tags= "@sc1"
        )
public class Testrun {
}
