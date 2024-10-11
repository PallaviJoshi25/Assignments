package NOTES_Using_SELENIUM_ASSIGNMENT;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Delete_All_Notes {
	//EdgeDriver driver;
	ChromeDriver driver;
	WebElement tblAllOrders;

	@Test
	
	public void login() throws InterruptedException {
		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//		driver = new EdgeDriver();
		driver = new ChromeDriver();
		driver.get("https://practice.expandtesting.com/notes/app/login");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pallavijoshi2002@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	
	Thread.sleep(2000);
}

	@Test(priority = 1)
	public void delete_all_notes() throws InterruptedException {
		Thread.sleep(2000);

		if (!driver.getPageSource().contains("You don't have any notes in all categories")) {

//List<WebElement> delete_box = tblAllOrders.findElements(By.xpath("//button[contains(.,'Delete')]")); -> Works Fine
			List<WebElement> delete_box = driver.findElements(By.xpath("//button[contains(.,'Delete')]"));
			Thread.sleep(3000);

			for (WebElement checked : delete_box) {
				// if (checked.isSelected() != true) {
				checked.click();
				Thread.sleep(3000);
				//// button[@type='button'][normalize-space()='Delete']
				driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Delete']")).click();
				Thread.sleep(3000);
				// }
			}

		} else {
			System.out.println("sdfg");
		}
	}
}