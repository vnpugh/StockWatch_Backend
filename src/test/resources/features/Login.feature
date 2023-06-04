

Feature: User Can Successfully Login
  Scenario: Successful User Login
    Given a registered user
    When I enter my valid email and password
    Then I should be logged in successfully

#    Feature: User Can Successfully Register
#    Scenario: Successful User Registration
#     Given a user has a unique email
#     When they enter their email and password
#     Then the password and their information is stored in database








