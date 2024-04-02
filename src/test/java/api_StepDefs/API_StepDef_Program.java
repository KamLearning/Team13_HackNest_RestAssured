package api_StepDefs;

import java.io.FileNotFoundException;

import api_Actions.Actions_Program;
import api_EnvVariables.EnvConstants;
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
	
	String ProgramSchema = EnvConstants.file_Path_Schema_GetProgram;
	String GetAllProgramsSchema = EnvConstants.file_Path_Schema_GetAllPrograms;
	String GetAllProgramsWithAdminsSchema = EnvConstants.file_Path_Schema_GetAllProgramsWithUsers;
	
	RequestSpecification reqSpec;
	Response response;
	Actions_Program actions = new Actions_Program();
	
	
	/* Scenario 1 */
	@Given("Admin creates Request for LMS")
	public void admin_creates_request_for_lms() {
		reqSpec = actions.buildRequest();
	}

	@When("Admin sends HTTPS POST Request to log in LMS with valid endpoint")
	public void admin_sends_https_post_request_to_log_in_lms_with_valid_endpoint() throws FileNotFoundException {
	   response = actions.loginToGetAuthorized(reqSpec);
	}

	@Then("Admin receives {int} OK Status with auth token in response body")
	public void admin_receives_ok_status_with_auth_token_in_response_body(Integer int1) throws FileNotFoundException {
		actions.setAuth(response);
		actions.validateStatusCode(response, http_OK);
		actions.validateResponseTime(response);
	}

	/* Scenario 2 */

    @When("Admin sends HTTPS POST Request and request Body with create program endpoint")
    public void admin_sends_https_post_request_and_request_body_with_create_program_endpoint() throws FileNotFoundException {
    	response = actions.createProgram(reqSpec, "ValidProgram");
    }
    
    @Then("Admin receives {int} Created Status with response body")
    public void admin_receives_created_status_with_response_body(Integer int1) throws FileNotFoundException {
    	actions.validateStatusCode(response, http_Created);
    	actions.setProgramID(response);
    	actions.setProgramName(response);
		actions.validateResponseTime(response);
		actions.validateResponseBodySchema(response,ProgramSchema);
    }
    
    /* Scenario 3 */
    @When("Admin sends HTTPS GET Request to retrieve all programs with valid endpoint")
    public void admin_sends_https_get_request_to_retrieve_all_programs_with_valid_endpoint() {
        response = actions.getAllPrograms(reqSpec);
    }

    @Then("Admin receives {int} OK Status with response body having all programs")
    public void admin_receives_ok_status_with_response_body_having_all_programs(Integer int1) {
    	actions.validateStatusCode(response, http_OK);
    	actions.validateResponseTime(response);
		//actions.validateResponseBodySchema(response,GetAllProgramsSchema);
    }

    /* Scenario 4 */
    @When("Admin sends HTTPS GET Request to retrieve a program with endpoint and valid program ID")
    public void admin_sends_https_get_request_to_retrieve_a_program_with_endpoint_and_valid_program_id() {
    	response = actions.getProgramByProgramID(reqSpec);
    }
    
    @Then("Admin receives {int} OK Status with response body")
    public void admin_receives_ok_status_with_response_body(Integer int1){
    	actions.validateStatusCode(response, http_OK);
    	actions.validateResponseTime(response);
		//actions.validateResponseBodySchema(response,ProgramSchema);
    }

    /* Scenario 5 */
    @When("Admin sends HTTPS GET Request to retrieve all programs with admins with valid endpoint")
    public void admin_sends_https_get_request_to_retrieve_all_programs_with_admins_with_valid_endpoint() {
    	response = actions.getAllProgramsWithUsers(reqSpec);
    }
    
    @Then("Admin receives {int} OK Status with response body having all programs with admins")
    public void admin_receives_ok_status_with_response_body_having_all_programs_with_admins(Integer int1){
    	actions.validateStatusCode(response, http_OK);
    	actions.validateResponseTime(response);
		//actions.validateResponseBodySchema(response,GetAllProgramsWithAdminsSchema);
    }

    /* Scenario 6 */
    @When("Admin sends HTTPS PUT Request having request Body with mandatory ,additional fields and valid endpoint")
    public void admin_sends_https_put_request_having_request_body_with_mandatory_additional_fields_and_valid_endpoint() {
        
    }

    @Then("Admin receives {int} OK Status with updated value in response body")
    public void admin_receives_ok_status_with_updated_value_in_response_body(Integer int1) {
        
    }

    /* Scenario 7 */
    @When("Admin sends HTTPS PUT Request having valid request Body with mandatory ,additional fields with valid endpoint")
    public void admin_sends_https_put_request_having_valid_request_body_with_mandatory_additional_fields_with_valid_endpoint() {
       
    }

    /* Scenario 8 */
    @When("Admin sends HTTPS DELETE Request with valid programName and valid endpoint")
    public void admin_sends_https_delete_request_with_valid_program_name_and_valid_endpoint() {
    	response = actions.deleteProgramByProgramName(reqSpec);
    }

    @Then("Admin receives {int} Ok status with message")
    public void admin_receives_ok_status_with_message(Integer int1) {
    	System.out.println("Status code is : "+response.getStatusCode()+"Expected is 200");
    	actions.validateStatusCode(response, http_OK);
    	actions.validateResponseTime(response);
    }
    
    /* Scenario 9 */
    @When("Admin sends HTTPS DELETE Request with valid program ID and valid endpoint")
    public void admin_sends_https_delete_request_with_valid_program_id_and_valid_endpoint() {
    	response = actions.deleteProgramByProgramID(reqSpec);
    }
    
    /* Scenario 10 */
    @When("Admin sends HTTPS LogOut Request with valid endpoint")
    public void admin_sends_https_log_out_request_with_valid_endpoint() {
        
    }

    @Then("Admin is log out successfully")
    public void admin_is_log_out_successfully() {
        
    }
    	
}
