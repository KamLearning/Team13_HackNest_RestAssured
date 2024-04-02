package api_StepDefs;

import java.io.FileNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import api_Actions.Actions_ProgBatch;
import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef_ProgramBatch {
	int http_Created = EnvConstants.http_Created;
	int http_OK = EnvConstants.http_OK;
	int http_NotFound = EnvConstants.http_NotFound;
	int http_BadRequest = EnvConstants.http_BadRequest;
	int http_Unauthorized = EnvConstants.http_UnAuthorized;

	static RequestSpecification reqSpec;
	static Response response;
	
	Actions_ProgBatch actionBatch = new Actions_ProgBatch();

	@Given("Admin creates Request for LMS for Batch")
	public void admin_creates_request_for_lms_for_batch() {
		reqSpec = actionBatch.buildRequest();
	}

	@When("Admin sends HTTPS POST Request to log in LMS with valid endpoint for Batch")
	public void admin_sends_https_post_request_to_log_in_lms_with_valid_endpoint_for_batch()
			throws FileNotFoundException {
		response = actionBatch.loginToGetAuthorized(reqSpec);
	}

	@Then("Admin receives {int} OK Status with auth token in response body for Batch")
	public void admin_receives_ok_status_with_auth_token_in_response_body_for_batch_for_batch(Integer int1)
			throws FileNotFoundException {
		actionBatch.setAuth(response);
		actionBatch.validateStatusCode(response, http_OK);
		actionBatch.validateResponseTime(response);
	}

	@Given("Admin creates POST Request  with valid data in request body for batch")
	public void admin_creates_post_request_with_valid_data_in_request_body_for_batch() throws FileNotFoundException {
		response =	actionBatch.createBatch(reqSpec, "ValidBatch");		
	}

	@When("Admin sends HTTPS Request with endpoint for batch")
	public void admin_sends_https_request_with_endpoint_for_batch() throws FileNotFoundException {
		
	}

	@Then("Admin receives {int} Created Status with response body for Batch")
	public void admin_receives_created_status_with_response_body_for_batch(Integer int1) throws FileNotFoundException {
		actionBatch.validateStatusCode(response, http_Created);
		actionBatch.SetbatchId(response);
		
	}

	@Given("Admin creates POST Request with missing additional fields for batch")
	public void admin_creates_post_request_with_missing_additional_fields_for_batch() throws FileNotFoundException {
		response =	actionBatch.createBatch(reqSpec, "ValidBatch");		
		
	}

	@Then("Admin receives {int} Created Status with response body for Batch for batch")
	public void admin_receives_created_status_with_response_body_for_batch_for_batch(Integer int1)
			throws FileNotFoundException {
 	}

	@Given("Admin creates GET Request for batch")
	public void admin_creates_get_request_for_batch() throws FileNotFoundException {
		response = actionBatch.getAllbatchs(reqSpec);
		
	}

	@Then("Admin receives {int} OK Status with response body for Batch")
	public void admin_receives_ok_status_with_response_body_for_batch(Integer status) throws FileNotFoundException {
		actionBatch.validateStatusCode(response, http_OK);
	}

	@Given("Admin creates GET Request with search string for batch")
	public void admin_creates_get_request_with_search_string_for_batch() throws FileNotFoundException {
		response = actionBatch.getbatchByName(reqSpec);
	}

	@Given("Admin creates GET Request with valid Batch ID")
	public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
		response = actionBatch.getbatchBybatchId(reqSpec);
	}

	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body for batch")
	public void admin_receives_ok_status_with_batch_status_field_in_the_response_body_for_batch(Integer int1,
			String string) throws FileNotFoundException {

	}
 
	@Given("Admin creates GET Request with valid Batch Name")
	public void admin_creates_get_request_with_valid_batch_name() throws FileNotFoundException {
		response = actionBatch.getbatchByName(reqSpec);
	}

	@Given("Admin creates GET Request with batch Name")
	public void admin_creates_get_request_with_batch_name() throws FileNotFoundException {
		response = actionBatch.getbatchByName(reqSpec);
	}

	@Given("Admin creates GET Request with valid Program Id for batch")
	public void admin_creates_get_request_with_valid_program_id_for_batch() throws FileNotFoundException {
		response = actionBatch.getbatchByProgramId(reqSpec);
	}

	@Given("Admin creates PUT Request with valid BatchId and Data for batch")
	public void admin_creates_put_request_with_valid_batch_id_and_data_for_batch() throws FileNotFoundException, JsonMappingException, JsonProcessingException {
		// Write code here that turns the phrase above into concrete actions
//		response = actionBatch.updateBatch(reqSpec);
	}

	@Then("Admin receives {int} OK Status with updated value in response body for Batch")
	public void admin_receives_ok_status_with_updated_value_in_response_body_for_batch(Integer int1)
			throws FileNotFoundException {
		
	}

	@Given("Admin creates PUT Request with deleted batch Id")
	public void admin_creates_put_request_with_deleted_batch_id() throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions
		;
	}

	@Given("Admin creates DELETE Request with valid BatchId")
	public void admin_creates_delete_request_with_valid_batch_id() throws FileNotFoundException {
		response = actionBatch.deletebatchBybatchId(reqSpec);
	}

	@Then("Admin receives {int} Ok status with message for Batch")
	public void admin_receives_ok_status_with_message_for_batch(Integer int1) throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions
		;
	}

}
