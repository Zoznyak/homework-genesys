@ui @saucedemo
Feature: Sauce demo tests

  Scenario: Cart count updates after adding items
    Given The application is opened
    When I add items to the cart
    Then The cart shows the number of items placed in it

  Scenario: Purchase is successful
    Given The application is opened
    And I have items in my cart
    When I complete the purchase
    Then A message about the successful purchase appears
