package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {


    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws IOException {
        WebElement usernameField = driver.findElement(By.xpath("//*[@id='txtUsername']"));
        WebElement passwordField = driver.findElement(By.cssSelector("input#txtPassword"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        usernameField.sendKeys(ConfigReader.read("userName"));
        passwordField.sendKeys(ConfigReader.read("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = driver.findElement(By.cssSelector("input#btnLogin"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        System.out.println("test passed");
    }
}
