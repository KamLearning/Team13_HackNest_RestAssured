Feature: User Login
Background: User sets Authorization to No Auth
Scenario Outline: Check if Admin able to generate token with valid credential
Given Admin creates request with valid credentials "<password>" "<username>"
When Admin calls Post Https method with valid endpoint
Then Admin receives 201 created with auto generated token

Examples:
|password|username|
|password|numpyninja@gmail.com|
