package api_Actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import api_POJO.API_Pojo_Program;
import api_POJO.API_RequestPOJO_Program;
import api_Utils.RestUtils;
import api_Utils.Json_Reader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


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
	String updateByProgramID_Endpoint = EnvConstants.updateByProgramID_Endpoint;
	String updateByProgramName_Endpoint = EnvConstants.updateByProgramName_Endpoint;
	String logOutEndpoint = EnvConstants.logOut_Endpoint;
	String token;
	
	/* Builds the request and returns reqSpec */
	
	public RequestSpecification buildRequest() {
		RequestSpecification reqSpec;
		try {
			reqSpec = restUtil.getRequestSpec();
			//reqSpec = getRequestSpec();
			return reqSpec;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Log File not found");
			return null;
		}
	}
	
/* Builds the request with invalid baseURI and returns reqSpec */
	
	public RequestSpecification buildRequestWithInvalidBaseURI() {
		RequestSpecification reqSpec;
		try {
			reqSpec = restUtil.getRequestSpec();
			//reqSpec = getRequestSpecWithInvalidBaseURI();
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
		Env_Variables.token = token;
	}
	
	/* Log out from LMS */
	
	public Response logOut(RequestSpecification reqSpec) {
		
		Response response = restUtil.getAll(reqSpec, Env_Variables.token, logOutEndpoint);
		return response;
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
	
	/*Get ProgramId from envvariables */
	
	public int getProgramID() {
		int prgmID = Env_Variables.Program_ProgramID;
		System.out.println("Reading prgmID from env variables : "+prgmID);
		return prgmID;
	}
	
	/*Get ProgramName from envvariables */
	
	public String getProgramName() {
		String prgmName = Env_Variables.Program_ProgramName;
		System.out.println("Reading prgmName from env variables : "+prgmName);
		return prgmName;
	}
	
	/* Create program for given testDataName valid */
	
	public Response createProgram(RequestSpecification reqSpec, String testDataName) throws FileNotFoundException {
		String json = jsonReader.getJSONpayloadAsString(testDataName,filePath);
		API_RequestPOJO_Program program = jsonReader.readJsonInRequestPOJO(json, API_RequestPOJO_Program.class);
		
		//generate and attach serial number to programName 
		String newProgramName = restUtil.generateProgramName();
		program.setProgramName(newProgramName);
		
		String requestBody = program.toString();
		Env_Variables.Program_requestBody = program.toString();
		System.out.println("Program request Body is : "+requestBody);
		
		Response response = restUtil.create(reqSpec, Env_Variables.token, requestBody, createProgramEndpoint);
		String programID = restUtil.extractStringFromResponse(response, "programId");
		System.out.println("Program ID is "+programID);
		System.out.println("Response Body is "+response.asPrettyString());
		
		return response;
	}
	
	
	/* Get All programs */
	public Response getAllPrograms(RequestSpecification reqSpec) {
		Response response = restUtil.getAll(reqSpec, Env_Variables.token, getAllProgramsEndpoint);
		return response;
	}
	
	/* Get All programs with Users */
	public Response getAllProgramsWithUsers(RequestSpecification reqSpec) {
		Response response = restUtil.getAll(reqSpec, Env_Variables.token, getAllProgramsWithUsersEndpoint);
		return response;
	}
	
	/* Get All programs by programID */
	public Response getProgramByProgramID(RequestSpecification reqSpec){
		int programID = Env_Variables.Program_ProgramID;
		Response response = restUtil.getByParameter(reqSpec, Env_Variables.token,programID, getAllProgramsByProgramIDEndpoint);
		return response;
	}
	
	
	/* Delete program by programID */
	public Response deleteProgramByProgramID(RequestSpecification reqSpec) {
		int programID = Env_Variables.Program_ProgramID;
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.token,programID, deleteByProgramID_Endpoint);
		return response;
	}
	
	/* Delete program by programName */
	public Response deleteProgramByProgramName(RequestSpecification reqSpec) {
		String ProgramName = Env_Variables.Program_ProgramName;
		System.out.println("ProgramName from Env var : "+ProgramName);
		Response response = restUtil.deleteByParameter(reqSpec, Env_Variables.token,ProgramName, deleteByProgramName_Endpoint);
		return response;
	}
	
	/* Update all field's values program by programID */
	public Response updateProgram_AllFields_ByProgramID(RequestSpecification reqSpec , int prgmID) {
		int programID = prgmID;
		StringBuffer programName,programDescription; 
		Response response=null;
		
		try {
			API_Pojo_Program programResponseBody = restUtil.getResponseInObject(getProgramByProgramID(reqSpec), API_Pojo_Program.class);
			
			programName = new StringBuffer(programResponseBody.getProgramName()+"_Updated");
			programDescription = new StringBuffer(programResponseBody.getProgramDescription()+"_Updated");
			
			programResponseBody.setProgramName(programName.toString());
			programResponseBody.setProgramDescription(programDescription.toString());
			programResponseBody.setProgramStatus("Inactive");
			Env_Variables.Program_ProgramName=programName.toString();
			
			response = restUtil.updateByParameter(reqSpec, Env_Variables.token, programID, restUtil.getRequestAsString(programResponseBody), updateByProgramID_Endpoint);
		} catch (JsonMappingException e) {
			System.out.println("UpdateProgramByProgramID : Exception occured while mapping program response with request body");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			System.out.println("UpdateProgramByProgramID : Exception occured while processing program response json with request body");
			e.printStackTrace();
		}
		
		return response;
	}
	
	/* Update all field's values in program by programName */
	public Response updateProgram_AllFields_ByProgramName(RequestSpecification reqSpec, String prgmName) {
		String org_programName = prgmName;
		StringBuffer updated_programName,programDescription; 
		Response response=null;
		
		try {
			API_Pojo_Program programResponseBody = restUtil.getResponseInObject(getProgramByProgramID(reqSpec), API_Pojo_Program.class);
			
			updated_programName = new StringBuffer(programResponseBody.getProgramName()+"_ByName");
			programDescription = new StringBuffer(programResponseBody.getProgramDescription()+"_ByName");
			
			programResponseBody.setProgramName(updated_programName.toString());
			programResponseBody.setProgramDescription(programDescription.toString());
			programResponseBody.setProgramStatus("Active");
			Env_Variables.Program_ProgramName=updated_programName.toString();
			
			response = restUtil.updateByParameter(reqSpec, Env_Variables.token, org_programName, restUtil.getRequestAsString(programResponseBody), updateByProgramName_Endpoint);
		} catch (JsonMappingException e) {
			System.out.println("UpdateProgramByProgramID : Exception occured while mapping program response with request body");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			System.out.println("UpdateProgramByProgramID : Exception occured while processing program response json with request body");
			e.printStackTrace();
		}
		
		return response;
	}
	
	/* Update given field with provided value by ProgramName*/
	public Response updateProgram_RequiredField(String requestType, RequestSpecification reqSpec,String endPoint, Object paramValue,String attribute, String value) {
		//String endpoint = getEndpoint(endPointType);
		Response response=null;
		try {
			API_Pojo_Program programResponseBody = restUtil.getResponseInObject(getProgramByProgramID(reqSpec), API_Pojo_Program.class);
			API_Pojo_Program updatedResponseBody = programResponseBody;
			updatedResponseBody = restUtil.upDateRequestBodyField_PUT(updatedResponseBody, attribute, value);
			response = restUtil.executeRequest(requestType, reqSpec, Env_Variables.token, restUtil.getRequestAsString(updatedResponseBody), paramValue, endPoint);
			return response;	
		
		} catch (JsonMappingException e) {
			System.out.println("UpdateProgram_RequiredField : Exception occured while mapping program response with request body");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			System.out.println("UpdateProgram_RequiredField : Exception occured while processing program response json with request body");
			e.printStackTrace();
		}
		return null;
	}
	
	/* Execute request with No Authorization */
	
	public Response executeRequestWithNoAuthorization(String requestType, RequestSpecification reqSpec,Object paramValue, String endPoint) {
		String requestBody = jsonReader.getJSONpayloadAsString("ValidProgram",filePath);
		Response response = restUtil.executeRequest(requestType, reqSpec, "", requestBody, paramValue, endPoint);
		return response;
	}
	
	/* Execute request with invalid endpoint */
	public Response executeRequestWithInvalidEndpoint(String requestType, RequestSpecification reqSpec,Object paramValue, String endPoint) {
		String requestBody = jsonReader.getJSONpayloadAsString("ValidProgram",filePath);
		Response response = restUtil.executeRequest(requestType, reqSpec, Env_Variables.token, requestBody, paramValue, endPoint);
		return response;
	}
	
	/* Execute request : Negative scenarios */
	public Response executeProgramRequest(String requestType, RequestSpecification reqSpec,String testDataName,Object paramValue, String endPoint) {
		String requestBody;
		if(!testDataName.equals("NoRequestBody")) {
		requestBody = jsonReader.getJSONpayloadAsString(testDataName,filePath);
		}else {
		requestBody="";
		}
		Response response = restUtil.executeRequest(requestType, reqSpec, Env_Variables.token, requestBody, paramValue, endPoint);
		return response;
	}
	
	/* Get Endpoint */
	
	public String getEndpoint(String endPointType) {
		if (endPointType.equalsIgnoreCase("getAllProgramsEndpoint")) {
			return EnvConstants.getAllPrograms_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("getAllProgramsWithUsersEndpoint")) {
			return EnvConstants.getAllProgramsWithUsers_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("getAllProgramsByProgramIDEndpoint")) {
			return EnvConstants.getAllProgramByProgramID_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("createProgramEndpoint")) {
			return EnvConstants.createProgram_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("updateByProgramID_Endpoint")) {
			return EnvConstants.updateByProgramID_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("updateByProgramName_Endpoint")) {
			return EnvConstants.updateByProgramName_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("deleteByProgramID_Endpoint")) {
			return EnvConstants.deleteByProgramID_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("deleteByProgramName_Endpoint")) {
			return EnvConstants.deleteByProgramName_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("logOutEndpoint")) {
			return EnvConstants.logOut_Endpoint ;
		}else if (endPointType.equalsIgnoreCase("invalidEndpoint")) {
			return "/invalidEndpoint" ;
		}
		return null;
	}
	
/*==============================Code for Program validations============================================*/

	//Verify status code
	public void validateStatusCode(Response response, int status_Code) {
		Assert.assertEquals(response.getStatusCode(),status_Code);
	}
	
	//Verify status line
	public void validateStatusLine(Response response, String status_line) {
		Assert.assertEquals(response.getStatusLine(), status_line);
	}
	
	//Verify error message with boolean success flag
	public void validateErrorMessageAndSuccessFlag(Response response, String expectedErrorMsg, boolean expectedSuccessFlag){
		String actualErrorMsg = restUtil.extractStringFromResponse(response, "message");
		boolean actualSuccessFlag = response.getBody().jsonPath().getBoolean("success");
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
		Assert.assertEquals(actualSuccessFlag, expectedSuccessFlag);
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
		
		System.out.println("Validating response schema ");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePath)));
	}
	
	//Verify response body created with same values
	public void validateIfResponseBodyContainsRequestBodyValues(Response response) {
		String program_requestBody = Env_Variables.Program_requestBody;
		API_RequestPOJO_Program programResponse;
		try {
			programResponse = restUtil.getResponseInObject(response,API_RequestPOJO_Program.class);
			Assert.assertEquals(String.valueOf(programResponse),program_requestBody);
		} catch (JsonMappingException e) {
			System.out.println("Exception occured while mapping program response with request body");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			System.out.println("Exception occured while processing program response json with request body");
			e.printStackTrace();
		}		
		
	
	}		

}
