Feature: Scenarios to test back end APIS

  Scenario: Check the response from jsonplaceholder/comments and esure email address is Eliseo@gardner.biz
    When a GET request is sent to "http://jsonplaceholder.typicode.com/comments/1"
    Then the status code is 200
    And the response contains the email address "Eliseo@gardner.biz"


  Scenario: Check if it is possible to test soap with rest assured
    Given the SOAP service "http://services.aonaware.com" exists
    When I POST a test payload containing the word "hand"
    Then the response contains definitions of the word "hand"
