package api_StepDefs;

import java.io.FileNotFoundException;

import api_Actions.Actions_Program;
import api_EnvVariables.EnvConstants;
import api_Utils.LoggerLoad;
import api_Utils.RestUtils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef_Program {
	
	int http_Created = EnvConstants.http_Created;
	int http_OK = EnvConstants.http_OK;
	int http_NotFound = EnvConstants.http_NotFound;
	int http_BadRequest = EnvConstants.http_BadRequest;
	int http_Unauthorized = EnvConstants.http_UnAuthorized;
	int http_MethodNotAllowed = EnvConstants.http_MethodNotAllowed;
	int invalidPrgmID = EnvConstants.invalidprogram_ID;
	String ProgramSchema = EnvConstants.file_Path_Schema_GetProgram;
	String GetAllProgramsSchema = EnvConstants.file_Path_Schema_GetAllPrograms;
	String GetAllProgramsWithAdminsSchema = EnvConstants.file_Path_Schema_GetAllProgramsWithUsers;
	
	String loginEndpoint = EnvConstants.login_Endpoint;
	String createProgramEndpoint = EnvConstants.createProgram_Endpoint;
	String getAllProgramsEndpoint = EnvConstants.getAllPrograms_Endpoint;
	String getAllProgramsWithUsersEndpoint = EnvConstants.getAllProgramsWithUsers_Endpoint;
	String getAllProgramsByProgramIDEndpoint = EnvConstants.getAllProgramByProgramID_Endpoint;
	String deleteByProgramID_Endpoint = EnvConstants.deleteByProgramID_Endpoint;
	String deleteByProgramName_Endpoint = EnvConstants.deleteByProgramName_Endpoint;
	String updateByProgramID_Endpoint = EnvConstants.updateByProgramID_Endpoint;
	String updateByProgramName_Endpoint = EnvConstants.updateByProgramName_Endpoint;
	String logOutEndpoint = EnvConstants.logOut_Endpoint;
	
	String expectedErrorMsg_existingProgramName = EnvConstants.existingProgramNameErrorMsg;
	String deleteInvalidProgramNameExpectedErrorMessage = EnvConstants.deleteInvalidProgramNameErrorMessage;
	Integer updateProgram_ProgramID = EnvConstants.updateProgram_ProgramID;
	String updateProgram_ProgramName = EnvConstants.updateProgram_ProgramName;
	Integer invalid_ProgramID = EnvConstants.invalidprogram_ID;
	String Created = EnvConstants.Created;
	String BadRequest = EnvConstants.BadRequest;
	String OK = EnvConstants.OK;
	String UnAuthorized = EnvConstants.UnAuthorized;
	String NotFound = EnvConstants.NotFound;
	String MethodNotAllowed = EnvConstants.MethodNotAllowed;
	
	RequestSpecification reqSpec;
	Response response;
	Actions_Program actions = new Actions_Program();
	RestUtils restActions = new RestUtils();
	
	@Before
	public void scenario(Scenario scenario) {
		LoggerLoad.info("===============================================================================================");
		LoggerLoad.info(scenario.getSourceTagNames() +" : "+scenario.getName());
		LoggerLoad.info("-----------------------------------------------------------------------------------------------");
		
	}
	
	/* Program_S1 */
	@Given("Admin creates Request for LMS")
	public void admin_creates_request_for_lms() throws FileNotFoundException {
		LoggerLoad.info("Building request to send");
		reqSpec = actions.buildRequest();
	}

	@When("Admin sends HTTPS POST Request to log in LMS with valid endpoint")
	public void admin_sends_https_post_request_to_log_in_lms_with_valid_endpoint() throws FileNotFoundException {
		LoggerLoad.info("Logging in to LMS");
		response = actions.loginToGetAuthorized(reqSpec);
	}

	@Then("Admin receives {int} OK Status with auth token in response body")
	public void admin_receives_ok_status_with_auth_token_in_response_body(Integer int1) throws FileNotFoundException {
		actions.setAuth(response);
		LoggerLoad.info("Validating log in response");
		actions.validateStatusCode(response, http_OK);
		actions.validateStatusLine(response, OK);
		actions.validateResponseTime(response);
	}

	/* Program_S2 */

    @When("Admin sends HTTPS POST Request and request Body with create program endpoint")
    public void admin_sends_https_post_request_and_request_body_with_create_program_endpoint() throws FileNotFoundException {
    	LoggerLoad.info("Creating program with valid request");
    	response = actions.createProgram(reqSpec, "ValidProgram");
    }
    
    @Then("Admin receives {int} Created Status with response body")
    public void admin_receives_created_status_with_response_body(Integer int1) throws FileNotFoundException {
    	LoggerLoad.info("Validating create program response");
    	actions.validateStatusCode(response, http_Created);
    	actions.validateStatusLine(response, Created);
    	actions.setProgramID(response);
    	actions.setProgramName(response);
		actions.validateResponseTime(response);
		actions.validateHeader(response);
		actions.validateResponseBodySchema(response,ProgramSchema);
		actions.validateIfResponseBodyContainsRequestBodyValues(response);
    }
    
    /* Program_S3 */
    @When("Admin sends HTTPS GET Request to retrieve all programs with valid endpoint")
    public void admin_sends_https_get_request_to_retrieve_all_programs_with_valid_endpoint() {
    	LoggerLoad.info("Sending request to get all programs");
    	response = actions.getAllPrograms(reqSpec);
    }

    @Then("Admin receives {int} OK Status with response body having all programs")
    public void admin_receives_ok_status_with_response_body_having_all_programs(Integer int1) {
    	LoggerLoad.info("Validating successful retrieval of all programs");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateStatusLine(response, OK);
    	actions.validateResponseTime(response);
		actions.validateResponseBodySchema(response,GetAllProgramsSchema);
    }

    /* Program_S4 */
    @When("Admin sends HTTPS GET Request to retrieve a program with endpoint and valid program ID")
    public void admin_sends_https_get_request_to_retrieve_a_program_with_endpoint_and_valid_program_id() {
    	LoggerLoad.info("Sending request to get a programs with given programID");
    	response = actions.getProgramByProgramID(reqSpec);
    }
    
    @Then("Admin receives {int} OK Status with response body")
    public void admin_receives_ok_status_with_response_body(Integer int1){
    	LoggerLoad.info("Validating response");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateStatusLine(response, OK);
    	actions.validateResponseTime(response);
		actions.validateResponseBodySchema(response,ProgramSchema);
    }

    /* Program_S5 */
    @When("Admin sends HTTPS GET Request to retrieve all programs with admins with valid endpoint")
    public void admin_sends_https_get_request_to_retrieve_all_programs_with_admins_with_valid_endpoint() {
    	LoggerLoad.info("Sending request to get all programs with admins");
    	response = actions.getAllProgramsWithUsers(reqSpec);
    }
    
    @Then("Admin receives {int} OK Status with response body having all programs with admins")
    public void admin_receives_ok_status_with_response_body_having_all_programs_with_admins(Integer int1){
    	LoggerLoad.info("Validating successful retrieval of all programs with admin");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateStatusLine(response, OK);
    	actions.validateResponseTime(response);
		actions.validateResponseBodySchema(response,GetAllProgramsWithAdminsSchema);
    }

    /* Program_S6 */
    @When("Admin sends HTTPS PUT by ID Request having request Body with mandatory ,additional fields and valid endpoint")
    public void admin_sends_https_put_by_id_request_having_request_body_with_mandatory_additional_fields_and_valid_endpoint() {
        int programID = actions.getProgramID();
        LoggerLoad.info("Sending request to update program by ID "+programID);
        response = actions.updateProgram_AllFields_ByProgramID(reqSpec, programID);
    }

    @Then("Admin receives {int} OK Status with updated value in response body")
    public void admin_receives_ok_status_with_updated_value_in_response_body(Integer int1) {
    	LoggerLoad.info("Validating successful updation of program");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateStatusLine(response, OK);
    	actions.validateResponseTime(response);
    	actions.validateHeader(response);
    	actions.validateResponseBodySchema(response,ProgramSchema);
    }

    /* Program_S7 */
    @When("Admin sends HTTPS PUT by Name Request having valid request Body with mandatory ,additional fields with valid endpoint")
    public void admin_sends_https_put_by_name_request_having_valid_request_body_with_mandatory_additional_fields_with_valid_endpoint() {
    	String programName = actions.getProgramName();
    	LoggerLoad.info("Sending request to update program by program name "+programName);
       response = actions.updateProgram_AllFields_ByProgramName(reqSpec, programName);
    }

    /* Program_S8 */
    @When("Admin sends HTTPS DELETE Request with valid programName and valid endpoint")
    public void admin_sends_https_delete_request_with_valid_program_name_and_valid_endpoint() {
    	LoggerLoad.info("Sending request to delete program by program name ");
    	response = actions.deleteProgramByProgramName(reqSpec);
    }

    @Then("Admin receives {int} Ok status with message")
    public void admin_receives_ok_status_with_message(Integer int1) {
    	LoggerLoad.info("Validating successful deletion of program");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateStatusLine(response, OK);
    	actions.validateResponseTime(response);
    	actions.validateHeader(response);
    }
    
    /* Program_S9 */
    @When("Admin sends HTTPS DELETE Request with valid program ID and valid endpoint")
    public void admin_sends_https_delete_request_with_valid_program_id_and_valid_endpoint() {
    	LoggerLoad.info("Sending request to delete program by program ID ");
    	response = actions.deleteProgramByProgramID(reqSpec);
    }
    
	/* Program_S10_Negative_1 */
	@When("Admin sends HTTPS {string} Request with {string}")
	public void admin_sends_https_request_with(String requestType, String endpointtype) {
		LoggerLoad.info("Sending request to perform operation with invalid endpoint ");
		String endPoint = actions.getEndpoint(endpointtype);
		response = actions.executeRequestWithInvalidEndpoint(requestType, reqSpec, updateProgram_ProgramName, endPoint);
	}

	@Then("Admin receives {int} Not Found")
	public void admin_receives_not_found(Integer int1) {
		LoggerLoad.info("Validating resource not found error");
		actions.validateStatusCode(response, http_NotFound);
		actions.validateStatusLine(response, NotFound);
	}

	/* @Program_S11_Negative_2 */
	@When("Admin sends HTTPS Request with request Body having already existing program name and valid endpoint")
	public void admin_sends_https_request_with_request_body_having_already_existing_program_name_and_valid_endpoint() throws FileNotFoundException {
		LoggerLoad.info("Sending request to create program with existing program name");
		response = actions.executeProgramRequest("createProgram", reqSpec, "InvalidProgramWithAlreadyExistingProgramName", "", createProgramEndpoint);		
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
		LoggerLoad.info("Validating error message with boolean success flag");
		actions.validateStatusCode(response, http_BadRequest);
		actions.validateStatusLine(response, BadRequest);
		actions.validateResponseTime(response);
		actions.validateHeader(response);
		actions.validateErrorMessageAndSuccessFlag(response, expectedErrorMsg_existingProgramName, false);
	}
	/* @Program_S12 */
	@When("Admin sends HTTPS Request and request Body with missing additional field")
	public void admin_sends_https_request_and_request_body_with_missing_additional_field() throws FileNotFoundException {
		LoggerLoad.info("Sending request to create program with missing additional field");
		response = actions.executeProgramRequest("createProgram", reqSpec, "ProgramWithMissingAdditionalField", "", createProgramEndpoint);
	}
	@Then ("Admin receives {int} ok")
	public void admin_receives_ok(Integer int1) {
		LoggerLoad.info("Validating response");
		actions.validateStatusCode(response, http_OK);
		actions.validateStatusLine(response, OK);
		actions.validateResponseTime(response);
		actions.validateHeader(response);
	 }
	
	/* @Program_S13_Negative_3 */
	@When("Admin sends HTTPS invalid method in Request with valid request Body and endpoint")
	public void admin_sends_https_invalid_method_in_request_with_valid_request_body_and_endpoint() throws FileNotFoundException {
		LoggerLoad.info("Sending invalid method");
		response = actions.executeProgramRequest("createProgram", reqSpec, "ValidProgram", "", getAllProgramsEndpoint);
	}
    @Then("Admin receives {int} Method Not Allowed")
    public void admin_receives_method_not_allowed(Integer int1) {
    	LoggerLoad.info("Validating method not allowed error");
    	actions.validateStatusCode(response, http_MethodNotAllowed);
    	actions.validateStatusLine(response, MethodNotAllowed);
    	actions.validateResponseTime(response);
		actions.validateHeader(response);
    }
    
    /* @Program_S14_Negative_4 */
    @When("Admin sends HTTPS POST Request with {string}")
    public void admin_sends_https_post_request_with(String invalidRequestBody) {
    	LoggerLoad.info("Creating program with invalid request body");
    	response = actions.executeProgramRequest("createProgram", reqSpec, invalidRequestBody, "NoParameter", createProgramEndpoint);	   
    }

    @Then("Admin receives {int} Bad Request Status with error message {string} and success flag as false")
    public void admin_receives_bad_request_status_with_error_message_and_success_flag_as_false(Integer int1, String expectedErrorMessage) {
    	LoggerLoad.info("Validating error message with boolean success flag");
    	actions.validateStatusCode(response, http_BadRequest);
    	actions.validateStatusLine(response, BadRequest);
    	actions.validateResponseTime(response);
		actions.validateHeader(response);
		actions.validateErrorMessageAndSuccessFlag(response, expectedErrorMessage, false);
	}
	
	/* @Program_S15_Negative_5 */
	@When("Admin sends HTTPS Request with missing values in the request body and valid endpoint")
	public void admin_sends_https_request_with_missing_values_in_the_request_body_and_valid_endpoint() {
		LoggerLoad.info("Creating program with missing values in request body");
		response = actions.executeProgramRequest("createProgram", reqSpec, "missingValuesInFields", "NoParameter", createProgramEndpoint);	   	
	}

	@Then("Admin receives {int} Bad Request Status")
	public void admin_receives_bad_request_status(Integer int1) {
		LoggerLoad.info("Validating Bad Request error message");
		actions.validateStatusCode(response, http_BadRequest);
		actions.validateStatusLine(response, BadRequest);
    	actions.validateResponseTime(response);
		actions.validateHeader(response);
	}
	
	/* @Program_S16_Negative_6 */
	@When("Admin sends HTTPS GET Request to retrieve {string} with {string} and invalid Method")
	public void admin_sends_https_get_request_to_retrieve_retrievalType_with_endPointType_and_invalid_method(String retrievalType,String endPointType ) {
		LoggerLoad.info("Sending invalid method to perform retrieval");
		String endpoint = actions.getEndpoint(endPointType);
		response = actions.executeProgramRequest("createProgram", reqSpec, "ValidProgram", "NoParameter", endpoint);	   	
	}
   
	/* @Program_S17_Negative_7 */
	@When("Admin sends HTTPS GET Request to retrieve a program with invalid program ID")
	public void admin_sends_https_get_request_to_retrieve_a_program_with_invalid_program_id() {
		LoggerLoad.info("Retrieving program with invalid program ID");
		response = actions.executeProgramRequest("getProgramWithProgramID", reqSpec,"NoRequestBody",invalidPrgmID, getAllProgramsByProgramIDEndpoint);	   		
	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
		LoggerLoad.info("Validating Not Found error message with boolean success flag");
		String expectedErrorMsg = "program with this: "+invalidPrgmID+"not found";
		LoggerLoad.info("Expected error message is : "+expectedErrorMsg);
	    actions.validateStatusCode(response, http_NotFound);
	    actions.validateStatusLine(response, NotFound);
	    actions.validateErrorMessageAndSuccessFlag(response, expectedErrorMsg, false);
		actions.validateResponseTime(response);
		actions.validateHeader(response);
	}
	
	/* @Program_S18_Negative_8 */
	@Given("Admin creates Request with invalid baseURI for LMS")
	public void admin_creates_request_with_invalid_base_uri_for_lms() throws FileNotFoundException {
		LoggerLoad.info("Building request with invalid baseURI");
		//reqSpec = restActions.getRequestSpecWithInvalidBaseURI();
		reqSpec = actions.buildRequestWithInvalidBaseURI();
	}
	
	@When("Admin sends HTTPS Request to {string} with {string} and invalid baseURI")
	public void admin_sends_https_request_to_requesttype_with_endpointtype_and_invalid_base_uri(String requestType,String endPointType) {
		LoggerLoad.info("Sending request with invalid baseURI");
		String endPoint = actions.getEndpoint(endPointType);
		response = actions.executeProgramRequest(requestType, reqSpec, "ValidUpdateProgram", updateProgram_ProgramID, endPoint);
			   
	}
	@Then("Admin receives {int} Not Found with message and boolean success details")
	public void admin_receives_not_found_with_message_and_boolean_success_details(Integer int1) {
		LoggerLoad.info("Validating response of request with invalid baseURI");
		actions.validateStatusCode(response, http_NotFound);
		actions.validateStatusLine(response, NotFound);
	}
	
	/* @Program_S19_Negative_9 */
	@When("Admin sends HTTPS PUT Request with {string}")
    public void admin_sends_https_put_request_with(String invalidRequestBody) {
		LoggerLoad.info("Sending request to update program with invalid request body");
    	response = actions.executeProgramRequest("updateByProgramID", reqSpec, invalidRequestBody, updateProgram_ProgramID, updateByProgramID_Endpoint);	   
    }
	
	/* @Program_S20_Negative_10 */
	@When("Admin sends HTTPS PUT by ID Request without request body and valid endpoint")
	public void admin_sends_https_put_by_id_request_without_request_body_and_valid_endpoint() {
		LoggerLoad.info("Sending request to update program without request body");
		response = actions.executeProgramRequest("updateByProgramID", reqSpec, "NoRequestBody", updateProgram_ProgramID, updateByProgramID_Endpoint);
	}
	
	/* @Program_S21_Negative_11 */
	
    @When("Admin sends HTTPS PUT by ID with invalid method and valid endpoint")
    public void admin_sends_https_put_by_id_with_invalid_method_and_valid_endpoint() {
    	LoggerLoad.info("Sending request to update program  by ID with invalid method");
    	response = actions.executeProgramRequest("getProgramWithProgramID", reqSpec,"ValidUpdateProgram",updateProgram_ProgramID, updateByProgramID_Endpoint);	   		 
    }
    
    /* @Program_S22_Negative_12 */
    
    @When("Admin sends HTTPS DELETE by invalid programName and valid endpoint")
    public void admin_sends_https_delete_by_invalid_program_name_and_valid_endpoint() {
    	LoggerLoad.info("Sending request to delete program by programName : Invalid_ProgramName");
    	response = actions.executeProgramRequest("deleteByProgramName",reqSpec,"NoRequestBody","Invalid_ProgramName",deleteByProgramName_Endpoint);
    }

    @Then("Admin receives {int} Not Found Status for invalid programName and boolean success details")
    public void admin_receives_not_found_status_for_invalid_program_name_and_boolean_success_details(Integer int1) {
    	LoggerLoad.info("Validating response of program deletion by invalid programName");
    	actions.validateStatusCode(response, http_NotFound);
        actions.validateStatusLine(response, NotFound);
    	actions.validateErrorMessageAndSuccessFlag(response, deleteInvalidProgramNameExpectedErrorMessage, false);
        actions.validateResponseTime(response);
        
    }

    /* @Program_S23_Negative_13 */
    
    @When("Admin sends HTTPS DELETE by invalid programID and valid endpoint")
    public void admin_sends_https_delete_by_invalid_program_id_and_valid_endpoint() {
    	LoggerLoad.info("Sending request to delete program by invalid program ID : "+invalid_ProgramID);
    	response = actions.executeProgramRequest("deleteByProgramID",reqSpec,"NoRequestBody",invalid_ProgramID,deleteByProgramID_Endpoint);
    	   
    }

    @Then("Admin receives {int} Not Found Status for invalid programID and boolean success details")
    public void admin_receives_not_found_status_for_invalid_program_id_and_boolean_success_details(Integer int1) {
    	LoggerLoad.info("Validating response of program deletion by invalid program ID");
    	String expectedErrorMessage = "no record found with programId"+invalid_ProgramID;
        actions.validateErrorMessageAndSuccessFlag(response, expectedErrorMessage, false);
        actions.validateResponseTime(response);
        actions.validateStatusCode(response, http_NotFound);
        actions.validateStatusLine(response, NotFound);
    }
   /* @Program_S24_Negative_14 */ 
    @When("Admin sends HTTPS PUT by name Request with {string}")
    public void admin_sends_https_put_by_name_request_with(String invalidRequestBody) {
    	LoggerLoad.info("Sending request to update program having invalid request body");
    	response = actions.executeProgramRequest("updateByProgramName", reqSpec, invalidRequestBody, updateProgram_ProgramName, updateByProgramName_Endpoint);	   
    }
    
    /* @Program_S25 */
    @When("Admin sends PUT Request for the LMS API endpoint and Valid program Name and status")
    public void admin_sends_put_request_for_the_lms_api_endpoint_and_valid_program_name_and_status() {
    	LoggerLoad.info("Sending request to update program status by program name");
    	response = actions.executeProgramRequest("updateByProgramName", reqSpec, "Update_validProgramStatus", updateProgram_ProgramName, updateByProgramName_Endpoint);	   
        
    }
    
	/* Program_S26_Negative_15 */
	@When("Admin sends HTTPS {string} Request with {string} and no authorization")
	public void admin_sends_https_request_type_request_with_endpointtype_and_no_authorization(String requestType, String endpointtype) {
		LoggerLoad.info("Sending request to LMS without authorization");
		String endPoint = actions.getEndpoint(endpointtype);
		response = actions.executeRequestWithNoAuthorization(requestType, reqSpec, 17305, endPoint);
	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
		LoggerLoad.info("Validating request to LMS without authorization");
		actions.validateStatusCode(response, http_Unauthorized);
		actions.validateStatusLine(response, UnAuthorized);
	}
}
