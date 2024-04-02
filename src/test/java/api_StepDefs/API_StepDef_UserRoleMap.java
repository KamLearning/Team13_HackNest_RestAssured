package api_StepDefs;

import java.io.FileNotFoundException;

import com.aventstack.extentreports.gherkin.model.Scenario;

import api_Actions.Actions_UserRoleMap;
import api_EnvVariables.EnvConstants;
import api_Utils.LoggerLoad;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef_UserRoleMap {
	int http_Created = EnvConstants.http_Created;
	int http_OK = EnvConstants.http_OK;
	int http_NotFound = EnvConstants.http_NotFound;
	int http_BadRequest = EnvConstants.http_BadRequest;
	int http_Unauthorized = EnvConstants.http_UnAuthorized;

	RequestSpecification reqSpec;
	Response response;
	Actions_UserRoleMap urmActions = new Actions_UserRoleMap();

	String GetAllAdminsUserRoleMapSchema = EnvConstants.file_Path_Schema_GetAllAdminsUserRoleMap;

	String OK = EnvConstants.OK_getUserRoleMap;
	String UnAuthorized = EnvConstants.UnAuthorized;

//	@Before
//	public void scenario(Scenario scenario) {
//		LoggerLoad.info("============================================================");
//		//LoggerLoad.info(scenario.getSourseTagNames() + "" + scenario.getName());
//		LoggerLoad.info("------------------------------------------------------------");
//	}

	@Given("Admin creates Request for LMS on UserRoleMap")
	public void admin_creates_request_for_lms_on_user_role_map() {
//		LoggerLoad.info("Building request to send");
		reqSpec = urmActions.buildRequest();
	}

	@When("Admin sends HTTPS POST Request to log in LMS with valid endpoint on UserRoleMap")
	public void admin_sends_https_post_request_to_log_in_lms_with_valid_endpoint_on_user_role_map()
			throws FileNotFoundException {
//		LoggerLoad.info("Logging in to LMS");
		response = urmActions.loginToGetAuthorized(reqSpec);
	}

	@Then("Admin receives {int} OK Status with auth token in response body on UserRoleMap")
	public void admin_receives_ok_status_with_auth_token_in_response_body_on_user_role_map(Integer int1)
			throws FileNotFoundException {
		urmActions.setAuth(response);
//		LoggerLoad.info("Validating log in response");
		urmActions.validateStatusCode(response, http_OK);
		urmActions.validateResponseTime(response);
	}

	@When("Admin sends HTTPS DELETE Request to delete Admin assigned to program\\/batch by AdminId")
	public void admin_sends_https_delete_request_to_delete_admin_assigned_to_program_batch_by_admin_id() {
//		LoggerLoad.info("Send https Delete request for UserRoleMap by userId");
		urmActions.deleteUrpbmByUserId(reqSpec);
	}

	@Then("Admin receives {int} OK")
	public void admin_receives_ok(Integer int1) throws FileNotFoundException {
		urmActions.setAuth(response);
		urmActions.validateStatusCode(response, http_OK);
		urmActions.validateResponseTime(response);
	}

	@When("Admin sends HTTPS DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
	public void admin_sends_https_delete_request_to_delete_admin_assigned_to_program_batch_by_invalid_admin_id() {
		urmActions.deleteUrpbmByInValidUserId(reqSpec);
	}

	@Then("Admin receives {int}")
	public void admin_receives(Integer int1) throws FileNotFoundException {
		urmActions.setAuth(response);
		urmActions.validateStatusCode(response, http_NotFound);
		urmActions.validateResponseTime(response);
	}

	@When("Admin sends HTTPS DELETE Request to delete Admin assigned to program\\/batch without authorization by valid AdminId")
	public void admin_sends_https_delete_request_to_delete_admin_assigned_to_program_batch_without_authorization_by_valid_admin_id() {
		urmActions.deleteUrpbmByUserIdWithoutToken(reqSpec);
	}

	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) throws FileNotFoundException {
		urmActions.setAuth(response);
		urmActions.validateStatusCode(response, http_Unauthorized);
		urmActions.validateResponseTime(response);
	}

	@When("Admin sends HTTPS GET Request to retrieve Admin assigned to Program\\/Batch by AdminId")
	public void admin_sends_https_get_request_to_retrieve_admin_assigned_to_program_batch_by_admin_id() {
		urmActions.getUrmByUserId(reqSpec);
	}

	@When("Admin sends HTTPS GET Request to retrieve Admin assigned to Program\\/Batch by invalid AdminID")
	public void admin_sends_https_get_request_to_retrieve_admin_assigned_to_program_batch_by_invalid_admin_id() {
		urmActions.getUrmByInvalidUserId(reqSpec);
	}

	@When("Admin sends HTTPS GET Request to retrieve Admin assigned to Program\\/Batch without authorization by valid AdminID")
	public void admin_sends_https_get_request_to_retrieve_admin_assigned_to_program_batch_without_authorization_by_valid_admin_id() {
		urmActions.getUrmByUserIdWithoutToken(reqSpec);
	}

	@When("Admin sends GET Request to retrieve all Admins assigned to programs batches")
	public void admin_sends_get_request_to_retrieve_all_admins_assigned_to_programs_batches()
			throws FileNotFoundException {
//		LoggerLoad.info("Sending request to retrieve all Admins assigned to programs batches");
		response = urmActions.getAllProgramBatches_AllAdmins(reqSpec);
	}

	@Then("Admin receives {int} OK")
	public void Admin_receives_ok(Integer int1) {
//		LoggerLoad.info("Validating retrieve all Admins assigned to programs batches");
		urmActions.validateStatusCode(response, http_OK);
		urmActions.validateStatusLine(response, OK);
		urmActions.validateResponseTime(response);
		urmActions.validateResponseBodySchema(response, GetAllAdminsUserRoleMapSchema);
	}

	@When("Admin sends HTTPS Get Request with valid endpoint and no authorization")
	public void admin_sends_https_get_request_with_valid_endpoint_and_no_authorization() {
//		LoggerLoad.info("Sending request to retrieve all Admins assigned to programs batches with no auth");
		response = urmActions.getAllProgramBatches_AllAdmins_NoAuth(reqSpec);
	}

	@Then("Admin receives {int} Unauthorized for get")
	public void admin_receives_unauthorized_for_get(Integer int1) {
//		LoggerLoad.info("Validating retrieve all Admins assigned to programs batches with no auth");
		// actions.validateStatusCode(response,http_OK);
		urmActions.validateStatusLine(response, UnAuthorized);
		urmActions.validateResponseTime(response);
	}

}
