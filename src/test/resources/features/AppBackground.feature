Feature: App Background and Resume

  Scenario: Background app during passenger details entry
    Given I am on the passenger details screen
    When I enter passenger details with name "John Doe" and age "35"
    And I send the app to background for 30 seconds
    Then I should see the passenger details "John Doe" and "35" preserved