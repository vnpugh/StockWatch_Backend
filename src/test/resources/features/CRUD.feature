Feature: Rest API Functionalities
  Scenario: Successful User Registration
      Given a new user
      When User enters their first name, email and password
      Then User registration is successful

  Scenario: Successful User Login
      Given a registered user
      When User enters their email and password
      Then User logs in successfully

  Scenario: User can create a custom watchlist
    Given a user that is logged in
    When the user creates a new watchlist
    Then a new watchlist for the user is created successfully


  Scenario: User Can Search, Add, And Delete A Stock
     Given a user searches for stocks by entering the company name or symbol
     When the stocks are displayed on the page
     Then user can add a stock to their watchlist successfully
     When a user deletes a stock from their watchlist
     Then the stock is deleted successfully


