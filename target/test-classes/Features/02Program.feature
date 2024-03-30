##Author: Bhagyashri
##Feature: Program 
##Scenario: Positive
@Program_Positive
Feature: Program
 
  @tag1
  Scenario: Admin sets Authorization
    Given Admin creates Request for LMS 
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint 
    Then Admin receives 200 OK Status with auth token in response body
    
  @tag2
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates Request for LMS
    When Admin sends HTTPS POST Request and request Body with create program endpoint
    Then Admin receives 201 Created Status with response body
    
  @tag3
  Scenario: Check if Admin able to retrieve all programs with valid Endpoint
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve all programs with valid endpoint 
    Then Admin receives 200 OK Status with response body having all programs
  
  @tag4
  Scenario: Check if Admin able to retrieve a program with valid program ID
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve a program with endpoint and valid program ID
    Then Admin receives 200 OK Status with response body 
 	
 	@tag5
  Scenario: Check if Admin able to retrieve all programs with admins with valid Endpoint
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve all programs with admins with valid endpoint
    Then Admin receives 200 OK Status with response body having all programs with admins
    
  @tag6
  Scenario: Check if Admin able to update a program with valid programID endpoint and valid request body
    Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT Request having request Body with mandatory ,additional fields and valid endpoint
    Then Admin receives 200 OK Status with updated value in response body
    
  @tag7
  Scenario: Check if Admin able to update a program with valid program Name and request body
    Given Admin creates Request for LMS
    When Admin sends HTTPS PUT Request having valid request Body with mandatory ,additional fields with valid endpoint
    Then Admin receives 200 OK Status with updated value in response body
  
  @tag8
  Scenario: Check if Admin able to delete a program with valid programName
    Given Admin creates Request for LMS
    When Admin sends HTTPS DELETE Request with valid programName and valid endpoint
    Then Admin receives 200 Ok status with message
 
 @tag9
  Scenario: Check if Admin able to delete a program with valid program ID
    Given Admin creates Request for LMS
    When Admin sends HTTPS DELETE Request with valid program ID and valid endpoint
    Then Admin receives 200 Ok status with message  
    
 @tag10
  Scenario: Check if Admin able to log out
    Given Admin creates Request for LMS 
    When Admin sends HTTPS LogOut Request with valid endpoint
    Then Admin is log out successfully 
                                                                                                                                                                                                                                                