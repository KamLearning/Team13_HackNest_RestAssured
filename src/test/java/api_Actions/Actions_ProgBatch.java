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

public class Actions_ProgBatch {
	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String filePath = EnvConstants.file_Path_batchTestdata;
	String loginFilePath = EnvConstants.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants.login_Endpoint;

	String token;

	public RequestSpecification buildRequest() {
		// TODO Auto-generated method stub
		RequestSpecification reqSpec;
		try {
			reqSpec = restUtil.getRequestSpec();
			System.out.println(reqSpec);
			return reqSpec;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Log File not found");
			return null;
		}
	}

	public Response loginToGetAuthorized(RequestSpecification reqSpec) throws FileNotFoundException {
		String requestBody = jsonReader.getJSONpayloadAsString("LoginPayload", loginFilePath);
		System.out.println("Login request Body is : " + requestBody);
		Response response = restUtil.create(reqSpec, requestBody, loginEndpoint);

		System.out.println("Login response  Body is : " + response );
		return response;
	}

	/*
	 * Set auth token in environment variable to be used by batchController requests
	 */

	public void setAuth(Response response) throws FileNotFoundException {
		String token = restUtil.extractStringFromResponse(response, "token");
		System.out.println("Token is " + token);
		System.out.println("Setting token in Env Variables");
		Env_Variables.batch_token = token;
	}

	public Response createBatch(RequestSpecification reqSpec, String testDataName) throws FileNotFoundException {

		System.out.println(testDataName);
		String requestBody = jsonReader.getJSONpayloadAsString(testDataName, filePath);
		System.out.println("batch request Body is : " + requestBody);

		Response response = restUtil.create(reqSpec, token, requestBody, EnvConstants.Create_New_Batch);

		String batchID = restUtil.extractStringFromResponse(response, "batchId");

		System.out.println("batch ID is " + batchID);
		System.out.println("Response Body is " + response.asPrettyString());
		return response;
	}

	/* Get All batchs */
	public Response getAllbatchs(RequestSpecification reqSpec) {
		Response response = restUtil.getAll(reqSpec, Env_Variables.batch_token, EnvConstants.Get_All_Batches);
		return response;
	}

	/* Get All batchs by batchID */
	public Response getbatchBybatchID(RequestSpecification reqSpec) {
		int batchID = Env_Variables.Batch_Id;
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.batch_token, batchID,
				EnvConstants.Get_Batch_by_BatchId);
		return response;
	}

	/* Get All batchs by batchID */
	public Response getbatchByName(RequestSpecification reqSpec) {
		String batchName = Env_Variables.Batch_Name;
		
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.batch_token, batchName,
				EnvConstants.Get_Batch_by_BatchName );
		return response;
	}
	
	
	/* Delete batch by batchID */
	public Response deletebatchBybatchID(RequestSpecification reqSpec) {
		int batchID = Env_Variables.Batch_Id;
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.batch_token, batchID,
				EnvConstants.Delete_batch_by_BatchId);
		return response;
	}

 

	/*
	 * ==============================Code for batch
	 * validations============================================
	 */

	// Verify status code
	public void validateStatusCode(Response response, int status_Code) {
		Assert.assertEquals(response.getStatusCode(), status_Code);
	}

	public void validateStatusLine(Response response) {

	}

	// Verify response time
	public void validateResponseTime(Response response) {
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) < 5);
	}

	// Verify Response Schema
	public void validateResponseBodySchema(Response response, String FilePath) {

		System.out.println("Validating response schema ");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePath)));
	}

}
