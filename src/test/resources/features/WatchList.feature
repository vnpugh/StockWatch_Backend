Feature: Watchlist Management
#
#  Background:
#    Given a registered user
#
#  Scenario: Can successfully view all stocks
#    When the user can view all stocks on their watchlist
#    Then the API should return a list of stocks on the watchlist
#
#  Scenario: User can create a custom watchlist
#    When the user requests to create a new watchlist
#    Then the API should create a new watchlist for the user and return it with HTTP status 201
#
  Scenario: User can add a stock to their watchlist by ticker symbol

    When the user adds a stock to their watchlist by symbol
    Then the stock is added to the user's watchlist

#  Scenario: User can update the name of a watchlist
#    Given the user has a watchlist with ID "1"
#    When the user requests to update the name of the watchlist with the new name "My New Watchlist"
#    Then the API should update the name of the watchlist and return it with HTTP status 200

#  Scenario: User can delete a stock from their watchlist
#    Given the user has a watchlist with a stock with symbol "AAPL"
#    When the user requests to delete the stock from their watchlist with the symbol "AAPL"
#    Then the API should delete the stock from the user's watchlist and return the updated watchlist
