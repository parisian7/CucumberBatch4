#Feature: Validation Of USer Info
#  Scenario: Validation Of UserName
#    Given User requests response with JSON contentType
#    When User deserialize the response with restAssure
#    Then User validate statusCode, ContentType and firstName

  Feature: Getting and deserializing pet from petStore
    Scenario Outline: get pet
      Given contentType header is set to "application/json"
