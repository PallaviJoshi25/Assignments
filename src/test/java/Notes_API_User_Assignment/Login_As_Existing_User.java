package Notes_API_User_Assignment;

import org.testng.annotations.Test;

public class Login_As_Existing_User extends Base_Class {
	@Test
	public static String Login_Using_BaseClass(){
		String token = Base_Class.Login("pallavijoshi2002@gmail.com","1234567890");
		System.out.println("Token is"+token);
		return token;
	}
}
