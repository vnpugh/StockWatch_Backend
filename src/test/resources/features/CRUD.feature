Feature: Rest API Functionalities
#  Scenario: Successful User Login and Registration
#    Given a registered user
#    When User enters their email and password
#    Then User logs in successfully

    Scenario: User Can Search for Stocks
     Given a logged-in user
     When a user search for stocks by company or symbol
     Then the stocks are displayed