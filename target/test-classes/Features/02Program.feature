##Author: Bhagyashri
##Feature: Program 
##Scenario: Positive and Negative
@Program
Feature: Program
 
  @Program_S1
  Scenario: Admin sets Authorization
    Given Admin creates Request for LMS 
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint 
    Then Admin receives 200 OK Status with auth token in response body
    
<<<<<<< HEAD
  @tag2
=======
  @Program_S2
>>>>>>> master
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates Request for LMS
    When Admin sends HTTPS POST Request and request Body with create program endpoint
    Then Admin receives 201 Created Status with response body
    
<<<<<<< HEAD
  @tag3
=======
  @Program_S3
>>>>>>> master
  Scenario: Check if Admin able to retrieve all programs with valid Endpoint
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve all programs with valid endpoint 
    Then Admin receives 200 OK Status with response body having all programs
  
<<<<<<< HEAD
  @tag4
=======
  @Program_S4
>>>>>>> master
  Scenario: Check if Admin able to retrieve a program with valid program ID
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve a program with endpoint and valid program ID
    Then Admin receives 200 OK Status with response body 
 	
<<<<<<< HEAD
 	@tag5
=======
 	@Program_S5
>>>>>>> master
  Scenario: Check if Admin able to retrieve all programs with admins with valid Endpoint
    Given Admin creates Request for LMS
    When Admin sends HTTPS GET Request to retrieve all programs with admins with valid endpoint
    Then Admin receives 200 OK Status with response body having all programs with admins
    
<<<<<<< HEAD
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
=======
  @Program_S6
  Scenario: Check if Admin able to update a program with valid programID endpoint and valid request body
    Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT by ID Request having request Body with mandatory ,additional fields and valid endpoint
    Then Admin receives 200 OK Status with updated value in response body
    
  @Program_S7
  Scenario: Check if Admin able to update a program with valid program Name and request body
    Given Admin creates Request for LMS
    When Admin sends HTTPS PUT by Name Request having valid request Body with mandatory ,additional fields with valid endpoint
    Then Admin receives 200 OK Status with updated value in response body
  
  @Program_S8
>>>>>>> master
  Scenario: Check if Admin able to delete a program with valid programName
    Given Admin creates Request for LMS
    When Admin sends HTTPS DELETE Request with valid programName and valid endpoint
    Then Admin receives 200 Ok status with message
 
<<<<<<< HEAD
 @tag9
=======
 @Program_S9
>>>>>>> master
  Scenario: Check if Admin able to delete a program with valid program ID
    Given Admin creates Request for LMS
    When Admin sends HTTPS DELETE Request with valid program ID and valid endpoint
    Then Admin receives 200 Ok status with message  
<<<<<<< HEAD
    
 @tag10
  Scenario: Check if Admin able to log out
    Given Admin creates Request for LMS 
    When Admin sends HTTPS LogOut Request with valid endpoint
    Then Admin is log out successfully 
=======
   
 @Program_S10_Negative_1
  Scenario Outline: Check if Admin able to perform operation on programAPI with invalid endpoint
    Given Admin creates Request for LMS 
    When Admin sends HTTPS "<requestType>" Request with "<endpointtype>" 
    Then Admin receives 404 Not Found
  Examples:
  | requestType							| endpointtype 		|
  | getAllPrograms 					|invalidEndpoint	|
  | getAllProgramsWithUsers |invalidEndpoint	|
  | getProgramWithProgramID |invalidEndpoint	|
  | createProgram 					|invalidEndpoint	|
  | updateByProgramID 			|invalidEndpoint	|
  | updateByProgramName 		|invalidEndpoint	|		
  | deleteByProgramID 			|invalidEndpoint	|
  | deleteByProgramName 		|invalidEndpoint	|
  
  @Program_S11_Negative_2
  Scenario: Check if Admin able to create a program with already existing program name 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS Request with request Body having already existing program name and valid endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details
  
  @Program_S12
  Scenario: Check if Admin able to create a program with missing additional field 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS Request and request Body with missing additional field
    Then Admin receives 200 ok 
  
  @Program_S13_Negative_3  
  Scenario: Check if Admin able to create a program with invalid method 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS invalid method in Request with valid request Body and endpoint
    Then Admin receives 405 Method Not Allowed  
  
  @Program_S14_Negative_4
  Scenario Outline: Check if Admin able to create a program with invalid request body
    Given Admin creates Request for LMS 
    When Admin sends HTTPS POST Request with "<invalidRequestBody>" 
    Then Admin receives 400 Bad Request Status with error message "<expectedErrorMessage>" and success flag as false
  Examples:
  | invalidRequestBody| expectedErrorMessage 	|
  | numbersInPrgramName 	|programName must begin with alphabet and can contain only alphanumeric characters	|
  | numbersInPrgramDescription |programDescription must begin with alphabet and can contain only alphanumeric characters	|
  | invalidProgramStatus |Invalid Status: must be Active or Inactive	|
  | specialCharactersInProgramName|programName must begin with alphabet and can contain only alphanumeric characters	|
  | specialCharactersInProgramDescription|programDescription must begin with alphabet and can contain only alphanumeric characters |
  
 @Program_S15_Negative_5  
  Scenario: Check if Admin able to create a program with missing values in the request body 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS Request with missing values in the request body and valid endpoint
    Then Admin receives 400 Bad Request Status 
     
 @Program_S16_Negative_6  
  Scenario Outline: Check if Admin able to retrieve all programs with invalid Method 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS GET Request to retrieve "<retrievalType>" with "<endPointType>" and invalid Method
    Then Admin receives 405 Method Not Allowed 
  Examples: 
 	|retrievalType 					| endPointType 									|
 	|getAllPrograms					|getAllProgramsEndpoint					|				
 	|getAllProgramsWithUsers|getAllProgramsWithUsersEndpoint|
 
 @Program_S17_Negative_7  
  Scenario: Check if Admin able to retrieve a program with invalid program ID 
    Given Admin creates Request for LMS 
    When Admin sends HTTPS GET Request to retrieve a program with invalid program ID
    Then Admin receives 404 Not Found Status with message and boolean success details 
  
 @Program_S18_Negative_8  
  Scenario Outline: Check if Admin able to execute request on program API with invalid baseURI 
    Given Admin creates Request with invalid baseURI for LMS 
    When Admin sends HTTPS Request to "<requestType>" with "<endPointType>" and invalid baseURI
    Then Admin receives 404 Not Found with message and boolean success details  
   Examples: to retrieve a program
   |requestType						 |endpointtype										 |
   |getProgramWithProgramID|getAllProgramsByProgramIDEndpoint|
   |updateByProgramID			 |updateByProgramID_Endpoint			 |

