Feature: API testing
  Background:
    Given a JWT bearer token is generated

  @api @createAnEmployee
  Scenario: creating an employee
    Given a request is prepared for creating an employee
    When a POST call is made to create the employee
    Then the status code will be 201 for this call

    @api @getOneEmployee
    Scenario: getting one employee
      Given a request is prepared for getting an employee
      When a GET call is made to get one employee
      Then the status code will be 200 for this call
      And the employee ID should be "110209A"
      And the employee's first name should be "manal"
      And the employee's middle name should be "ms"
      And the employee's last name should be "premium"

      @api @getAllEmployees
      Scenario: getting all employees
        Given a request is prepared for getting all employees
        When a GET call is made to get all employees
        Then the status code will be 200 for this call
        And the connection type will be "Keep-Alive"

      @api @getJobTitles
      Scenario: getting all job titles
        Given a request is prepared for getting all job titles
        When a GET call is made to get all job titles
        Then the status code will be 200 for this call
        And the connection type will be "Keep-Alive"

       @api @updateEmployee
      Scenario: updating employee information
        Given a request is prepared for updating an employee with ID "<employee_id>"
        When a PUT call is made for updating employee with the new details
          | firstName | lastName | middleName | gender | birthday   | status    | jobTitle |
          | Dmytro     | pedrick   | sm          | M      | 2001-06-29 | temporary | admin     |
        Then the status code will be 200 for this call
        And the employee's updated first name should be "Dmytro"
        And the employee's updated last name should be "pedrick"
        And the employee's updated middle name should be "sm"
        And the employee's updated gender should be "M"
        And the employee's updated birthday should be "2001-06-29"
        And the employee's updated status should be "temporary"
        And the employee's updated job title should be "admin"