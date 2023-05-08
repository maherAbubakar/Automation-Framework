@regression
Feature: WebDriver University - Contact Us Page

  Background:
    Given User access the webDriver university contact us page

  Scenario: Validate Successful Submission - Unique Data
    When User enter a unique first name
    And user enter a unique last name
    And User enter unique email address
    And User enter comment
    And User click on submit button
    Then I should be presented with a successful contact us submission message

  Scenario: Validate Successful Submission - Specific Data
    When User enter a specific first name Imran
    And user enter a specific last name "Khan"
    And User enter specific email address "IK@email.com"
    And User enter specific comment "hello PM"
    And User click on submit button
    Then I should be presented with a successful contact us submission message

  Scenario Outline: Validate Successful Submission -  Data
    When User enter a specific first name <fname>
    And user enter a specific last name "<lname>"
    And User enter specific email address "<email>"
    And User enter specific comment "<comment>"
    And User click on submit button
    Then I should be presented with a successful contact us submission message

    Examples:
      | fname  | lname | email | comment |
      | Usman  | Saleem | umaher@maher.com | hello Usmane |
      | Talha  | Amjad | tmajda@maher.com | hello Talha |



