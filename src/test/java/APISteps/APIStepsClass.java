package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIStepsClass {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    RequestSpecification request;
    Response response;
    public static String token;
    static String employee_id;
    public static String emp_firstname;
    public static String emp_lastname;
    public static String emp_middle_name;
    public static String emp_gender;
    public static String emp_birthday;
    public static String emp_status;
    public static String emp_job_title;

//----------------------------------------------- Creating Token Method

    @Given("a JWT bearer token is generated")
    public void a_jwt_bearer_token_is_generated() {
        request = given().
                header("Content-Type","application/json").
                body("{\n" +
                        "  \"email\": \"hrmsb19@test.com\",\n" +
                        "  \"password\": \"hrmtest456\"\n" +
                        "}");

        response = request.when().post("/generateToken.php");

        response.then().assertThat().statusCode(200);
        //store the value of token here
        token = "Bearer "+response.jsonPath().getString("token");
    }
//----------------------------------------------- Create Employee Method

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {

        request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"manal\",\n" +
                        "  \"emp_lastname\": \"premium\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1976-06-16\",\n" +
                        "  \"emp_status\": \"permanent\",\n" +
                        "  \"emp_job_title\": \"QA Manager\"\n" +
                        "}");

    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
        response = request.when().post("/createEmployee.php");
        // System.out.println(response);
        response.prettyPrint();
        employee_id =  response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Then("the status code will be {int} for this call")
    public void the_status_code_will_be_for_this_call(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
//----------------------------------------------- Get One Employee Method
    @Given("a request is prepared for getting an employee")
    public void a_request_is_prepared_for_getting_an_employee() {
        request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);
    }
    @When("a GET call is made to get one employee")
    public void a_get_call_is_made_to_get_one_employee() {
        response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();
    }
    @Then("the employee ID should be {string}")
    public void the_employee_id_should_be(String expectedEmpId) {
        String empId = response.jsonPath().getString("Employee.employee_id");
        Assert.assertEquals(expectedEmpId, empId);

    }
    @Then("the employee's first name should be {string}")
    public void the_employee_s_first_name_should_be(String expectedFirstName) {
        System.out.println(response.getBody().asString());
        response.then().assertThat().
                body("employee.emp_firstname",equalTo(expectedFirstName));
    }
    @Then("the employee's middle name should be {string}")
    public void the_employee_s_middle_name_should_be(String expectedMiddleName) {
        response.then().assertThat().
                body("employee.emp_middle_name",equalTo(expectedMiddleName));
    }
    @Then("the employee's last name should be {string}")
    public void the_employee_s_last_name_should_be(String expectedLastName) {
        response.then().assertThat().
                body("employee.emp_lastname",equalTo(expectedLastName));
    }

//------------------------------------------- Get All Employees Method
    @Given("a request is prepared for getting all employees")
    public void a_request_is_prepared_for_getting_all_employees() {
        request = given().
                header("Content-Type","application/json").
                header("Authorization", token);
    }
    @When("a GET call is made to get all employees")
    public void a_get_call_is_made_to_get_all_employees() {
        Response response = request.when().get("/getAllEmployees.php");
        response.prettyPrint();
    }
    @Then("the connection type will be {string}")
    public void the_connection_type_will_be(String string) {
        response.then().assertThat().header("Connection","Keep-Alive");
    }

//-------------------------------------------- Get All Job Titles Method
    @Given("a request is prepared for getting all job titles")
    public void a_request_is_prepared_for_getting_all_job_titles() {
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization", token);
    }
    @When("a GET call is made to get all job titles")
    public void a_get_call_is_made_to_get_all_job_titles() {
        Response response = request.when().get("/jobTitle.php");
        response.prettyPrint(); // Body is more than 10 KB and can only be viewed in editor.
    }
//------------------------------------------- Update Employee Method

    @Given("a request is prepared for updating an employee with ID {string}")
    public void a_request_is_prepared_for_updating_an_employee_with_id(String empId) {
        employee_id = empId;
        request = given().
                header("Content-Type", "application/json").
                header("Authorization", token);

    }
    @When("a PUT call is made for updating employee with the new details")
    public void a_put_call_is_made_for_updating_employee_with_the_new_details(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated first name should be {string}")
    public void the_employee_s_updated_first_name_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated last name should be {string}")
    public void the_employee_s_updated_last_name_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated middle name should be {string}")
    public void the_employee_s_updated_middle_name_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated gender should be {string}")
    public void the_employee_s_updated_gender_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated birthday should be {string}")
    public void the_employee_s_updated_birthday_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated status should be {string}")
    public void the_employee_s_updated_status_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee's updated job title should be {string}")
    public void the_employee_s_updated_job_title_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}