@Program_S19_Negative_9  
  Scenario Outline: Check if Admin able to update a program by ID with invalid request body
    Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT Request with "<invalidRequestBody>" 
    Then Admin receives 400 Bad Request Status with error message "<expectedErrorMessage>" and success flag as false
  Examples:
  | invalidRequestBody| expectedErrorMessage 	|
  | Update_numbersInPrgramName 	|programName must begin with alphabet and can contain only alphanumeric characters	|
  | Update_numbersInPrgramDescription |programDescription must begin with alphabet and can contain only alphanumeric characters	|
  | Update_invalidProgramStatus |Invalid Status: must be Active or Inactive	|
  | Update_specialCharactersInProgramName|programName must begin with alphabet and can contain only alphanumeric characters	|
  | Update_specialCharactersInProgramDescription|programDescription must begin with alphabet and can contain only alphanumeric characters |

@Program_S20_Negative_10 
	Scenario: Check if Admin able to update a program without request body 
	  Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT by ID Request without request body and valid endpoint
    Then Admin receives 400 Bad Request Status 


@Program_S21_Negative_11 
	Scenario: Check if Admin able to update a program with invalid method 
	  Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT by ID with invalid method and valid endpoint
    Then Admin receives 405 Method Not Allowed  

@Program_S22_Negative_12
  Scenario: Check if Admin able to delete a program with valid LMS API,invalid programName 
	  Given Admin creates Request for LMS 
    When Admin sends HTTPS DELETE by invalid programName and valid endpoint
    Then Admin receives 404 Not Found Status for invalid programName and boolean success details   
 
 @Program_S23_Negative_13
  Scenario: Check if Admin able to delete a program with valid LMS API,invalid programID 
	  Given Admin creates Request for LMS 
    When Admin sends HTTPS DELETE by invalid programID and valid endpoint
    Then Admin receives 404 Not Found Status for invalid programID and boolean success details 
 
 @Program_S24_Negative_14  
  Scenario Outline: Check if Admin able to update a program by Name with invalid request body
    Given Admin creates Request for LMS 
    When Admin sends HTTPS PUT by name Request with "<invalidRequestBody>" 
    Then Admin receives 400 Bad Request Status with error message "<expectedErrorMessage>" and success flag as false
  Examples:
  | invalidRequestBody| expectedErrorMessage 	|
  | Update_numbersInPrgramName 	|programName must begin with alphabet and can contain only alphanumeric characters	|
  | Update_numbersInPrgramDescription |programDescription must begin with alphabet and can contain only alphanumeric characters	|
  | Update_invalidProgramStatus |Invalid Status: must be Active or Inactive	|
  | Update_specialCharactersInProgramName|programName must begin with alphabet and can contain only alphanumeric characters	|
  | Update_specialCharactersInProgramDescription|programDescription must begin with alphabet and can contain only alphanumeric characters |
 
 @Program_S25
 Scenario: Check if Admin able to update a program status by programName
    Given Admin creates Request for LMS 
    When Admin sends PUT Request for the LMS API endpoint and Valid program Name and status
    Then Admin receives 200 OK Status with updated value in response body                                           
 
 @Program_S26_Negative_15
  Scenario Outline: Check if Admin able to perform operation on programAPI without Authorization
    Given Admin creates Request for LMS 
    When Admin sends HTTPS "<requestType>" Request with "<endpointtype>" and no authorization
    Then Admin receives 401 Unauthorized
  Examples:
  | requestType							| endpointtype 										|
  | getAllPrograms 					|getAllProgramsEndpoint						|
  | getAllProgramsWithUsers |getAllProgramsWithUsersEndpoint	|
  | getProgramWithProgramID |getAllProgramsByProgramIDEndpoint|
  | createProgram 					|createProgramEndpoint						|
  | updateByProgramID 			|updateByProgramID_Endpoint				|
  | updateByProgramName 		|updateByProgramName_Endpoint			|		
  | deleteByProgramID 			|deleteByProgramID_Endpoint				|
  | deleteByProgramName 		|deleteByProgramName_Endpoint			|
>>>>>>> master
                                                                                                                                                                                                                                                