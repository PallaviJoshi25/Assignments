package Notes_API_User_Assignment;

import org.testng.annotations.Test;

public class Create_New_User {
	String outh_token;

	@Test
	public void before() {

		Base_Class.Create_New_User("plkmnhjui", "plkmnjik@gmail.com", "Epsilon@123");
	}

}
