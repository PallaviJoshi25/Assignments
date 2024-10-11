package Notes_API_User_Assignment;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Update_User {
	static String token;

	@BeforeTest
	public static void Login() {
		token = Login_As_Existing_User.Login_Using_BaseClass();
	}

	@Test
	public static void update() {
	RestAssured.baseURI = "https://practice.expandtesting.com";
	RequestSpecification request = RestAssured.given();

	JSONObject requestParams = new JSONObject();
	requestParams.put("name", "Ayan");
	requestParams.put("phone", "21323418583*");
	requestParams.put("company", "xyz");

	// Add a header stating the Request body is a JSON
	request.header("Content-Type", "application/json");
	request.header("x-auth-token", token);
	request.body(requestParams.toJSONString());
	//request.header
	// POST the Response
	Response response = request.request(Method.PATCH, "/notes/api/users/profile");
	// Response response = request.request(Method.POST,"/spree_oauth/token");
	response.prettyPrint();
	int statusCode = response.getStatusCode();}

}
