Feature: Pet addition
  Scenario: Validation pet addition
    When user send a POST request to create a pet 5146 , "Berk", "illegal entry"
    Then the status code is twoHundred
    And pet with id,name status is created 5146 , "Berk", "illegal entry"