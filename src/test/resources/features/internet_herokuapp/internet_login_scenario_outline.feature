# Demonstrates: Background + Scenario Outline + tagged Examples + reuse of existing step definitions
Feature: Internet Herokuapp Login via Scenario Outlines
  As a student, I want to see how Scenario Outline and tagged Examples work
  So that I can cover multiple credential combinations succinctly

  # Background runs before every scenario (and every example row in outlines)
  Background:
    Given I am on the Internet Herokuapp login page

  @smoke @outline @positive
  Scenario Outline: Successful login with multiple valid users
    When I login with username "<username>" and password "<password>"
    Then I should see a success message containing "You logged into a secure area!"
    And the URL should contain "/secure"

    # You can tag a specific Examples block. Tags on Examples only affect the rows in that table
    @examples:valid
    Examples: Valid users
      | username  | password               |
      | tomsmith  | SuperSecretPassword!   |

  @regression @outline @negative
  Scenario Outline: Unsuccessful login with data-driven errors
    When I login with username "<username>" and password "<password>"
    Then I should see an error message containing "<expectedMessage>"
    And the URL should contain "/login"

    @examples:invalid-usernames
    Examples: Invalid usernames
      | username | password             | expectedMessage               |
      | wrong    | SuperSecretPassword! | Your username is invalid!     |
      | tom      | SuperSecretPassword! | Your username is invalid!     |

    @examples:invalid-passwords
    Examples: Invalid passwords
      | username | password | expectedMessage              |
      | tomsmith | wrong    | Your password is invalid!    |
      | tomsmith | 123      | Your password is invalid!    |
