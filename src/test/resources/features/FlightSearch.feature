Feature: Flight Search with Infinite Scroll

  Scenario: Search for flight and verify it appears in infinite scroll
    Given the app is launched
    When I search for flights from "New York" to "London" on "2026-03-01"
    And I scroll through the flight results
    Then I should see flight "BA117" in the search results