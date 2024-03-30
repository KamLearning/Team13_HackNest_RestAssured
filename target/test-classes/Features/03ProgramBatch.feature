@ProgramBatch_Positive_Scenarios
Feature: Program Batch

  @tag1
  Scenario: Admin sets Authorization
    Given Admin creates Request for LMS
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint
    Then Admin receives 200 OK Status with auth token in response body

  @tag2
  Scenario: Check if admin able to create a Batch with
           valid endpoint and request body (non existing values)

    Given Admin creates POST Request  with valid data in request body
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body.

  @tag3
  Scenario: Check if admin able to create a batch with missing additional fields
    Given Admin creates POST Request with missing additional fields
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body

  @tag4
  Scenario: Check if admin able to retrieve all batches  with valid LMS API
    Given Admin creates GET Request
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body

  @tag5
  Scenario: Check if admin able to retrieve all batches with search string
    Given Admin creates GET Request with search string
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 Ok status with response body

  @tag6
  Scenario: Check if admin able to retrieve a batch with valid BATCH ID
    Given Admin creates GET Request with valid Batch ID
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body

  @tag7
  Scenario: Check if admin able to retrive a batch after deleting the batch
    Given Admin creates GET Request with valid Batch ID
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body

  @tag8
  Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
    Given Admin creates GET Request with valid Batch Name
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body

  @tag9
  Scenario: Check if admin able to retrive a deleted batch  using batch name
    Given Admin creates GET Request with batch Name
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body

  @tag10
  Scenario: Check if admin able to retrieve a batch with valid Program ID
    Given Admin creates GET Request with valid Program Id
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body

  @tag11
  Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
    Given Admin creates PUT Request with valid BatchId and Data
    When Admin sends HTTPS Request  with endpoint
    Then Admin receives 200 OK Status with updated value in response body

  @tag12
  Scenario: Check if admin able to update a Batch with a deleted batchID in the endpoint
    Given Admin creates PUT Request with deleted batch Id
    When Admin sends HTTPS Request  with endpoint
    Then Admin receives 200 Ok status with message

  @tag13
  Scenario: Check if admin able to delete a Batch with valid Batch ID
    Given Admin creates DELETE Request with valid BatchId
    When Admin sends HTTPS Request  with endpoint
    Then Admin receives 200 Ok status with message
