@backend
Feature: Alcohol drinking


  Scenario: User drinking alcohol
    Given user has 10 bottles of whiskey
    When user drinks 1 bottle
    Then user is drunk
    And user has 9 bottles remaining
