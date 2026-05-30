@smoke
Feature: SauceDemo inventory functionality

  Background: User navigates to the login page
    Given The user opens the SauceDemo login page

  Scenario: Successful login with valid credentials
    When The user enters username "standard_user" and password "secret_sauce"
    And The user clicks the login button
    Then The user should be redirected to the inventory page
    And The page title should display "Products"
    When The user adds the product "Sauce Labs Backpack" to the cart
    Then The cart is active for the added item with 1

