@regression
Feature: WebDriver University - Login Page

  Background:
    Given user the WebDriver university login page

  Scenario: Validate Successful Login
    When user enters the username "webdriver"
    And user enters the password webdriver123
    And user clicks on login button
    Then user should be presented with successful login message

#Not Working
  Scenario: Validate Unsuccessful Login
    When user enters the username "webdriver"
    And user enters the password webdriver555
    And user clicks on login button
    Then user should be presented with unsuccessful login message



