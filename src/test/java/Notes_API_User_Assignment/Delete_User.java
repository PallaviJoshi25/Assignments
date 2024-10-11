package Notes_API_User_Assignment;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Delete_User {
	String token;
	
	@BeforeTest
	public void login() {
		Base_Class.Create_New_User("accountDel", "accountTo@gmail.com", "test@123");
		token = Base_Class.Login("accountTo@gmail.com", "test@123");
	}
	@Test
	  public void delete() throws IOException, ParseException, org.json.simple.parser.ParseException 
	  {
		  Base_Class.deleteAccount(token);
	  }
}
