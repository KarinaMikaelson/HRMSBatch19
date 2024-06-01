Feature: Login related scenarios

  @login @smoke @sprint1 @karina @luckyCharm
  Scenario: Valid admin login
    #Given user is navigated to HRMS application
    When user enters valid username and password
    When user clicks on login button
    Then user is successfully logged in

  @featurefile
  Scenario: Valid admin login using feature file
    When user enters "admin" value and "Hum@nhrm123" value
    And user clicks on login button
    Then user is successfully logged in

  @datadriven
  Scenario Outline: login multiple times
    When user enters "<username>" values and "<password>" values
    And user clicks on login button
    Then user is successfully logged in
    Examples:
      | username | password |
      |admin     |Hum@nhrm123|
      |admin     |Hum@nhrm123|
      |admin     |Hum@nhrm123|