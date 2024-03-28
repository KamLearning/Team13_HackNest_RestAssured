##Author: Bhagyashri
##Feature: Program 
##Scenario: Positive
@Program_Positive
Feature: Program
 
  @tag1
  Scenario: Admin sets Authorization
    Given Admin creates POST Request to log in LMS 
    When Admin sends HTTPS Request to log in with valid endpoint 
    Then Admin receives 200 OK Status with auth token in response body
    
  