package api_Actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import api_Utils.RestUtils;
import api_Utils.Json_Reader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;

// Flow :
//RestUtil ==> Actions ==>stepdef

public class Actions_Program {
 
	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String filePath = EnvConstants.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants.login_Endpoint;
	String createProgramEndpoint = EnvConstants.createProgram_Endpoint;
	String getAllProgramsEndpoint = EnvConstants.getAllPrograms_Endpoint;
	String getAllProgramsWithUsersEndpoint = EnvConstants.getAllProgramsWithUsers_Endpoint;
	String getAllProgramsByProgramIDEndpoint = EnvConstants.getAllProgramByProgramID_Endpoint;
	String deleteByProgramID_Endpoint = EnvConstants.deleteByProgramID_Endpoint;
	String deleteByProgramName_Endpoint = EnvConstants.deleteByProgramName_Endpoint;
	
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
		System.out.println("Setting token in Env Variables");
		Env_Variables.Program_token = token;
	}
	
	/* Set ProgramID value in environment variable to be used by programController requests  */
	
	public void setProgramID(Response response) throws FileNotFoundException {
		int programID = Integer.parseInt(restUtil.extractStringFromResponse(response, "programId"));
		System.out.println("programID is "+programID);
		System.out.println("Setting programID in Env Variables");
		Env_Variables.Program_ProgramID = programID;
	}
	
	/* Set ProgramName value in environment variable to be used by programController requests  */
	
	public void setProgramName(Response response) throws FileNotFoundException {
		String programNAME = restUtil.extractStringFromResponse(response, "programName");
		System.out.println("programName is "+programNAME);
		System.out.println("Setting programName in Env Variables");
		Env_Variables.Program_ProgramName = programNAME;
	}
	
	/* Create program for given testDataName */
	
	public Response createProgram(RequestSpecification reqSpec, String testDataName) throws FileNotFoundException {
		String requestBody = jsonReader.getJSONpayloadAsString(testDataName,filePath);
		System.out.println("Program request Body is : "+requestBody);
		Response response = restUtil.create(reqSpec, Env_Variables.Program_token, requestBody, createProgramEndpoint);
		String programID = restUtil.extractStringFromResponse(response, "programId");
		System.out.println("Program ID is "+programID);
		System.out.println("Response Body is "+response.asPrettyString());
		return response;
	}
	
	/* Get All programs */
	public Response getAllPrograms(RequestSpecification reqSpec) {
		Response response = restUtil.getAll(reqSpec, Env_Variables.Program_token, getAllProgramsEndpoint);
		return response;
	}
	
	/* Get All programs with Users */
	public Response getAllProgramsWithUsers(RequestSpecification reqSpec) {
		Response response = restUtil.getAll(reqSpec, Env_Variables.Program_token, getAllProgramsWithUsersEndpoint);
		return response;
	}
	
	/* Get All programs by programID */
	public Response getProgramByProgramID(RequestSpecification reqSpec){
		int programID = Env_Variables.Program_ProgramID;
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.Program_token,programID, getAllProgramsByProgramIDEndpoint);
		return response;
	}
	
	/* Delete program by programID */
	public Response deleteProgramByProgramID(RequestSpecification reqSpec) {
		int programID = Env_Variables.Program_ProgramID;
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.Program_token,programID, deleteByProgramID_Endpoint);
		return response;
	}
	
	/* Delete program by programName */
	public Response deleteProgramByProgramName(RequestSpecification reqSpec) {
		String ProgramName = Env_Variables.Program_ProgramName;
		System.out.println("ProgramName from Env var : "+ProgramName);
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.Program_token,ProgramName, deleteByProgramName_Endpoint);
		return response;
	}
	
/*==============================Code for Program validations============================================*/

	//Verify status code
	public void validateStatusCode(Response response, int status_Code) {
		Assert.assertEquals(response.getStatusCode(),status_Code);
	}
	
	public void validateStatusLine(Response response) {
		
	}
	
	//Verify response time
	public void validateResponseTime(Response response) {
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<5);
	}
	
	//Verify Response Schema
	public void validateResponseBodySchema(Response response, String FilePath) {
		
		System.out.println("Validating response schema ");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePath)));
	}
	
			

}
