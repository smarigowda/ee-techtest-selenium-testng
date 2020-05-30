Feature: Hotel Booking
  Scenario: User books hotel with deposit
    Given User opens the application
    When User provides all the details with deposit true
    And User Saves the booking
    Then Booking should be saved successfully