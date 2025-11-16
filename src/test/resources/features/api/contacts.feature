@api @contacts
Feature: Contacts tests

  Scenario: The first email address contains @
    Given There are contacts in the system
    When I collect all contacts
    Then The first email address contains "@"

