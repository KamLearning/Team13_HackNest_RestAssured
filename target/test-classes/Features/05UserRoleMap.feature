#Author: Sudha
@UserRoleMap
Feature: UserRoleMap

  @login
  Scenario: Admin sets Authorization
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS POST Request to log in LMS with valid endpoint on UserRoleMap
    Then Admin receives 200 OK Status with auth token in response body on UserRoleMap

  @deleteUserRoleMap
  Scenario: Check if admin is able to delete the program batch for a Admin
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS DELETE Request to delete Admin assigned to program/batch by AdminId
    Then Admin receives 200 OK

  @deleteUserRoleMap_NE1
  Scenario: Check if admin is able to delete the program batch for invalid Admin
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS DELETE Request to delete Admin assigned to program/batch by invalid AdminId
    Then Admin receives 404

  @deleteUserRoleMap_NE2
  Scenario: Check if admin is able to delete the program batch for valid Admin and No Authorization
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS DELETE Request to delete Admin assigned to program/batch without authorization by valid AdminId
    Then Admin receives status 401 with Unauthorized message

  @getUserRoleMapByUserId
  Scenario: Check if admin is able to retreive assigned program batches for valid AdminId
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS GET Request to retrieve Admin assigned to Program/Batch by AdminId
    Then Admin receives 200 OK

  @getUserRoleMapByUserId_NE1
  Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS GET Request to retrieve Admin assigned to Program/Batch by invalid AdminID
    Then Admin receives 404

  @getUserRoleMapByUserId_NE2
  Scenario: Check if admin is able to retreive assigned program batches for valid AdminId with No authorization
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends HTTPS GET Request to retrieve Admin assigned to Program/Batch without authorization by valid AdminID
    Then Admin receives status 401 with Unauthorized message

  @getUserRoleMap_S1
  Scenario: Check if admin is able to retreive all Admins with assigned program batches
    Given Admin creates Request for LMS on UserRoleMap
    When Admin sends GET Request to retrieve all Admins assigned to programs batches
    Then Admin receives 200 OK

  @getUserRoleMap_S2
  Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
    Given Admin creates Request for LMS
    When Admin sends HTTPS Get Request with valid endpoint and no authorization
    Then Admin receives 401 Unauthorized for get
