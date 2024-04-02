package api_EndPoints;

import static io.restassured.RestAssured.given;
import api_POJO.API_Pojo_ProgBatch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BatchEndPoints {

	public static Response createNewBatch(API_Pojo_ProgBatch payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;

	}

	public static Response readBatches(String batches) {
		Response response = given().pathParam("batches", batches).when().get(Routes.get_url);

		return response;
	}

	public static Response readBatchId(String batchId) {
		Response response = given().pathParam("batches", batchId).when().get(Routes.getBatch_url);

		return response;
	}

	public static Response readBatchName(String batchName) {
		Response response = given().pathParam("batches", batchName).when().get(Routes.getBatchName_url);

		return response;
	}

	public static Response readProgramId(String programId) {
		Response response = given().pathParam("batches", programId).when().get(Routes.ProgramId_url);

		return response;
	}

	public static Response updateBatch(String batchId, API_Pojo_ProgBatch payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("batchName", batchId).body(payload).when().put(Routes.updateBatchId_url);

		return response;
	}

	public static Response deleteBatchId(String batchId) {
		Response response = given().pathParam("batches", batchId).when().delete(Routes.deleteBatchId_url);

		return response;
	}
}
