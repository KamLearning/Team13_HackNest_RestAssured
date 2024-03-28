package api_Actions;

import java.io.FileNotFoundException;

import api_EnvVariables.EnvConstants_Program;
import api_EnvVariables.Env_Variables;
import api_Utils.RestUtils;
import api_Utils.Json_Reader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Actions_Program {
 
	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String filePath = EnvConstants_Program.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants_Program.login_Endpoint;
	String createProgramEndpoint = EnvConstants_Program.createProgram_Endpoint;
	String token;
	
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
		String requestBody = jsonReader.getJSONpayloadAsString("LoginPayload",filePath);
		System.out.println("Login request Body is : "+requestBody);
		Response response = restUtil.create(reqSpec,requestBody,loginEndpoint);
		
		return response;
	}
	
	/* Set auth token in environment variable to be used by programController requests  */
	
	public void setAuth(Response response) throws FileNotFoundException {
		String token = restUtil.extractStringFromResponse(response, "token");
		System.out.println("Token is "+token);
		System.out.println("Setting token");
		Env_Variables.Program_token = token;
	}
	
	/* Create program for given testDataName */
	
	public Response createProgram(String testDataName) throws FileNotFoundException {
		String requestBody = jsonReader.getJSONpayloadAsString(testDataName,filePath);
		System.out.println("Program request Body is : "+requestBody);
		RequestSpecification reqSpec = buildRequest();
		Response response = restUtil.create(reqSpec, Env_Variables.Program_token, requestBody, createProgramEndpoint);
		String programID = restUtil.extractStringFromResponse(response, "programId");
		System.out.println("Program ID is "+programID);
		System.out.println("Response Body is "+response.asPrettyString());
		return response;
	}
	
/*==============================Code for Program validations============================================*/

}
