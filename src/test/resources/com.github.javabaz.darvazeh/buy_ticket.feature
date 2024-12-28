Feature: user want to buy tickets

#  Background: The application is reachable and healthy
#    Given The application is available at "http://localhost:8080"
#    And Health check is fine at "/actuator/health"

  @RunMe
  Scenario: open the page and click on the buy tickets button
    Given user open site and click on the buy tickets button
    When we have  no tickets
    Then give exception