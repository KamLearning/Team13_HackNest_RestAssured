package api_Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api_EnvVariables.EnvConstants;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

	/* Build the request and attach restassured logs in logFILE */
	
	public RequestSpecification getRequestSpec() throws FileNotFoundException {
		RestAssured.baseURI = EnvConstants.qaEnvironmentbaseURI;
		PrintStream log = new PrintStream(new FileOutputStream("RestAPIHackathonLogs.txt"));
		
		RequestSpecification req=RestAssured.given().auth().none()
								//.accept(ContentType.JSON)
				.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log))
				.contentType(ContentType.JSON);							
		return req;							
	}
	
	/* Reusable code for HTTP_POST_REQUEST without auth-token */
	
	public Response create(RequestSpecification reqSpec,String requestBody, String endPoint) {
		Response response = reqSpec.body(requestBody).when().post(endPoint);
		return response;
	}
	
	/* Reusable code for HTTP_POST_REQUEST with auth-token */
	
	public Response create(RequestSpecification reqSpec, String authToken ,String requestBody, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken)
				.body(requestBody).when().post(endPoint);
		return response;
	}
	
	/* Reusable code for HTTP_GET_REQUEST with auth-token */
	
	public Response getAll(RequestSpecification reqSpec,String authToken, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).when().get(endPoint);
		return response;
	}
	
	/* Reusable code for HTTP_GET_REQUEST with auth-token and String path parameter */
	
	public Response getByParameter(RequestSpecification reqSpec,String authToken,String paramValue,String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().get(endPoint+"/{paramKey}");
		return response;
	}
	
	/* Reusable code for HTTP_GET_REQUEST with auth-token and int path parameter */
	
	public Response getByParameter(RequestSpecification reqSpec,String authToken,int paramValue,String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().get(endPoint+"/{paramKey}");
		return response;
	}
	
	/* Reusable code for HTTP_DELETE_REQUEST with auth-token and path parameter */
	
	public Response deleteByParameter(RequestSpecification reqSpec,String authToken,String paramValue, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_DELETE_REQUEST with auth-token and int path parameter */
	
	public Response deleteByParameter(RequestSpecification reqSpec,String authToken,int paramValue, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_PUT_REQUEST with auth-token and path parameter */
	
	public Response updateByParameter(RequestSpecification reqSpec,String authToken,String paramKey , String paramValue, String requestBody,String endPoint) {
		String pathParamKey=paramKey;
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam(pathParamKey, paramValue).body(requestBody).when().put(endPoint+"/{"+pathParamKey+"}");	
		return response;
	}
	
	/* Reusable code for reading response in POJO class */
	//not tested yet
	public <T> T getResponseInObject(Response response, Class<T> responseType) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T objectResponse = mapper.readValue(response.getBody().asString(), responseType);
        return objectResponse;
    }
	
	/* Reusable code for extracting particular given string value from response */
	
	public String extractStringFromResponse(Response response, String req) {
		String reqString = response.jsonPath().getString(req).trim();
		return reqString;
		
	}
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
}
