package api_Actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import api_Utils.Json_Reader;
import api_Utils.RestUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Actions_UserRoleMap {

	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String filePath = EnvConstants.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants.login_Endpoint;
	
	String getAllProgramsBatchesWithUsers_Endpoint = EnvConstants.getAllProgramsBatchesWithUsers_Endpoint;
	String getURProgramBatchMapByUserIdEndpoint = EnvConstants.getURProgramBatchMapByUserId_Endpoint;
	String deleteURProgramBatchMapByUserIdEndpoint = EnvConstants.deleteURProgramBatchMapByUserId_Endpoint;

	/* Builds the request and returns reqSpec */

	public RequestSpecification buildRequest() {
		RequestSpecification reqSpec;
		try {
			reqSpec = restUtil.getRequestSpec();
			return reqSpec;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Log File not found");
			return null;
		}
	}

	/* Log in to LMS with LoginPayload and get token */

	public Response loginToGetAuthorized(RequestSpecification reqSpec) throws FileNotFoundException {
		String requestBody = jsonReader.getJSONpayloadAsString("LoginPayload", filePath);
		System.out.println("Login request Body is : " + requestBody);
		Response response = restUtil.create(reqSpec, requestBody, loginEndpoint);

		return response;
	}

	/* Set auth token in environment variable to be used by UserRoleMap requests */

	public void setAuth(Response response) throws FileNotFoundException {
		String token = restUtil.extractStringFromResponse(response, "token");
		System.out.println("Token is " + token);
		System.out.println("Setting token in Env Variables");
		Env_Variables.token = token;
		System.out.println("UserRoleMap_token: " + Env_Variables.token);
	}

	/* Delete user role program batch map by userId */
	public Response deleteUrpbmByUserId(RequestSpecification reqSpec) {
		String user_Id = Env_Variables.userId;
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.token, user_Id,
				deleteURProgramBatchMapByUserIdEndpoint);
		return response;
	}

	/* Delete user role program batch map by invalid userId */
	public Response deleteUrpbmByInValidUserId(RequestSpecification reqSpec) {
		String invalidUserId = EnvConstants.invalidUserId;
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.token, invalidUserId,
				deleteURProgramBatchMapByUserIdEndpoint);
		return response;
	}

	/* Delete user role program batch map by valid userId without token */
	public Response deleteUrpbmByUserIdWithoutToken(RequestSpecification reqSpec) {
		String user_Id = Env_Variables.userId;
		Response response = restUtil.deleteByParameter(reqSpec, user_Id, "", deleteURProgramBatchMapByUserIdEndpoint);
		return response;
	}

	/* Get user role map by userId */
	public Response getUrmByUserId(RequestSpecification reqSpec) {
		String user_Id = Env_Variables.userId;
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.token, user_Id,
				getURProgramBatchMapByUserIdEndpoint);
		return response;
	}

	/* Get user role map by invalid userId */
	public Response getUrmByInvalidUserId(RequestSpecification reqSpec) {
		String invalidUser_Id = EnvConstants.invalidUserId;
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.token, invalidUser_Id,
				getURProgramBatchMapByUserIdEndpoint);
		return response;
	}

	/* Get user role map without auth by userId */
	public Response getUrmByUserIdWithoutToken(RequestSpecification reqSpec) {
		String user_Id = Env_Variables.userId;
		Response response = restUtil.getByParameter(reqSpec, "", user_Id, getURProgramBatchMapByUserIdEndpoint);
		return response;
	}
	
	/* Get assigned Program/Batches for all admins */
	public Response getAllProgramBatches_AllAdmins(RequestSpecification reqSpec) {
		
			Response response = restUtil.getAll(reqSpec, Env_Variables.token, getAllProgramsBatchesWithUsers_Endpoint);
			return response;
		}

	// Code for Program validations

	//Verify status code
		public void validateStatusCode(Response response, int status_Code) {
			Assert.assertEquals(response.getStatusCode(),status_Code);
		}
			
		//Verify status line
		public void validateStatusLine(Response response, String status_line) {
			Assert.assertEquals(response.getStatusLine(), status_line);
		}
		
		//Verify response time
		public void validateResponseTime(Response response) {
				Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<5);
		}
			
		//Verify header
		public void validateHeader(Response response) {
			response.then().assertThat().header("Content-Type", "application/json");
		}
		
		//Verify Response Schema
		public void validateResponseBodySchema(Response response, String FilePath) {
				
			//LoggerLoad.info("Validating response schema ");
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePath)));
		}
		
}
