package Notes_API_User_Assignment;

import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base_Class {
	public static String outh_token;
	public static String Login(String email,String password) {
		RestAssured.baseURI = "https://practice.expandtesting.com";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email);
		requestParams.put("password", password);
		
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		
		Response response = request.request(Method.POST, "/notes/api/users/login");
		response.prettyPrint();
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		outh_token = jsonPathEvaluator.get("data.token").toString();
		//System.out.println("oAuth Token is =>  " + outh_token);
		return outh_token;
	
}
	public static void Create_New_User(String name, String email, String pass) {

		RestAssured.baseURI = "https://practice.expandtesting.com/notes/api/";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("email", email);
		requestParams.put("password", pass);
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		// POST the Response
		Response response = request.request(Method.POST, "/users/register");
		
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		// System.out.println(response.asString());
		Assert.assertEquals(statusCode, 201);
		// To get the Token from JSON Response
		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		String success_msg = jsonPathEvaluator.get("message").toString();
		Assert.assertEquals(success_msg, "User account created successfully");
	}
	
	public static void deleteAccount(String token) throws IOException, ParseException, org.json.simple.parser.ParseException 
	  {
		  RestAssured.baseURI = "https://practice.expandtesting.com";
	      RequestSpecification request = RestAssured.given();
		  request.header("Content-Type", "application/json");
		  request.header("X-Auth-Token", token);
	      Response response = request.request(Method.DELETE,"/notes/api/users/delete-account");
	      response.prettyPrint();
	      int statusCode = response.getStatusCode();
	      Assert.assertEquals(statusCode, 200);
	      System.out.println("Account deleted!");
	  }
}
