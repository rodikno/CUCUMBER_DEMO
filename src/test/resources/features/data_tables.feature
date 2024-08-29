Feature: Magical Creature Adoption

  Background:
    Given the magical creatures are available for adoption

  Scenario: Adopt magical creatures
    Given the following magical creatures are available for adoption:
      | Name            | Type    | Age | SpecialAbility |
      | Puff the Dragon | Dragon  | 150 | Fire Breathing |
      | Whiskers        | Griffin | 7   | Flying         |
      | Sparkle         | Unicorn | 5   | Healing        |
    When the following creatures are adopted:
      | Name            | Adopter |
      | Puff the Dragon | Alice   |
      | Whiskers        | Bob     |
      | Sparkle         | Charlie |
    Then the adoption records should be updated as follows:
      | Name            | Adopter | Status  |
      | Puff the Dragon | Alice   | Adopted |
      | Whiskers        | Bob     | Adopted |
      | Sparkle         | Charlie | Adopted |