@random-date-gen
Feature: Generate random dates with different format
  As an User
  I want to be able to generate random dates with different formats
  So I can have random dates generated on given range

  Background:
    Given The user is on the codeBeautify random date generator page

  Scenario Outline:Generation of random dates with Choosing Different Date Formats
    When User enters 5 on input field to generate random dates
    And User selects date time format as "<dateTimeFormat>"
    And User wants to generate the dates within the following range
      | start date          | end date            |
      | 2010-01-01 00:00:00 | 2020-12-31 23:59:59 |
    Then User should see 5 random dates generated
    And User should see date time in selected format
    And User should see generated dates within the selected range
    Examples:
      | dateTimeFormat           |
      | YYYY-MM-DD hh:mm:ss      |
      | YYYY-DD-MM hh:mm:ss      |
      | MM-DD-YYYY hh:mm:ss      |

  Scenario:Generation of random dates By clicking on 'Generate random dates' button
    When User enters 5 on input field to generate random dates
    And User selects date time format as "YYYY-DD-MM hh:mm:ss"
    And User wants to generate the dates within the following range
      | start date          | end date            |
      | 2010-01-01 00:00:00 | 2020-12-31 23:59:59 |
    And User clicks on Generate random dates button
    Then User should see different random dates generated

  Scenario:Generation of random dates By selecting Custom Date Format
    When User enters 5 on input field to generate random dates
    And User selects date time format as "Custom date format"
    And User enter Custom date format as "YYYY-MM-DD hh:mm:ss"
    And User wants to generate the dates within the following range
      | start date          | end date            |
      | 1900-01-01 00:00:00 | 2050-12-31 23:59:59 |
    Then User should see 5 random dates generated
    And User should see date time in selected format
    And User should see generated dates within the selected range

  Scenario: Generation of random dates with Invalid inputs on Number of Dates field
    When User enters 0 on input field to generate random dates
    Then User should see 0 random dates generated
