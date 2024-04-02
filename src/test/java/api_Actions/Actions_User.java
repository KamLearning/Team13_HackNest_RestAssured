package api_Actions;

import api_EnvVariables.EnvConstants;
import api_Utils.Json_Reader;
import api_Utils.RestUtils;

import api_EnvVariables.EnvConstants;
import api_EnvVariables.Env_Variables;
import api_POJO.API_POJO_User_PUT;
import api_POJO.API_Pojo_User;
import api_Utils.RestUtils;
import api_Utils.Json_Reader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Actions_User {
	RestUtils restUtil = new RestUtils();
	Json_Reader jsonReader = new Json_Reader();
	String filePath = EnvConstants.file_Path_ProgramTestdata;
	String loginEndpoint = EnvConstants.login_Endpoint;
	String GetAllActiveUsers=EnvConstants.GetAllActiveUsers;
	 //String GetCountOfActiveInactive= EnvConstants.GetCountOfActiveInactive;
	 String GetCountOfActiveInactiveID= EnvConstants.GetCountOfActiveInactiveID;
	 String GetUserByProBatch= EnvConstants.GetUserByProBatch;
	 String GetUserByProg= EnvConstants.GetUserByProg;
	 //String GetAllUsersWithRoles= EnvConstants.GetAllUsersWithRoles;
	 //String GetUserByRoleID= EnvConstants.GetUserByRoleID;
	 String UpdateUserByUserId= EnvConstants.UpdateUserByUserId;
	 String UpdateUserLoginStatus= EnvConstants.UpdateUserLoginStatus;
	 String UpdateUserRoleStatus= EnvConstants.UpdateUserRoleStatus;
	 String UpdateUserRoleProgBatch= EnvConstants.UpdateUserRoleProgBatch;
	 String UpdateUserRoleID= EnvConstants.UpdateUserRoleID;
	 String UpdateUser= EnvConstants.UpdateUser;
	 String CreateUsers= EnvConstants.CreateUsers;
	 //String EnvConstants.DeleteUser;
	 String token;
	 String scenarioName;
	 String reqBody;
	 Response response;
	 String userId;
	 //String userId_endpoint=GetUserbyID+ Env_Variables.userId;
	 String adminId;
	 String endpoint;
	 String finalEndpoint_Negative= "/users"+"/75963258";
	 String userId_variable;
	
	 
	 
	 
	 
	 public RequestSpecification buildRequest() throws FileNotFoundException {
			RequestSpecification reqSpec;
			reqSpec = restUtil.getRequestSpec();
			return reqSpec;
		}
	 /* Log in to LMS with LoginPayload and get token */
		
		public Response loginToGetAuthorized_User(RequestSpecification reqSpec) throws FileNotFoundException {
			String requestBody = jsonReader.getJSONpayloadAsString("LoginPayload",filePath);
			System.out.println("Login request Body is : "+requestBody);
			Response response = restUtil.create(reqSpec,requestBody,loginEndpoint);
			
			return response;
		}
	 
		
		/* Set auth token in environment variable   */
		public void setAuth(Response response) throws FileNotFoundException {
			String token = restUtil.extractStringFromResponse(response, "token");
			System.out.println("Token is "+token);
			System.out.println("Setting token in Env Variables");
			Env_Variables.token = token;
			Env_Variables.token = token;
		}
		
		
		/*................Create request for all user roles..........*/
		
	public Response createUser(RequestSpecification reqSpec,List<API_Pojo_User> userList ) throws JsonProcessingException	
	{
		int size = userList.size();
	    
		 //API_Pojo_User userOneList = userList.get(0);
	    for (int i = 0; i < size-1; i++) {
	        API_Pojo_User userOneList = userList.get(i);
	        
		ObjectMapper objectMapper = new ObjectMapper();
		 reqBody= objectMapper.writeValueAsString(userOneList);
		 
		 System.out.println("The reqBody is "+ reqBody);
		 System.out.println(Env_Variables.token);
		 System.out.println(CreateUsers);
		 response = restUtil.create(reqSpec, Env_Variables.token,reqBody,CreateUsers);
		System.out.println(response.asPrettyString());
		
		
		 userId = restUtil.extractStringFromResponse(response, "userId");
		System.out.println("userId is "+userId);
		System.out.println("Setting UserID in Env Variables");
		Env_Variables.userId = userId;
		System.out.println("userId is "+Env_Variables.userId );
		}
		return response;
		
	}
	
	/* Set userId value in environment variable to be used in further requests  */
	
		
	/*public void setUserID(Response response) throws FileNotFoundException {
		int userId = Integer.parseInt(restUtil.extractStringFromResponse(response, "userId"));
		System.out.println("userId is "+userId);
		System.out.println("Setting programID in Env Variables");
		Env_Variables.userId = userId;
		System.out.println("userId is "+Env_Variables.userId );
	}*/
		
	/*Executing positive and negative scenarios based on the condition*/
		
	public Response getReqsOfUser(RequestSpecification reqSpec, String conditionTag,String scenarioTag,String IdTag) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		String endpoint = null;
		 //String finalEndpoint = null;
		String finalEndpoint = "";
		 boolean useToken = true; 
		 Field field;
		 Field fieldValue;
		 //adminId=GetUserbyID+String.valueOf(Env_Variables.userId);
		 //finalEndpoint=endpoint+String.valueOf(Env_Variables.userId);
		 field = EnvConstants.class.getField(scenarioTag);
		 endpoint=(String) field.get(null);

		 
		 		   if (conditionTag.contains("@auth") && conditionTag.contains("@getall")) {
		    	   System.out.println("The end point is "+ endpoint);
		    	   useToken = true;
		    	   Response response = restUtil.getAll(reqSpec, Env_Variables.token, endpoint);
			       
		    	   return response;
		    		
		    	} 
		    else if (conditionTag.contains("@noauth")&& conditionTag.contains("@getall")) {
	    			System.out.println("The end point is "+ endpoint);
		    	    useToken = false;
		    	    Response response = restUtil.getAllNoAuth(reqSpec, endpoint);
		    	   
		    	    return response;
		    } else if (conditionTag.contains("@invalidEndpoint") && conditionTag.contains("@getall")) {    
			    	endpoint="758632148";
			    	System.out.println("The end point is "+ endpoint);
			    	useToken = true;
			    	Response response = restUtil.getAll(reqSpec, Env_Variables.token, endpoint);
			    	
			    	return response;
		    	} 
		 			
		    else if (conditionTag.contains("@Positive") && conditionTag.contains("@UId")) {
			 		System.out.println("The endpoint is "+endpoint);
			 		//field = EnvConstants.class.getField(scenarioTag);
			 		//finalEndpoint=endpoint+String.valueOf(Env_Variables.class.getField(IdTag));
			 		 fieldValue=Env_Variables.class.getField(IdTag);
			 		 userId_variable =(String)fieldValue.get(null);
			 		
			 		finalEndpoint=endpoint+userId_variable;
			 		System.out.println("userId_variable is "+userId_variable);
			 		//finalEndpoint=endpoint+"U314";
			 	    System.out.println("The end point is "+ finalEndpoint);
			 		useToken = true;
			    	Response response = restUtil.getAll(reqSpec, Env_Variables.token, finalEndpoint);
				    System.out.println(response.asPrettyString());
				        
				        return response;
			}
			else if (conditionTag.contains("@noauth") && conditionTag.contains("@UId")) {
			
				 System.out.println("The endpoint is "+endpoint);
				 finalEndpoint=endpoint+String.valueOf(Env_Variables.userId);
				// finalEndpoint=endpoint+"U314";
				 System.out.println("The end point is "+ finalEndpoint);
				 useToken = false;
				 System.out.println("Triaging......specific.");
		Response response = restUtil.getAllNoAuth(reqSpec, endpoint);
		        System.out.println(response.asPrettyString());
		        return response;
				
			}
			
			else if (conditionTag.contains("@invalidEndpoint") && conditionTag.contains("@UId")) {
				
				 System.out.println("The end point is "+ finalEndpoint_Negative);
				 useToken = true;
	  	   Response response = restUtil.getAll(reqSpec, Env_Variables.token, finalEndpoint_Negative);
		        System.out.println(response.asPrettyString());
		        return response;
				
			}
			else if (conditionTag.contains("@auth") && conditionTag.contains("@getRId")) {
			    
				System.out.println("The end point is "+ endpoint);
			    useToken = true;
			    String roleId=IdTag;
			    reqSpec=reqSpec.queryParam("id", roleId);
			    	
			    	Response response = restUtil.getAll(reqSpec, Env_Variables.token, endpoint);
			    System.out.println(response.asPrettyString());
			    return response;
			}
		 		   
			 else if (conditionTag.contains("@Positive") && conditionTag.contains("@UId_Prog")) {
			 		System.out.println("The endpoint is "+endpoint);
			 		finalEndpoint=endpoint+String.valueOf(Env_Variables.Program_Id);
			 		System.out.println("The end point is "+ finalEndpoint);
			 		useToken = true;
			    	Response response = restUtil.getAll(reqSpec, Env_Variables.token, finalEndpoint);
				    System.out.println(response.asPrettyString());
				        
				        return response;
			}
				return response;
	}
	
	//******************************PutRequests*****************************
	
public Response createUser_put(RequestSpecification reqSpec,List<API_POJO_User_PUT> userList_put ) throws JsonProcessingException	
	{
	
		 String endpoint_put=UpdateUserByUserId+ String.valueOf(Env_Variables.userId);
		 System.out.println("Env_Variables.User_Id"+String.valueOf(Env_Variables.userId));
		 ObjectMapper objectMapper = new ObjectMapper();
		 reqBody= objectMapper.writeValueAsString(userList_put.get(0));
		 System.out.println("Put req body is :"+ reqBody);
		 reqSpec.log().all();
		 
		 response = restUtil.create_PutByAuth(reqSpec, Env_Variables.token,reqBody,endpoint_put);
		response.then().log().all();
		
	
		return response;
		
	}	
	
}
