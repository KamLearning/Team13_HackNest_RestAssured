package api_StepDefs;

	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.List;
	import java.util.Map;
	import java.util.Random;

	import com.fasterxml.jackson.core.JsonProcessingException;

	import api_Actions.Actions_Program;
	import api_Actions.Actions_User;
	import api_EnvVariables.EnvConstants;
	import api_POJO.API_POJO_User_PUT;
	import api_POJO.API_Pojo_User;
	import api_Utils.API_BaseSetUp;
	import api_Utils.ExcelReader;
	import io.cucumber.datatable.DataTable;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

	public class API_StepDef_User extends API_BaseSetUp {
		int http_Created = EnvConstants.http_Created;
		int http_OK = EnvConstants.http_OK;
		int http_NotFound = EnvConstants.http_NotFound;
		int http_BadRequest = EnvConstants.http_BadRequest;
		int http_Unauthorized = EnvConstants.http_UnAuthorized;
		static String expecContenValue="application/json";
		static long expecResponseTime=5000;
		static String password="lmsHackathon@2024";
		RequestSpecification reqSpec;
		Response response;
		 API_Pojo_User user;
		 List<API_Pojo_User> userList;
		 API_POJO_User_PUT user_put;
		 List<API_POJO_User_PUT> userList_put;
		//Actions_Program actions = new Actions_Program();
		Actions_User actions_user = new Actions_User();
		
		@Given("Admin creates Request for LMS user")
		public void admin_creates_request_for_lms_user() throws FileNotFoundException {
		    reqSpec = actions_user.buildRequest();
		}

		@When("Admin sends HTTPS POST Request to log in LMS with valid endpoint user")
		public void admin_sends_https_post_request_to_log_in_lms_with_valid_endpoint_user() throws FileNotFoundException {
			
			response = actions_user.loginToGetAuthorized_User(reqSpec);
		}
		
		@Then("Admin receives {int} OK Status with auth token in response body user")
		public void admin_receives_ok_status_with_auth_token_in_response_body_user(Integer int1) throws FileNotFoundException {
			actions_user.setAuth(response);
			API_BaseSetUp.validateStatusCode(response, int1);
			API_BaseSetUp.validateStatusLine(response);
			API_BaseSetUp.validateResponseTime(response, expecResponseTime);
			API_BaseSetUp.validateContentType(response, expecContenValue);
			System.out.println("Post response validations.....");
		
		}

		
		 /*=================================*/
		
		@When("Admin sends HTTPS Request with endpoint for")
		public void admin_sends_https_request_with_endpoint_for(DataTable dataTable) throws FileNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
			   List<List<String>> rows = dataTable.asLists(String.class);
			   String tag="";
			   for (List<String> columns : rows) {
				    String conditionTag = columns.get(0);
				    String secondTag = columns.get(1);
				    String[] parts = secondTag.split("@");
				    String scenarioTag = parts[1]; 
				   
			   response = actions_user.getReqsOfUser(reqSpec,conditionTag,scenarioTag,tag);
			  
		}
		}

		@Then("Admin receives {int} OK")
		public void admin_receives_ok(Integer statusCode) {
			System.out.println("         **********Post response validations**************");
			API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime, expecContenValue);


		}


	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer statusCode) {
		System.out.println("         **********Post response validations**************");
		API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime, expecContenValue);

	}
		

	@Then("Admin receives status {int} with Not Found error message")
	public void admin_receives_status_with_not_found_error_message(Integer statusCode) {
		System.out.println("         **********Post response validations**************");
		API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime, expecContenValue);

	}
		
	                      /*******Create_Post request for all three roles
	                     * @throws FileNotFoundException *****************/

	@Given("Admin creates POST request with all mandatory fields {string}{string}")
	public void admin_creates_post_request_with_all_mandatory_fields(String excelFilePath, String sheetName) throws FileNotFoundException {
		 excelFilePath = ".\\src\\test\\resources\\TestData\\userTestData.xlsx";
		 sheetName = "Sheet1";
		 List<API_Pojo_User> userList = generatePojoFromExcel(excelFilePath, sheetName);
		 reqSpec = actions_user.buildRequest();
	     
	 }
	public List<API_Pojo_User> generatePojoFromExcel(String excelFilePath, String sheetName) {
	    userList = new ArrayList<API_Pojo_User>();
	    ExcelReader excelReader = new ExcelReader();
	    System.out.println( excelFilePath);

	    try {
	        List<Map<String, String>> excelData = excelReader.getData(excelFilePath, sheetName);
	        
	        for (Map<String, String> row : excelData) {
	            API_Pojo_User user = createPojoFromRow(row);
	            
	            userList.add(user);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception appropriately
	    }

	    return userList;
	}
	private API_Pojo_User createPojoFromRow(Map<String, String> row) {
	     user = new API_Pojo_User();
	     int count=165;
	     /*String userComments=row.put("userComments",row.get("userComments"));
	    user.setUserComments(row.get(userComments));//Merging 2 lines into single refer below
	    */ 
	     //user.setUserComments(row.put("userComments", row.get("userComments")));
	     
	     user.setUserComments(row.get("userComments"));
	    user.setUserEduPg(row.get("userEduPg"));
	    user.setUserEduUg(row.get("userEduUg"));
	    user.setUserFirstName(row.get("userFirstName"));
	    user.setUserLastName(row.get("userLastName"));
	    user.setUserLinkedinUrl(row.get("userLinkedinUrl"));
	    user.setUserLocation(row.get("userLocation"));

	    API_Pojo_User.UserLogin userLogin = new API_Pojo_User.UserLogin();
	    userLogin.setLoginStatus(row.get("loginStatus"));
	    row.put("userPassword", password);
	    userLogin.setPassword(row.get("userPassword"));
	    // Assuming roleIds are separated by comma
	    String[] roleIds = row.get("roleIds").split(",");
	    userLogin.setRoleIds(Arrays.asList(roleIds));
	    userLogin.setStatus(row.get("status"));
	    row.put("userLoginEmail",count+row.get("userLoginEmail"));
	    userLogin.setUserLoginEmail(row.get("userLoginEmail"));
	    count++;
	    user.setUserLogin(userLogin);

	    user.setUserMiddleName(row.get("userMiddleName"));
	    
	    Random random = new Random();
	    long randomNumber = Math.abs(random.nextLong() % 10000000000L);
	    row.put("UserPhoneNumber", Long.toString(randomNumber));
	    user.setUserPhoneNumber(row.get("UserPhoneNumber"));

	    List<API_Pojo_User.UserRoleMap> userRoleMaps = new ArrayList<API_Pojo_User.UserRoleMap>();
	    API_Pojo_User.UserRoleMap userRoleMap = new API_Pojo_User.UserRoleMap();
	    //userRoleMap.setRoleId("roleId");
	    userRoleMap.setRoleId(row.get("roleId"));
	    
	    userRoleMap.setUserRoleStatus(row.get("userRoleStatus"));
	    userRoleMaps.add(userRoleMap);
	    user.setUserRoleMaps(userRoleMaps);

	    user.setUserTimeZone(row.get("userTimeZone"));
	    user.setUserVisaStatus(row.get("userVisaStatus"));
	   
	    return user;
	}

	@When("Admin sends HTTPS Request with endpoint for create")
	public void admin_sends_https_request_with_endpoint_for_create() throws FileNotFoundException, JsonProcessingException {
		 
		/*for (API_Pojo_User userListReq : userList) {
			List<API_Pojo_User> singleUserList = Collections.singletonList(user);*/
		//System.out.println("The value of user is :"+user);    
		response = actions_user.createUser(reqSpec,userList);
		
		
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer statusCode) throws FileNotFoundException {
		
		
		System.out.println("         **********Post response validations**************");
		API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime,  expecContenValue);
		API_BaseSetUp.validateResponseBodySchema(response, EnvConstants.file_Path_userSchema_Post);

	}
		
		
		           //******************GetUserByID****************************************

	@Given("Admin creates GET Request with valid AdminId")
	public void admin_creates_get_request_with_valid_admin_id() throws FileNotFoundException {
		 reqSpec = actions_user.buildRequest();
	}

	@When("Admin sends HTTPS Request with endpoint")

	public void admin_sends_https_request_with_endpoint
	(DataTable dataTable) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		List<List<String>> rows = dataTable.asLists(String.class);
		   for (List<String> columns : rows) {
			    String conditionTag = columns.get(0);
			    String secondTag = columns.get(1);
			    String[] parts = secondTag.split("@");
			    String scenarioTag = parts[1]; 
				String thirdTag = columns.get(2);
			    String[] parts1 = thirdTag.split("@");
			    String IdTag = parts1[1]; 
			    System.out.println("IdTage is :"+IdTag);
			    
			response=actions_user.getReqsOfUser(reqSpec, conditionTag, scenarioTag, IdTag);
	}
		  
	}
	@Given("Admin creates GET Request with role id")
	public void admin_creates_get_request_with_role_id() throws FileNotFoundException {
		
		reqSpec= actions_user.buildRequest();
	}
	@Given("Admin creates GET Request with invalid role id")
	public void admin_creates_get_request_with_invalid_role_id() throws FileNotFoundException {
		reqSpec= actions_user.buildRequest();
	}

	@Then("Admin receives status {int} with Not Found")
	public void admin_receives_status_with_not_found(Integer statusCode) {
		System.out.println("         **********Post response validations**************");
		API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime, expecContenValue);
	}

	//******************************PutRequests*****************************

	@Given("Admin creates PUT Request with valid request body {string}{string}")
	public void admin_creates_put_request_with_valid_request_body(String excelFilePath, String sheetName) throws FileNotFoundException {

	excelFilePath = ".\\src\\test\\resources\\TestData\\userTestData.xlsx";
		 sheetName = "Sheet2";
		 List<API_POJO_User_PUT> userList_put = generatePojoFromExcel_put(excelFilePath, sheetName);
		 reqSpec = actions_user.buildRequest();
		 
		}
		public List<API_POJO_User_PUT> generatePojoFromExcel_put(String excelFilePath, String sheetName) {
			userList_put = new ArrayList<API_POJO_User_PUT>();
	    ExcelReader excelReader = new ExcelReader();
	    System.out.println( excelFilePath);

	    try {
	        List<Map<String, String>> excelData = excelReader.getData(excelFilePath, sheetName);
	        
	        /*for (Map<String, String> row : excelData) {
			API_POJO_User_PUT user_put = createPojoFromRow_put(row);
			userList_put.add(user_put);
	        }*/
	        int lastRowIndex = excelData.size() - 1; // Index of the last row

	     // Iterate over the rows from the first row to the last non-empty row
	     for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
	         Map<String, String> row = excelData.get(rowIndex);
	         API_POJO_User_PUT user_put = createPojoFromRow_put(row);
	         userList_put.add(user_put);
	     }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception appropriately
	    }

	    return userList_put;
	}
	private API_POJO_User_PUT createPojoFromRow_put(Map<String, String> row) {
	user_put = new API_POJO_User_PUT();
	user_put.setUserComments(row.get("userComments"));
	user_put.setUserEduPg(row.get("userEduPg"));
	user_put.setUserEduUg(row.get("userEduUg"));
	user_put.setUserFirstName(row.get("userFirstName"));
	user_put.setUserLastName(row.get("userLastName"));
	user_put.setUserLinkedinUrl(row.get("userLinkedinUrl"));
	user_put.setUserLocation(row.get("userLocation"));
	user_put.setUserMiddleName(row.get("userMiddleName"));
	    
	    Random random = new Random();
	    long randomNumber = Math.abs(random.nextLong() % 10000000000L);
	    row.put("UserPhoneNumber", Long.toString(randomNumber));
	    user_put.setUserPhoneNumber(row.get("UserPhoneNumber"));
	    user_put.setUserTimeZone(row.get("userTimeZone"));
	    user_put.setUserVisaStatus(row.get("userVisaStatus"));
	   
	    return user_put;
	}


	@When("Admin sends HTTPS Request with endpoint for putById")
	public void admin_sends_https_request_with_endpoint_for_putById() throws JsonProcessingException {
		response = actions_user.createUser_put(reqSpec,userList_put);
	}


	@Then("Admin receives {int} OK  Status with response body")
	public void admin_receives_ok_status_with_response_body(Integer statusCode) {
		System.out.println("         **********Post response validations**************");
		API_BaseSetUp.executeAllMethods(response, statusCode, expecResponseTime, expecContenValue);
	}


	}

