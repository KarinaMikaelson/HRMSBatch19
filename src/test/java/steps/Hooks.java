package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

import java.io.IOException;

public class Hooks extends CommonMethods {

    @Before
    //this method will always execute before every scenario
    //because it has before tag which is pre condition
    public void start() throws IOException {
        openBrowserAndLaunchApplication();
    }

    @After
//  this method will always execute after every scenario
//  because it has after tag which is post condition

//        before closing the browser, i need to get the screenshot for the test
//        scenario class in cucumber, this class contains all the information about the scenario you are executing


//        getName is the method in scenario class which returns the name of the test case
//          takeScreenshot(scenario.getName()); //short

        public void end(Scenario scenario){
            byte[] pic;
            if(scenario.isFailed()) {
                pic = takeScreenshot("failed/"+scenario.getName());
            }else{
                pic = takeScreenshot("passed/"+scenario.getName());
            }
            // it will separate passed and failed folders and keep each appropriate screenshot each time
            //it will attach the screenshot in the report
            scenario.attach(pic,"image/png",scenario.getName());
            closeBrowser();
    }
}
