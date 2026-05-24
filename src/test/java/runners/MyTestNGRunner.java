package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/LoginOrange.feature",
glue = {"stepdefinations", "hooks"},
plugin = {"pretty","html:target/cucumber.hrml"})
public class MyTestNGRunner extends AbstractTestNGCucumberTests {

}
