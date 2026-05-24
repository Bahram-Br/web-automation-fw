Feature: Login test cases for Orange HRM app

Scenario: Login with valid username and password
Given user enters valid username
And user enters valid password
And user clicks on login button
Then home page should be displayed