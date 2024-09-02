Feature: Login functionality

  Scenario Outline: Login with valid credentials
    Given the user is on the login page
    When the user enters the username "<username>" and the password "<password>" and the email "<email>"
    And clicks the "Login" button
    Then the user should be redirected to the homepage
    And a welcome message should be displayed

    Examples:
      | username | password  | email      |
      | user1    | pass123   | sds@ss.com |
      | user2    | secret456 | sds@ss.com |
      | admin    | adminPass | sds@ss.com |
      | aleks    | qwerty123 | sds@ss.com |
