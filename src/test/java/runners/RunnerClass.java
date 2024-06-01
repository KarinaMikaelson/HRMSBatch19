package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.checkerframework.checker.units.qual.C;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//           this will specify the path of features directory
        features = "src/test/resources/features/",

//           here in glue, we provide the name of the steps package
//           runner class will search all the step definitions inside this package
        glue = "steps",

//           when we set the value of dryRun = true:
//           - it stops the actual execution and scans all the step definitions
//           - if the step def is missing in any of the class, then it will give you that  missing step def.
//           - if all the step def are there, it will provide green tick
//           set the value of dryRun=false - to start the actual execution after implementing the missing step
        dryRun = false,
        tags = "@ddt"

)
public class RunnerClass {

}
