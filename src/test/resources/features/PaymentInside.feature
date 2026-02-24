Feature: Payment Inside WebView

  Scenario: Complete payment in WebView and return to native app
    Given I have reached the payment step
    When I switch to the WebView context
    And I complete the payment form with card "4111111111111111", expiry "12/26", CVV "123"
    Then I return to the native app
    And I should see the booking confirmation screen