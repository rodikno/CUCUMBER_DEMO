Feature: Login functionality

  Scenario Outline: Login with valid credentials
    Given the user is on the login page
    When the user enters the username "<username>" and the password "<password>"
    And clicks the "Login" button
    Then the user should be redirected to the homepage
    And a welcome message should be displayed

    Examples:
      | username   | password  |
      | user1      | pass123   |
      | user2      | secret456 |
      | admin      | adminPass |
