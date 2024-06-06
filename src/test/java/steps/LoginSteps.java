package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() throws IOException {
        openBrowserAndLaunchApplication();
    }


    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws IOException {
        //WebElement usernameField = driver.findElement(By.xpath("//*[@id='txtUsername']"));
        //WebElement passwordField = driver.findElement(By.xpath("txtPassword"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.usernameField.sendKeys(ConfigReader.read("userName"));
        loginPage.passwordField.sendKeys(ConfigReader.read("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //WebElement loginButton = driver.findElement(By.id("btnLogin"));
        //loginPage.loginButton.click();
        jsClick(loginPage.loginButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        // here we are checking if element exist or not
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());

        String expectedUrl = "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/dashboard";

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        //System.out.println("test passed");
    }
}
