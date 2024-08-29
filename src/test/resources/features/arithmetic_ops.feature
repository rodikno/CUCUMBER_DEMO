Feature: Arithmetic Operations

  # Given, When, Then, And, But
  Scenario: Add two numbers
    Given the first number is 5
    And the second number is 7
    When the user adds the two numbers
    Then the result should be 12

  Scenario: Subtract two numbers
    Given the first number is 15
    And the second number is 8
    When the user subtracts the second number from the first
    Then the result should be 7