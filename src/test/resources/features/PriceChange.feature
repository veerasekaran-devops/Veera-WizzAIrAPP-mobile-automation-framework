Feature: Handle Price Change During Booking

  Scenario: Price change appears during booking
    Given I have selected flight "BA117" to book
    When I start the booking process
    And a price change modal appears
    Then I handle the price change and continue booking
    And I should reach the payment screen