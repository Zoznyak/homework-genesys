@ui @html
Feature: Html editor tests

  Scenario: Rich Text Editor
    Given the user is on the HTML editor page
    When the user enters text with mixed formatting
    Then the editor content should contain the complete text "Automation Test Example"
    And the sub-string "Automation" should be bolded
    And the sub-string "Example" should be underlined

