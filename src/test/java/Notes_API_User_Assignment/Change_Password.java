package Notes_API_User_Assignment;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Notes_API_User_Assignment.Login_As_Existing_User;

public class Change_Password extends Login_As_Existing_User {

	static String token;

	@BeforeTest
	public static void Login() {
		token = Login_As_Existing_User.Login_Using_BaseClass();
	}

	@Test
	public static void ChangePassword() throws IOException, ParseException {
		RestAssured.baseURI = "https://practice.expandtesting.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		System.out.println("oAuth Token is =>  " + token);

		JSONObject requestParams = new JSONObject();
		requestParams.put("currentPassword", "12345678");
		requestParams.put("newPassword", "1234567890");
		request.body(requestParams.toJSONString());

		Response response = request.request(Method.POST, "notes/api/users/change-password");
		response.prettyPrint();

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		String message = jsonPathEvaluator.get("message").toString();
		System.out.println(message);
		
		Assert.assertEquals(message, "The password was successfully updated");
	}
}
