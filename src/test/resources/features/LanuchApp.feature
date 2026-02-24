Feature: Launch flight via Deep Link

  Scenario: Deep Link + First Launch + Permissions
    Given the app is installed on the device
    When I launch the app using deep link "myapp://flight/12345"
    And I handle all system permission popups
    Then I should see the flight details screen for flight "12345"
