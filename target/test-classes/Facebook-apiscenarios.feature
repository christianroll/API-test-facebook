
Feature: Facebook API

  Scenario: Validate token
    Given User needs a token
    When User wants a request to "https://graph.facebook.com/me"
    And User performs a get request
    Then The response code should be 200

    Scenario: Post in User timeline
      Given User needs a token
      When User wants a request to "https://graph.facebook.com/feed"
      And Passing the body message
      |message|ABC|
      And User performs a post request
      Then The response code should be 200


  Scenario: Update Post from timeline
    Given User needs a token
    When User wants a request to "https://graph.facebook.com/feed"
    And Passing the body message
      |message|CACO|
    And User performs a post request
    Then Saves the id from post response

    Given User needs a token
    When User wants a request to "https://graph.facebook.com/" for update previous post
    And Passing the body message
      |message|updated|
    And User performs a post request

