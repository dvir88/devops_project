Feature: Web Server Behavior
  Scenario: Retrieving logs from the server
    When I request the "/logs" route
    Then the response should have a status code of 200
    And the response should contain at least one log
    And each log should have a valid ID, time, date, and method

  Scenario: Creating a new transaction
    When I request the "/addLog" route
    Then the response should have a status code of 200
    And a new log should be created
