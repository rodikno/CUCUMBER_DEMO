Feature: Cookie Eating Contest

  Background:
    Given the contest has started
    And there are 100 cookies available on the table

  Scenario: Alice eats cookies
    When Alice eats 5 cookies
    Then 95 cookies should be remaining
    And Alice should feel happy

  Scenario: Bob eats cookies
    When Bob eats 10 cookies
    Then 90 cookies should be remaining
    And Bob should feel even happier

  Scenario: Charlie tries to eat more cookies than available
    When Charlie eats 101 cookies
    Then an error message should be displayed saying "Not enough cookies!"
