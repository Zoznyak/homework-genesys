@ui @saucedemo
Feature: Sauce demo tests

  Scenario: Cart count updates after adding items
    Given The main page is opened
    When I add items to the cart
    Then The cart shows the number of items placed in it

  Scenario: Purchase is successful
    Given The main page is opened
    And I have items in my cart
    When I complete the purchase
    Then A message about the successful purchase appears

  Scenario: Login fails with empty credentials
    Given The login page is opened
    When I click the login button
    Then A message about missing credentials appears
