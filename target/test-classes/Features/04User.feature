@Team13_HackNest_RestAssured
Feature: Creating User with Role : Post Request

  Background: Admin sets authorization to bearer token 1
  
  Background: Admin sets authorization to no auth 2
  
  @Post_S1
  Scenario: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields
    Given: Admin creates POST request with all mandatory fields 
    When: Admin sends HTTPS Request with endpoint
    Then: Admin receives 201 Created Status with response body


  @Post_S2
  Scenario: Check if admin able to create a new Admin with valid endpoint and request body with mandatory and additional fields
    Given: Admin creates POST request with all mandatory fields and additional fields
    When: Admin sends HTTPS Request with endpoint
    Then: Admin receives 201 Created Status with response body

  @Post_NE_S1
  Scenario: Check if admin is able to create a Admin with valid endpoint and invalid values in request body
    Given: Admin creates POST request with all mandatory fields and additional fields
    When: Admin sends HTTPS Request with endpoint
    Then: Admin receives 400 Bad Request Status with message and boolean success details


  @Post_NE_S2
  Scenario: Check if Admin able to create a Admin missing mandatory fields in request body
    Given: Admin creates POST request with missing mandatory fields in request body
    When: Admin sends HTTPS Request with endpoint
    Then: Admin receives 400 Bad Request Status with error message


  @Post_NE_S3
  Scenario: Check if admin able to create a new Admin with request body without authorization
    Given: Admin creates POST request with all mandatory fields and additional fields
    When: Admin sends HTTPS Request with endpoint
    Then: Admin receives status 401 with Unauthorized message

@GetAllRoles_S1
Scenario: Check if admin is able to retreive all the available roles
Given: Admin creates GET Request
When: Admin sends HTTPS Request with GET All Roles endpoint
Then: Admin receives 200 OK

@GetAllRoles_NE_S1
Scenario: Check if admin is able to retreive all the available roles without Authorization
Given: Admin creates GET Request
When: Admin sends HTTPS Request with GET All Roles endpoint
Then: Admin receives status 401 with Unauthorized message

@GetAllRoles_NE_S2
Scenario: Check if admin is able to retreive all the available roles with invalid End point
Given: Admin creates GET Request
When: Admin sends HTTPS Request with invalid endpoint
Then:Admin receives status 404 with Not Found error message

@GetAllUsers_S1
Scenario: Check if admin able to retrieve all Admin with valid endpoint
Given: Admin creates GET Request
When: Admin sends HTTPS Request with valid endpoint
Then: Admin receives 200 OK Status with response body                                                                  

@GetAllUsers_NE_S1
Scenario: Check if admin able to retrieve all Admin without Authorization
Given: Admin creates GET Request
When: Admin sends HTTPS Request with valid endpoint
Then: Admin receives status 401 with Unauthorized message

@GetAllUsers_NE_S2
Scenario: Check if admin able to retrieve all Admin with invalid endpoint 
Given: Admin creates GET Request
When: Admin sends HTTPS Request with invalid endpoint
Then: Admin receives status 404 with Not Found error message

@GetProgramBatch_S1
Scenarios: Check if admin is able to get the Admins by program batches for valid batch ID
Given: Admin creates GET Request with valid batch Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives 200 OK

@GetProgramBatch_NE_S1
Scenarios: Check if admin is able to get the Admins by program batches for invalid batch ID
Given: Admin creates GET Request  with invalid batchId
When: Admin sends HTTPS Request with endpoint
Then: Admin receives 404

@GetProgramBatch_NE_S2
Scenarios: Check if admin is able to get the Admins by program batches for valid batch ID with no authorization
Given: Admin creates GET Request with valid batch Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives status 401 with Unauthorized message

@GetProgramBatch_NE_S3
Scenarios: Check if admin is able to get the Admins by program batches for valid batch ID with invalid endpoint
Given: Admin creates GET Request with valid batch Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives status 404 with Not Found error message

@GetProgram_S1
Scenarios: Check if admin is able to get the Admins for valid program Id
Given: Admin creates GET Request with valid program Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives 200 OK

@GetProgram_NE_S1
Scenarios: Check if admin is able to get the Admins for invalid program Id
Given: Admin creates GET Request with invalid program Id
When: Admin sends HTTPS Request with endpoint
Then: Admin gets 404

@GetProgram_NE_S2
Scenarios: Check if admin is able to get the Admins for valid program Id without authorization
Given: Admin creates GET Request with valid program Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives status 401 with Unauthorized message

@GetProgram_NE_S3
Scenarios: Check if admin is able to get the Admins for valid program Id with invalid endpoint
Given: Admin creates GET Request with valid program Id
When: Admin sends HTTPS Request with endpoint
Then: Admin receives status 404 with Not Found error message

@GetProgram_S1
Scenarios:
Given:
When:
Then: 

