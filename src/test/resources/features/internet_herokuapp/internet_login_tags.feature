# Demonstrates: Feature-level tags, Scenario tags, and selective execution by tag
@browser @ui @internet @login
Feature: Internet Herokuapp Login with tagging strategies
  Teams can slice the suite by tags to form smoke/regression suites or to focus work-in-progress.

  Background:
    Given I am on the Internet Herokuapp login page

  @smoke @positive
  Scenario: Happy path login
    When I login with username "tomsmith" and password "SuperSecretPassword!"
    Then I should see a success message containing "You logged into a secure area!"
    And the URL should contain "/secure"

  @regression @negative
  Scenario: Invalid username shows a clear error
    When I login with username "wrong" and password "SuperSecretPassword!"
    Then I should see an error message containing "Your username is invalid!"
    And the URL should contain "/login"

  @regression @negative @wip
  Scenario: Invalid password shows a clear error
    When I login with username "tomsmith" and password "wrong"
    Then I should see an error message containing "Your password is invalid!"
    And the URL should contain "/login"

  # Example tag expressions to run:
  # mvn -Dtest=runners.TestNGRunner test -Dcucumber.filter.tags="@smoke"
  # mvn -Dtest=runners.TestNGRunner test -Dcucumber.filter.tags="@regression and not @wip"
