Feature: Employee search related scenarios

  Background:
  Scenario: Valid admin login
    #Given user is navigated to HRMS application
    When user enters valid username and password
    When user clicks on login button
    Then user is successfully logged in

  @employeeSearch @regression @smoke @sprint2 @ana
  Scenario: Search employee by id
    When user enters valid employee id
    And user clicks on search button
    Then user see the employee information
  @regression @sprint1 @sabina
  Scenario: Search employee by name
    When user enters valid employee name
    And user clicks on search button
    Then user see the employee information
