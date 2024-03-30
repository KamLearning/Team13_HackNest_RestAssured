package api_EndPoints;

public class Routes {

	public static String  base_url = "https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";

	//Post - Create New Batch
	
	public static String post_url = base_url+"/batches";
	
	//Get- All Batches
	
	public static String get_url = base_url+"/batches";
	
	//Get- Batch by BatchId
	
	public static String getBatch_url = base_url+"/batches/batchId/{batchId}";
	
	// Get- Batch by BatchName
	
	public static String getBatchName_url = base_url+"/batches/batchName/{batchName}";
	
	//Get- Batch by ProgramId
	
	public static String ProgramId_url = base_url+"/batches/program/{programId}";
	
	//Put - Update by Batch Id
	
	public static String updateBatchId_url = base_url+"/batches/{batchId}";
	
	//Delete - Delete batch by BatchId
	
	public static String deleteBatchId_url = base_url+" /batches/{id}";
}
