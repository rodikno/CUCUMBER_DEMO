@browser @ui
Feature: Internet Herokuapp Login
  As a user of the Internet Herokuapp
  I want to login to the secure area
  So that I can see the success message

  Background:
    Given I am on the Internet Herokuapp login page

  @positive
  Scenario: Successful login with valid credentials
    When I login with username "tomsmith" and password "SuperSecretPassword!"
    Then I should see a success message containing "You logged into a secure area!"
    And the URL should contain "/secure"

  @negative
  Scenario: Login fails with invalid username
    When I login with username "wrong" and password "SuperSecretPassword!"
    Then I should see an error message containing "Your username is invalid!"
    And the URL should contain "/login"

  @negative
  Scenario: Login fails with invalid password
    When I login with username "tomsmith" and password "wrong"
    Then I should see an error message containing "Your password is invalid!"
    And the URL should contain "/login"
