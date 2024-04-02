package api_Actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.Get;
import org.testng.Assert;

import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import api_Utils.Json_Reader;
import api_Utils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Actions_UserLogin extends EnvConstants {
	
	//RequestSpecification reqSpec;
	RequestSpecification request;
	ResponseSpecification res;
	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String requestBody;
	String token;
	Response response;
	String filePath = EnvConstants.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants.login_Endpoint;
	

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
	
	public  Response login(RequestSpecification reqSpec) throws FileNotFoundException {
		String requestBody = jsonReader.getJSONpayloadAsString("LoginPayload",file_Path_ProgramTestdata);
		System.out.println("Login request Body is : "+requestBody);
		Response response = restUtil.create(reqSpec,requestBody,login_Endpoint);
		
		return response;
	}
	
	public void logoutwithauth()  {
		RestAssured.baseURI =" https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
		String authToken = Env_Variables.token;
		 Response response = RestAssured.given().header("Authorization","Bearer"+authToken).get("/logoutlms");
		response.then().statusCode(200); 
		
		
	}
	
	
	

	public void setAuth(Response response) throws FileNotFoundException {
		String token = restUtil.extractStringFromResponse(response, "token");
		System.out.println("Token is "+token);
		System.out.println("Setting token in Env Variables");
		Env_Variables.Program_token = token;
	}
	
	
	//Verify Response Schema
		public void validateResponseBodySchema(Response response, String FilePath) {
			
			System.out.println("Validating response schema ");
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePath)));
		}
 
	//Verify status code
	public void validateStatusCode(Response response, int status_Code) {
		Assert.assertEquals(response.getStatusCode(),status_Code);
	}
	//Verify response time
		public void validateResponseTime(Response response) {
			Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<5);
		}

		public Response validEndPoint(RequestSpecification reqSpec) {
			Response response = reqSpec.post("login_Endpoint");
			
			return response;
		}
		/*public Response validLogoutEndPoint(RequestSpecification reqSpec) {
			Response response = reqSpec.get("logout_Endpoint");
			return response;
		}*/
		
	    public Response testInvalidCredentials(RequestSpecification reqSpec) {
	    	 
		
	    	String invalidUserLoginEmailid = "<invalid_userLoginEmailid>";
	        String invalidPassword = "<invalid_password>";
	        reqSpec.body("{ \"UserLoginEmail\": \"" + invalidUserLoginEmailid + "\", \"Password\": \"" + invalidPassword + "\" }");
			return response;
	        //return reqSpec;
	      
  // Make the POST request
	       // Response response = request.post("/login");
	    	
	    }

		public Response getAuth(RequestSpecification reqSpec) {
			reqSpec = RestAssured.given();
			Response response = restUtil.getAll(reqSpec,Env_Variables.token,logout_Endpoint);
			return response;
		}
		
		public Response create(RequestSpecification reqSpec,String requestBody, String endPoint) {
			Response response = reqSpec.body(requestBody).when().get(endPoint);
			return response;
		}
		

	}




