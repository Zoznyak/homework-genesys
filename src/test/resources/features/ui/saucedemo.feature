@ui @sauce
Feature: Sauce demo tests

  Scenario: Cart count updates after adding items
    Given "performance" user is logged in
    When I add items to the cart
    Then the cart shows the number of items placed in it

  Scenario: Purchase is successful
    Given "performance" user is logged in
    And I have items in my cart
    When I complete the purchase
    Then a message about the successful purchase appears

  Scenario: Login fails with empty credentials
    Given the login page is opened
    When I click the login button
    Then a message about missing credentials appears

  Scenario: Successful login redirects to the products page
    Given the login page is opened
    When I log in as a "standard" user
    Then products page is opened
