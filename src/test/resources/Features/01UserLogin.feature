Feature: User LogIn and User logout

  @user_Login_Post @tag1
  Scenario Outline: Admin able to generate token with valid credential
    
    Given Admin creates request with valid credentials for userLoginPost
    When Admin calls Post Https method  with valid endpoint for userLoginPost
    Then Admin receives 200 created with auto generated token for userLoginPost
   
  @tag2
  Scenario Outline: Admin unable to generate token with invalid endpoint
    Given Admin creates request with valid credentials for userLoginPost
    When Admin calls Post Https method  with invalid endpoint " <invalid_endpoint> "
    Then Admin receives 401 unauthorized

    Examples: 
     | invalid_endpoint |
      | logs             |

  @tag3
  Scenario Outline: Admin unable to generate token with invalid credentials
    Given Admin creates request with invalid UserLoginEmailid  "<invalid_userLoginEmailid>" and invalid Password "<invalid_password>"
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 401 Unauthorized
    
   
   @user_Logout_Get
   @tag4
  Scenario Outline: Admin able to logout
       # Admin sets authorization to bearer Token with token
    Given Admin creates request to Logout
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 200 ok and response with "Logout successful"
     
         
   @tag5
  Scenario Outline: Admin unable to logout with invalid endpoint
   #  Admin sets authorization to bearer Token with token
    Given Admin creates request to Logout
    When Admin calls Get Https method with invalid endpoint " <invalid_endpoint> "
    Then Admin receives 404 not found

  

  @tag6
  Scenario Outline: Admin unable to logout with no authentication
       # Admin sets No Authorization
    Given Admin creates request to Logout with No Auth
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 401 unauthorized