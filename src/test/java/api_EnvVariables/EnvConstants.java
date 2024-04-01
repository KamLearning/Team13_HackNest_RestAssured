package api_EnvVariables;

public class EnvConstants {

/*==================================File Paths==================================================================*/
	public static final String file_Path_batchTestdata="./src/test/resources/TestData/testdata_Batch.json";
	public static final String file_Path_ProgramTestdata="./src/test/resources/TestData/testdata_Program.json";
	public static final String file_Path_Schema_GetAllPrograms="./src/test/resources/TestData/Schema_GetAllPrograms.json";
	public static final String file_Path_Schema_GetAllProgramsWithUsers="./src/test/resources/TestData/Schema_GetAllProgramsWithUsers.json";
	public static final String file_Path_Schema_GetProgram="./src/test/resources/TestData/Schema_GetProgram.json";
	
/*==================================BaseURI & End Points=========================================================*/	
	
	public static final String qaEnvironmentbaseURI = "https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	public static final String login_Endpoint = "/login";
	public static final String createProgram_Endpoint = "/saveprogram";
	public static final String getAllPrograms_Endpoint="/allPrograms";
	public static final String getAllProgramsWithUsers_Endpoint="/allProgramsWithUsers";
	public static final String getAllProgramByProgramID_Endpoint="/programs";
	public static final String deleteByProgramID_Endpoint="/deletebyprogid";
	public static final String deleteByProgramName_Endpoint="/deletebyprogname";
	
/*==================================Base URL & End Points (Batch module)==================================================================*/	
    
	public static final String Create_New_Batch = "/batches";
	public static final String Get_All_Batches = "/batches";
	public static final String Get_Batch_by_BatchId = "/batches/batchId/{batchId}";
	public static final String Get_Batch_by_BatchName = "/batches/batchName/{batchName}";
	public static final String Get_Batch_by_ProgramId = "/batches/program/{programId}";
	public static final String Update_by_BatchId = "/batches/{batchId}";
	public static final String Delete_batch_by_BatchId= "/batches/{batchId}";
	
	
	
/*==================================Status Code==================================================================*/
	public static final int http_OK = 200;
	public static final int http_Created = 201;
	public static final int http_BadRequest = 400;
	public static final int http_UnAuthorized = 401;
	public static final int http_NotFound = 404;
	public static final int http_ServerError = 500;
	
}
