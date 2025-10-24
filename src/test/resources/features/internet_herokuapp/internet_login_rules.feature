# Demonstrates: Rule sections + Rule-scoped Backgrounds + reuse of same steps
Feature: Internet Herokuapp Login organized by business rules
  In order to keep scenarios focused, we can group them into rules

  # Note: Background can also be placed inside each Rule to scope setup

  Rule: Valid credentials allow access
    Background:
      Given I am on the Internet Herokuapp login page

    @positive @smoke
    Scenario: Tom logs in successfully
      When I login with username "tomsmith" and password "SuperSecretPassword!"
      Then I should see a success message containing "Welcome to the Secure Area. When you are done click logout below."
      And the URL should contain "/secure"

  Rule: Invalid credentials are rejected
    Background:
      Given I am on the Internet Herokuapp login page

    @negative
    Scenario: Wrong username is rejected
      When I login with username "wrong" and password "SuperSecretPassword!"
      Then I should see an error message containing "Your username is invalid!"
      And the URL should contain "/login"

    @negative @edgecase
    Scenario: Wrong password is rejected
      When I login with username "tomsmith" and password "wrong"
      Then I should see an error message containing "Your password is invalid!"
      And the URL should contain "/login"
