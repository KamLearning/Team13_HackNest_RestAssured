package api_StepDefs;

import java.io.FileNotFoundException;

import api_Actions.Actions_UserLogin;
import api_EnvVariables.EnvConstants;
import api_POJO.API_Pojo_UserLogin;
import api_Utils.LoggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef_UserLogin extends EnvConstants{
	
	

	RequestSpecification reqSpec;
	Response response;
	//API_Pojo_UserLogin ul;
	Actions_UserLogin actions= new Actions_UserLogin();

	//@tag1
	@Given("Admin creates request with valid credentials for userLoginPost")
	public void admin_creates_request_with_valid_credentials_for_user_login_post() {
		LoggerLoad.info("Create request to login");
		reqSpec = actions.buildRequest();

	   
	}

	@When("Admin calls Post Https method  with valid endpoint for userLoginPost")
	public void admin_calls_post_https_method_with_valid_endpoint_for_user_login_post() throws FileNotFoundException {
		LoggerLoad.info("Logging in to userlogin");
  		response = actions.validEndPoint(reqSpec);

 
	}

	@Then("Admin receives {int} created with auto generated token for userLoginPost")
	public void admin_receives_created_with_auto_generated_token_for_user_login_post(Integer int1) throws FileNotFoundException {
		response = actions.login(reqSpec);
		actions.setAuth(response);
		LoggerLoad.info("Validatind userlogin response");
		actions.validateStatusCode(response, http_OK);
		actions.validateResponseTime(response);
		actions.validateResponseBodySchema(response,EnvConstants.file_Path_Schema_Login);
		
	}
	
	
	//@tag2
	
	@When("Admin calls Post Https method  with invalid endpoint {string}")
	public void admin_calls_post_https_method_with_invalid_endpoint(String string) {
		LoggerLoad.info("Logging with invalid endpoint");
       response = reqSpec.post("invalid_endpoint");
	   
	}

	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
		actions.validateStatusCode(response, http_UnAuthorized);
		LoggerLoad.info("Validing invalid endpoint response");
		actions.validateResponseTime(response);
        System.out.println(response.asString());
       
	    
	}
 
	//@tag3
	@Given("Admin creates request with invalid UserLoginEmailid  {string} and invalid Password {string}")
	public void admin_creates_request_with_invalid_user_login_emailid_and_invalid_password(String string, String string2) {

		reqSpec = actions.buildRequest(); 
		response = actions.testInvalidCredentials(reqSpec);
		LoggerLoad.info("Logging with invalid credentials");

	   
	}
	
	@When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_post_https_method_with_valid_endpoint(){
		reqSpec = actions.buildRequest(); 
   		LoggerLoad.info("Logging with valid endpoint");
		 response = actions.validEndPoint(reqSpec);
	   
	}
	
	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_bad_request(Integer int1) throws FileNotFoundException {
		       LoggerLoad.info("Validing the response with invalid credentials");
		        actions.validateStatusCode(response, http_UnAuthorized);
				actions.validateResponseTime(response);
	
	}
					
	//@tag4
	@Given("Admin creates request to Logout")
	public void admin_creates_request_to_logout() throws FileNotFoundException {
		LoggerLoad.info("Request to logout ");
		reqSpec = actions.buildRequest();
		//response = actions.getAuth(reqSpec);
	
	}

	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_get_https_method_with_valid_endpoint() throws FileNotFoundException {
		LoggerLoad.info("Request to logout with valid endpoint ");
		//response = actions.getAuth(reqSpec);
		
		response = reqSpec.get("logout_Endpoint");
	
	}
	@Then("Admin receives {int} ok and response with {string}")
	public void admin_receives_ok_and_response_with(Integer int1, String string) {
		actions.validateStatusCode(response, http_OK);
		actions.validateResponseTime(response);
		 System.out.println(response.asString());
		 LoggerLoad.info("Logout sucessfull");
	  
	}

	/*@Then("Admin receives {int} ok and response with {string}")
	public void admin_receives_ok_and_response_with(String int1, String string2) {
		
				response = reqSpec.get("status_code");
				 System.out.println(response.asString());
				 LoggerLoad.info("Logout sucessfull");
	}*/
    
	//@tag5
	@When("Admin calls Get Https method with invalid endpoint {string}")
	public void admin_calls_get_https_method_with_invalid_endpoint(String string) {
		response = reqSpec.get("invalid_endpoint");
		 LoggerLoad.info("Logout request with invalid endpoint");
	   
	}
	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer int1) {
		 LoggerLoad.info("Validing the response with invalid logout endpoint");
	        actions.validateStatusCode(response, http_NotFound);
			actions.validateResponseTime(response);
	    
	}
	
   //@tag6
	@Given("Admin creates request to Logout with No Auth")
	public void admin_creates_request_to_logout_with_no_auth() {
		 LoggerLoad.info("Request to logout without authorization");
		reqSpec = actions.buildRequest();
	   
	}


}



