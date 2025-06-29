Feature: Example  Feature
    Scenario: Basic Cucumber Setup
		Given test cucumber basic setup.

	Scenario: Valid login attempt
		When user is on the login page
		And user enters valid credentials
		And user clicks on the login button
		Then user should be redirected to the dashboard

	Scenario: Invalid Login Attempt
		When user is on the login page
		And user enters invalid credentials
		And user clicks on the login button
		Then user should see an error message
		