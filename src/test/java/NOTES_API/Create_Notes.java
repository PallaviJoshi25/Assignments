package NOTES_API;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NOTES_API.Base_Class;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import NOTES_API.Delete_All;

public class Create_Notes extends Base_Class{

		static String Token;
		static String notesid;

		@BeforeTest
		public static void Login() throws IOException, ParseException {
			Token = Base_Class.LOGIN("suchetha01@mail.com", "API05**");
			System.out.println("LOGIN");
			Delete_All.deleteAllNotes();
		}


		@Test
		public static void create() throws IOException, ParseException {
			
			JSONParser jsonparser = new JSONParser();
			// Create object for FileReader class, which help to load and read JSON file
			FileReader reader = new FileReader(".\\TestData\\Notess.json");
			// Returning/assigning to Java Object
			Object obj = jsonparser.parse(reader);
			// Convert Java Object to JSON Object, JSONObject is typecast here
			JSONObject prodjsonobj = (JSONObject) obj;
			System.out.println("PASS");

			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.header("x-auth-token", Token);
			request.body(prodjsonobj.toJSONString());
			// POST the Response
			Response response = request.request(Method.POST, "/notes/api/notes");
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200);
			// To get the Token from JSON Response
			//JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			notesid = jsonPathEvaluator.get("data.id").toString();
			
			String fname = jsonPathEvaluator.get("data.title").toString();
			System.out.println("Title is =>  " + fname);
			Assert.assertEquals("Practice WebApp UI", fname);

		}
		
		@Test(priority = 1)
		  public void updatenotes() throws IOException, ParseException, org.json.simple.parser.ParseException 
		  {
			  
			  //Create json object of JSONParser class to parse the JSON data
			  JSONParser jsonparser=new JSONParser();
			  //Create object for FileReader class, which help to load and read JSON file
			  FileReader reader = new FileReader(".\\TestData\\NotessUpdate.json");
			  //Returning/assigning to Java Object
			  Object obj = jsonparser.parse(reader);
			  //Convert Java Object to JSON Object, JSONObject is typecast here
			  JSONObject prodjsonobj = (JSONObject)obj;
			  
			  RestAssured.baseURI = "https://practice.expandtesting.com";
		      RequestSpecification request = RestAssured.given();
			  request.header("Content-Type", "application/json");
			  request.header("X-Auth-Token", Token);
		      request.body(prodjsonobj.toJSONString());
		      // POST the Response
		      Response response = request.request(Method.PUT,"/notes/api/notes/"+notesid);
		      //Response response = request.request(Method.POST,"/spree_oauth/token");
		      response.prettyPrint();
		      int statusCode = response.getStatusCode();
		      // System.out.println(response.asString());
		      Assert.assertEquals(statusCode, 200);
		      // To get the Token from JSON Response
//		      JsonPath jsonPathEvaluator = response.getBody().jsonPath();
//		      id = jsonPathEvaluator.get("data.id").toString();
//		      System.out.println("Notes ID is =>  " + id);
		  }

	}

