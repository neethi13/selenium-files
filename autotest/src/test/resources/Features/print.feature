@tag
Feature: Print values
  print the index of BSE and NSE

  @tag1
  Scenario: Print the values of BSE and NSE
    Given open browser
    And navigates to url
    When user performs required action
    Then successful validation and close browser
	@tag2  
    Scenario: Print the values of BSE and NSE
    Given open browser
    And navigates to url
    When user prints href
    Then successful validation and close browser