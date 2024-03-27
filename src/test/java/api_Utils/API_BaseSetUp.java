package api_Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class API_BaseSetUp {

	RequestSpecification req;

	// RequestSpecBuilder is for reusable codes

	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log = new PrintStream(new FileOutputStream("Team13_HackNest_RestAssuredLogs.txt"));

		req = new RequestSpecBuilder().setBaseUri("https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms").addQueryParam("null", "null")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();

		return req;
	}

}
