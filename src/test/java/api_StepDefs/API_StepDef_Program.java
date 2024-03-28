package api_StepDefs;

import java.io.FileNotFoundException;

import api_Actions.Actions_Program;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef_Program {
	
	Actions_Program actions = new Actions_Program();
	RequestSpecification reqSpec;
	Response response;
	
	@Given("Admin creates POST Request to log in LMS")
	public void admin_creates_post_request_to_log_in_lms() {
		reqSpec = actions.buildRequest();
	}

	@When("Admin sends HTTPS Request to log in with valid endpoint")
	public void admin_sends_https_request_to_log_in_with_valid_endpoint() throws FileNotFoundException {
	   response = actions.loginToGetAuthorized(reqSpec);
	}

	@Then("Admin receives {int} OK Status with auth token in response body")
	public void admin_receives_ok_status_with_auth_token_in_response_body(Integer int1) throws FileNotFoundException {
		actions.setAuth(response);
		response.then().assertThat().statusCode(200);
	}

}
