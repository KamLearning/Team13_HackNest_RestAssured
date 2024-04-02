Feature: User Module

@baseauth
Scenario: Admin sets Authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint user 
    Then Admin receives 200 OK Status with auth token in response body user 
    
    
@Positive @auth @getall @GetAllActiveUsers 
Scenario: Check if admin is able to retrieve all Admin with valid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
     
      | @Positive @auth @getall |@GetAllActiveUsers|
    Then Admin receives 200 OK              


@Negative @noauth @getall @GetAllActiveUsers
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 

|@Negative @noauth @getall|@GetAllActiveUsers|
    Then Admin receives status 401 with Unauthorized message


@Negative @invalidEndpoint @getall @GetAllActiveUsers
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 

|@Negative @invalidEndpoint @getall|@GetAllActiveUsers|
   Then Admin receives status 404 with Not Found error message

@Positive @auth @getall @GetCountOfActiveInactive 
Scenario: Check if admin is able to retrieve all Admin with valid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
     
      | @Positive @auth @getall|@GetCountOfActiveInactive|
    Then Admin receives 200 OK              


@Negative @noauth @getall @GetCountOfActiveInactive
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 

|@Negative @noauth @getall|@GetCountOfActiveInactive|
    Then Admin receives status 401 with Unauthorized message


@Negative @invalidEndpoint @getall @GetCountOfActiveInactive
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 

|@Negative @invalidEndpoint @getall|@GetCountOfActiveInactive|
   Then Admin receives status 404 with Not Found error message

@Positive @auth @getall @GetAllUsers
Scenario: Check if admin is able to retrieve all Admin with valid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Positive @auth @getall | @GetAllUsers |
    Then Admin receives 200 OK              

@Negative @noauth @getall @GetAllUsers
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Negative @noauth @getall | @GetAllUsers |
    Then Admin receives status 401 with Unauthorized message

@Negative @invalidEndpoint @getall @GetAllUsers
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Negative @invalidEndpoint @getall| @GetAllUsers |
    Then Admin receives status 404 with Not Found error message

@Positive @auth @getall @GetAllRoles 
Scenario: Check if admin is able to retrieve all Admin with valid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Positive @auth @getall| @GetAllRoles |
    Then Admin receives 200 OK              

@Negative @noauth @getall @GetAllRoles
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Negative @noauth @getall| @GetAllRoles |
    Then Admin receives status 401 with Unauthorized message

@Negative @invalidEndpoint @getall @GetAllRoles
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
      | @Negative @invalidEndpoint @getall | @GetAllRoles |
    Then Admin receives status 404 with Not Found error message


@Positive @auth @getall @GetAllUsersWithRoles 
Scenario: Check if admin is able to retrieve all Admin with valid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
    | @Positive @auth @getall | @GetAllUsersWithRoles |
    Then Admin receives 200 OK              

@Negative @noauth @getall @GetAllUsersWithRoles
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
    | @Negative @noauth @getall| @GetAllUsersWithRoles |
    Then Admin receives status 401 with Unauthorized message

@Negative @invalidEndpoint @getall @GetAllUsersWithRoles
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
    | @Negative @invalidEndpoint @getall| @GetAllUsersWithRoles |
    Then Admin receives status 404 with Not Found error message

@Positive 
Scenario Outline: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields
Given Admin creates POST request with all mandatory fields "<excel>""<sheetName>"
When Admin sends HTTPS Request with endpoint for create
Then Admin receives 201 Created Status with response body.    


Examples:
|excel|sheetName|
|excelFilePath|sheetName|





@Positive @auth @GetUserbyID @UId
Scenario: Check if admin able to retrieve a Admin with valid Admin ID
   Given Admin creates GET Request with valid AdminId
   When Admin sends HTTPS Request with endpoint 
    | @Positive @auth @UId| @GetUserbyID| @userId|
    Then Admin receives 200 OK 
    
 @Negative @noauth @GetUserbyID @UId
Scenario: Check if admin able to retrieve one active Admins with no authorization
    Given Admin creates GET Request with valid AdminId
    When Admin sends HTTPS Request with endpoint
    | @Negative @noauth @UId| @GetUserbyID |@userId|
    Then Admin receives status 401 with Unauthorized message

@Negative @invalidEndpoint @GetUserbyID @UId
Scenario: Check if admin able to retrieve one active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When  Admin sends HTTPS Request with endpoint 
    | @Negative @invalidEndpoint @UId| @GetUserbyID |@userId|
    Then Admin receives status 404 with Not Found error message

@Positive @auth @getRId @GetCountOfActiveInactive 
Scenario: Check if admin is able to get count of active and inactive Admins by role id
    Given Admin creates GET Request with role id
    When Admin sends HTTPS Request with endpoint  
     
      | @Positive @auth @getRId|@GetCountOfActiveInactive|@R02|
    Then Admin receives 200 OK 


@Negative @auth @getRId @GetCountOfActiveInactive 
Scenario: Check if admin is able to get count of active and inactive Admins by invalid role ID 		
    Given Admin creates GET Request with invalid role id
    When Admin sends HTTPS Request with endpoint 
     
      | @Positive @auth @getRId|@GetCountOfActiveInactive|@R09|
    Then Admin receives status 404 with Not Found error message
    
@Positive @auth @getall @GetUsersByFilter 
Scenario: Check if admin is able to retrieve all Admins with filters
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for  
     
      | @Positive @auth @getall|@GetUsersByFilter|
    Then Admin receives 200 OK
    
   @Negative @noauth @getall @GetUsersByFilter
Scenario: Check if admin able to retrieve all active Admins with no authorization
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
    | @Negative @noauth @getall| @GetUsersByFilter |
    Then Admin receives status 401 with Unauthorized message

@Negative @invalidEndpoint @getall @GetUsersByFilter
Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates Request for LMS user
    When Admin sends HTTPS Request with endpoint for 
    | @Negative @invalidEndpoint @getall| @GetUsersByFilter |
    Then Admin receives status 404 with Not Found error message
    
@Positive @putById
Scenario Outline: Check if admin is able to update role id of a Admin by valid Admin id
Given Admin creates PUT Request with valid request body "<excel>""<sheetName>"
When Admin sends HTTPS Request with endpoint for putById	
Then Admin receives 200 OK  Status with response body     


Examples:
|excel|sheetName|
|excelFilePath|sheetName|

@Positive @auth @GetUserByProg @UId
Scenario: Check if admin able to retrieve a Admin with valid Admin ID
   Given Admin creates GET Request with valid AdminId
   When Admin sends HTTPS Request with endpoint 
    | @Positive @auth @UId| @GetUserByProg| @userId|
    Then Admin receives 200 OK 
    
 
















