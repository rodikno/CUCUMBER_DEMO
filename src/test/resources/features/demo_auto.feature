Feature: Demonstration for Auto QA lesson

  Scenario: Cukes on the table
    Given Aleks has 2 cucumbers
    When Aleks eats 1 cucumber
    # The step below will fail as the expectation deliberately doesn't match the actual result (1 cucumber remaining)
    # Made for the demo purpose
    Then Aleks should have 10 cucumber remaining