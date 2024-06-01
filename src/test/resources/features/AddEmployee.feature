Feature: Adding employees using different techniques

  Background:
    When user enters valid username and password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add Employee option


  @addEmployee
  Scenario: Adding an employee in HRMS system
    When user enters firstname and middlename and lastname
    And user clicks on save button
    Then employee added successfully

  @cucumber
  Scenario: Adding employee from feature file
    When user enters "Sheila" , "MS" and "Bangal"
    And user clicks on save button
    Then employee added successfully

  @ddt
  Scenario Outline: : Adding employees using data driven testing
    When user enters "<firstName>" and "<middleName>" and "<lastName>".
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      |Fouad      |MS          |Oliinyk   |
      |Matt       |MS          |Aslloun   |
      |Lali       |MS          |Shahad    |

