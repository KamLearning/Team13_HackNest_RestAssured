package api_Utils;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.nio.file.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api_EnvVariables.EnvConstants;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
	static boolean init = false;
	static PrintStream log;

	public static PrintStream getLogPrintStream() {
		if (init == false) {
			try {
				log= new PrintStream(new FileOutputStream("RestAPIHackathonLogs.txt"),true);
			} catch (FileNotFoundException e) {
				System.out.println("LogPrintStreaam File not found!!");
				e.printStackTrace();
			}
			init=true;
		} 
		
		return log;
	}
	/* Build the request and attach restassured logs in logFILE */
	
	public RequestSpecification getRequestSpec() throws FileNotFoundException {
		
		PrintStream logFile = getLogPrintStream();
		
		/*======added now=====*/
		
		// Configure RestAssured to use the custom logging filter
        RestAssured.config = RestAssured.config()
                .logConfig(new LogConfig().defaultStream(logFile));

        // Add the logging filters to log the request and response
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL));
        
        /*======until here=====*/
		
        RestAssured.baseURI = EnvConstants.qaEnvironmentbaseURI;
        RequestSpecification req = RestAssured.given()
				.accept(ContentType.JSON)
				.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log))
				.contentType(ContentType.JSON);		
		
		return req;							
	}
	
	public RequestSpecification getRequestSpecWithInvalidBaseURI() throws FileNotFoundException {
		
		PrintStream log = new PrintStream(new FileOutputStream("RestAPIHackathonLogs.txt"),true);
		RestAssured.baseURI = EnvConstants.invalidBaseURI;
				
		RequestSpecification req = RestAssured.given()
				.accept(ContentType.JSON)
				.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log))
				.contentType(ContentType.JSON);		
		
		return req;							
	}
	
	/* Reusable code for HTTP_POST_REQUEST without auth-token */
	
	public Response create(RequestSpecification reqSpec,String requestBody, String endPoint) {
		Response response = reqSpec.body(requestBody).when().post(endPoint);
		return response;
	}
	
	/* Reusable code for HTTP_All_REQUEST with/without auth-token  */
	public Response executeRequest(String requestType, RequestSpecification reqSpec,String authToken,String requestBody,Object paramValue, String endPoint) {
		Response response;
		
		if (requestType.equalsIgnoreCase("getAllPrograms")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).when().get(endPoint);
			System.out.println("Executed getAllPrograms. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("getAllProgramsWithUsers")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).when().get(endPoint);
			System.out.println("Executed getAllProgramsWithUsers. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("getProgramWithProgramID")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue)
					.when().get(endPoint+"/{paramKey}");
			System.out.println("Executed getProgramWithProgramID. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("createProgram")) {
			response = reqSpec.header("Authorization","Bearer "+authToken)
					.body(requestBody).when().post(endPoint);
			System.out.println("Executed createProgram. Here's response : "+response.prettyPrint());
			return response;
		}else if(requestType.equalsIgnoreCase("updateByProgramID")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).body(requestBody).when().put(endPoint+"/{paramKey}");	
			System.out.println("Executed updateByProgramID. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("updateByProgramName")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).body(requestBody).when().put(endPoint+"/{paramKey}");	
			System.out.println("Executed updateByProgramName. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("deleteByProgramID")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
			System.out.println("Executed deleteByProgramID. Here's response : "+response.prettyPrint());
			return response;
			
		}else if(requestType.equalsIgnoreCase("deleteByProgramName")) {
			
			response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
			System.out.println("Executed deleteByProgramName. Here's response : "+response.prettyPrint());
			return response;
			
		}
		return null;
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
	

	/* Reusable code for HTTP_GET_REQUEST with noAuthtoken */
	
	public Response getAllNoAuth(RequestSpecification reqSpec, String endPoint) {
		Response response = reqSpec.when().get(endPoint);
		return response;
	}

  /* Reusable code for HTTP_DELETE_REQUEST with auth-token and String path parameter */
	
	public Response deleteByParameter(RequestSpecification reqSpec,String authToken,String paramValue, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_DELETE_REQUEST with auth-token and int path parameter */
	
	public Response deleteByParameter(RequestSpecification reqSpec,String authToken,int paramValue, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).when().delete(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_PUT_REQUEST with auth-token and String path parameter */
	
	public Response updateByParameter(RequestSpecification reqSpec,String authToken,String paramValue, String requestBody,String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).body(requestBody).when().put(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_PUT_REQUEST with auth-token and int path parameter */
	
	public Response updateByParameter(RequestSpecification reqSpec,String authToken,int paramValue, String requestBody,String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken).pathParam("paramKey", paramValue).body(requestBody).when().put(endPoint+"/{paramKey}");	
		return response;
	}
	
	/* Reusable code for HTTP_PUT_REQUEST with auth-token without path param****/
	 public Response create_PutByAuth(RequestSpecification reqSpec, String authToken ,String requestBody, String endPoint) {
		Response response = reqSpec.header("Authorization","Bearer "+authToken)
				.body(requestBody).when().put(endPoint);
		return response;
	}

	/* Reusable code to deserialize a JSON response string to an object of the POJO class*/
	
	public <T> T getResponseInObject(Response response, Class<T> responseType) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T objectResponse = mapper.readValue(response.getBody().asString(), responseType);
        return objectResponse;
    }
	
	/* Reusable code to serialize an object to a JSON string */
	public String getRequestAsString(Object request) {
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            System.out.println("Exception occurred while converting the request to a JSON string");
            e.printStackTrace();
            return null;
        }
    }
	
	/* Reusable code for extracting particular given string value from response */
	
	public String extractStringFromResponse(Response response, String req) {
		String reqString = response.jsonPath().getString(req).trim();
		return reqString;
		
	}
/*====================Reusable code for updating field in object and return object=======================*/	
	public <T> T upDateRequestBodyField_PUT(T orgObject, String attribute, String value) {
	    T newObject = orgObject;
	    if (attribute.equalsIgnoreCase("programname")) {
	        newObject = setField(newObject, "programName", value);
	    } else if (attribute.equalsIgnoreCase("programdescription")) {
	        newObject = setField(newObject, "programDescription", value);
	    } else if (attribute.equalsIgnoreCase("programstatus")) {
	        newObject = setField(newObject, "programStatus", value);
	    } else {
	        System.out.println("Invalid attribute type: " + attribute);
	        return null;
	    }
	    return newObject;
	}

	private <T> T setField(T object, String fieldName, String value) {
	    try {
	        Field field = object.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        field.set(object, value);
	        return object;
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	        return null;
	    }catch (IllegalAccessException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
/*===========New addition File IO reusable methods to generate serial number and attach it to program name==============================*/
	
	 //public  String numberFile = "./src/test/resources/TestData/serialNumberContainer.txt";
	public String numberFile = EnvConstants.file_Path_numberFile;
	    public  String namePrefix = "Team13_HackNest_APIPhase2_";

	    public  int readFromFile(String fname) {
	        int number = -1;
	        try {
	            File inputFile = new File(fname);
	            Scanner scanner = new Scanner(inputFile);

	            while (scanner.hasNextInt()) {
	                number = scanner.nextInt();
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return number;
	    }
	    public  void writeToFile(int n, String fname) {
	        try {
	            File outputFile = new File(fname);
	            FileWriter writer = new FileWriter(outputFile);

	            writer.write(String.valueOf(n)); // Convert int to String and write
	            writer.close();
	            //System.out.println("Integer "+n+" written to file successfully! ...");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public  int getNextInteger() {
	        int num = 1;
	        try {
	            Path path = Paths.get(numberFile);
	            if (Files.exists(path)) {
	                num = readFromFile(numberFile);
	            } else {
	                File file = new File("filePath");
	                file.createNewFile();
	            }
	        } catch (IOException e) {
	            System.err.println("Error creating file: " + e.getMessage());
	        }
	        writeToFile(num+1, numberFile);
	        return num;
	    }
	    
	    public  String generateProgramName() {
	        int id = getNextInteger();
	        System.out.println(namePrefix+""+id);
	        return (namePrefix+""+id);
	    }
}