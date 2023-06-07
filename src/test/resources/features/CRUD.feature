Feature: Rest API Functionalities
  Scenario: Successful User Login and Registration
    Given a registered user
    When User enters their email and password
    Then User logs in successfully

#    Scenario: User Can Search, Add, Delete A Stock from Their Watchlist
#     Given a logged-in user
#     When a user searches for stocks by company or symbol
#     Then the stocks are displayed
#     When a user adds a stock to their watchlist by symbol
#     Then the stock is added to the user's watchlist successfully
#     When a user deletes a stock from their watchlist by symbol
#     Then the stock is deleted successfully

