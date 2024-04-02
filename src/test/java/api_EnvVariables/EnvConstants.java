package api_EnvVariables;

public class EnvConstants {

/*==================================File Paths==================================================================*/
	public static final String file_Path_ProgramTestdata="./src/test/resources/TestData/testdata_Program.json";
	public static final String file_Path_Schema_GetAllPrograms="./src/test/resources/TestData/Schema_GetAllPrograms.json";
	public static final String file_Path_Schema_GetAllProgramsWithUsers="./src/test/resources/TestData/Schema_GetAllProgramsWithUsers.json";
	public static final String file_Path_Schema_GetProgram="./src/test/resources/TestData/Schema_GetProgram.json";
	public static final String file_Path_numberFile = "./src/test/resources/TestData/serialNumberContainer.txt";
/*==================================BaseURI & End Points=========================================================*/	
	
	public static final String qaEnvironmentbaseURI = "https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	public static final String login_Endpoint = "/login";
	public static final String createProgram_Endpoint = "/saveprogram";
	public static final String getAllPrograms_Endpoint="/allPrograms";
	public static final String getAllProgramsWithUsers_Endpoint="/allProgramsWithUsers";
	public static final String getAllProgramByProgramID_Endpoint="/programs";
	public static final String deleteByProgramID_Endpoint="/deletebyprogid";
	public static final String deleteByProgramName_Endpoint="/deletebyprogname";
	public static final String updateByProgramID_Endpoint="/putprogram";
	public static final String updateByProgramName_Endpoint="/program";
	public static final String logOut_Endpoint = "/logoutlms";
	public static final String invalidBaseURI = "https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/InvalidBaseURI";
/*==================================Status Code and other values==================================================================*/
	public static final int http_OK = 200;
	public static final int http_Created = 201;
	public static final int http_BadRequest = 400;
	public static final int http_UnAuthorized = 401;
	public static final int http_NotFound = 404;
	public static final int http_MethodNotAllowed = 405;
	public static final int http_ServerError = 500;
	public static final String Created = "HTTP/1.1 201 ";
	public static final String BadRequest = "HTTP/1.1 400 ";
	public static final String OK = "HTTP/1.1 200 ";
	public static final String OK_getUserRoleMap = "HTTP/1.1 200 ";
	public static final String UnAuthorized = "HTTP/1.1 401 ";
	public static final String NotFound = "HTTP/1.1 404 ";
	public static final String MethodNotAllowed = "HTTP/1.1 405 ";
	public static final String ServerError = "HTTP/1.1 500 ";
	
	public static final int invalidprogram_ID = 16000000;
	public static final Integer updateProgram_ProgramID = 16906;
	public static final String updateProgram_ProgramName = "Team13_Phase2API_Test001";
	
/*==================================Error Messages==================================================================*/
	public static final String existingProgramNameErrorMsg = "cannot create program , since already exists";
	public static final String deleteInvalidProgramNameErrorMessage = "no record found with programName";

/*==================================File Paths for Program-Batch==================================================================*/
	public static final String getAllProgramsBatchesWithUsers_Endpoint="/userRoleProgramBatchMap";
	
	
}
