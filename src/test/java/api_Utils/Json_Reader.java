package api_Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import api_EnvVariables.EnvConstants;


public class Json_Reader {

String file_Path_expectedResponseMsg=EnvConstants.file_Path_ProgramTestdata;
	
	public <T> String getJSONpayloadAsString(String testDataName, String filePath) {
		String requestPayload="";
		String json= null;
		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(filePath));
			json = new String(encoded, StandardCharsets.UTF_8);
		} catch(IOException e) {
			System.out.println("Read input test data from JSON failed");
		}
		
		if(json != null) {
			Gson gson = new Gson();
			Map<String, T> inputMap = gson.fromJson(json, new TypeToken<HashMap<String,T>>(){
				
			}.getType());
			
			requestPayload = gson.toJson(inputMap.get(testDataName));
			
		} else {
			System.out.println("JSON is blank");
		}
		//System.out.println("Request payload is : "+requestPayload);
		return requestPayload;
	}	
	
	public <T> T readJsonInRequestPOJO(String jsonBody, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
		
		try {
            return objectMapper.readValue(jsonBody, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public static void main(String[] args) {
		
	}
}
