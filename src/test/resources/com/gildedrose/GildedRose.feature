Feature: Gilded Rose quality
  I want to know if the quality is updated properly

  Scenario: Checking foo
    Given The item <an item>
      | name                   | sellIn | quality |
      | +5 Dexterity Vest      | 10     | 20      |
      | Elixir of the Mongoose | 5      | 7       |
    When I update the quality
    Then I should get item as <an expected item>
      | name                   | sellIn | quality |
      | +5 Dexterity Vest      | 9      | 19      |
      | Elixir of the Mongoose | 4      | 6       |
