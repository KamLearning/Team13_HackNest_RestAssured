@ProgramBatch_Positive_Scenarios
Feature: Program Batch

  @tag1
  Scenario: Admin sets Authorization
    Given Admin creates Request for LMS for Batch
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint for Batch
    Then Admin receives 200 OK Status with auth token in response body for Batch

  @tag2
  Scenario: Check if admin able to create a Batch with
           valid endpoint and request body (non existing values)
    Given Admin creates POST Request  with valid data in request body for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 201 Created Status with response body for Batch


  @tag3
  Scenario: Check if admin able to create a batch with missing additional fields
    Given Admin creates POST Request with missing additional fields for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 201 Created Status with response body for Batch for batch

  @tag4
  Scenario: Check if admin able to retrieve all batches  with valid LMS API
    Given Admin creates GET Request for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with response body for Batch

  @tag5
  Scenario: Check if admin able to retrieve all batches with search string
    Given Admin creates GET Request with search string for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with response body for Batch

  @tag6
  Scenario: Check if admin able to retrieve a batch with valid BATCH ID
    Given Admin creates GET Request with valid Batch ID 
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with response body for Batch

  @tag7
  Scenario: Check if admin able to retrive a batch after deleting the batch
    Given Admin creates GET Request with valid Batch ID
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body for batch

  @tag8
  Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
    Given Admin creates GET Request with valid Batch Name
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 Ok Status with response body for Batch

  @tag9
  Scenario: Check if admin able to retrive a deleted batch  using batch name
    Given Admin creates GET Request with batch Name
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body for batch

  @tag10
  Scenario: Check if admin able to retrieve a batch with valid Program ID
    Given Admin creates GET Request with valid Program Id for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with response body for Batch

  @tag11
  Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
    Given Admin creates PUT Request with valid BatchId and Data for batch
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with updated value in response body for Batch

  @tag12
  Scenario: Check if admin able to update a Batch with a deleted batchID in the endpoint
    Given Admin creates PUT Request with deleted batch Id
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 OK Status with response body for Batch

  @tag13
  Scenario: Check if admin able to delete a Batch with valid Batch ID
    Given Admin creates DELETE Request with valid BatchId
    When Admin sends HTTPS Request with endpoint for batch
    Then Admin receives 200 Ok status with message for Batch 
