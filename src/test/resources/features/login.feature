Feature: SauceDemo Login Functionality

  Background: User navigates to the login page
    Given The user opens the SauceDemo login page

  # Scenario 1: Basic validation using standard valid credentials
  Scenario: Successful login with valid credentials
    When The user enters username "standard_user" and password "secret_sauce"
    And The user clicks the login button
    Then The user should be redirected to the inventory page
    And The page header should display "Swag Labs"

  # Scenario 2: Data-driven validation handling various error conditions
  Scenario Outline: Failed login with invalid or restrictive credentials
    When The user enters username "<username>" and password "<password>"
    And The user clicks the login button
    Then An error message containing "<error_message>" should be visible

    Examples:

      | username        | password     | error_message                                              |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.        |
      | standard_user   | wrong_pass   | Epic sadface: Username and password do not match any user  |
      | invalid_user    | secret_sauce | Epic sadface: Username and password do not match any user  |