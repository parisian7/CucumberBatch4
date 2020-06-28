package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\petAddition.feature",
        glue = "API",
        dryRun = false
)


public class ApiPetAddRunner {
}
