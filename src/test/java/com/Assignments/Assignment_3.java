package com.Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment_3 {
	
	WebDriver driver;
	@Test
	public void Capture_Error() throws InterruptedException {
		
		Thread.sleep(3000);
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
		
		Thread.sleep(3000);
		
		String Act_Text = driver.switchTo().alert().getText();
		String Exp_Text = "Customer ID  cannot be left blank.";
		
		//Thread.sleep(3000);
		
		driver.switchTo().alert().accept();
		Assert.assertEquals(Act_Text, Exp_Text);
		
		driver.switchTo().defaultContent(); // This will take you out from frame to outside
	}
	
	@Test(priority = 1)
	public void Login() throws InterruptedException {
		Thread.sleep(5000);
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("1000");
		driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(10000);
		String Act_Text = driver.findElement(By.xpath("//label[normalize-space()='Password/IPIN']")).getText();
		String Exp_Text = "Password/IPIN";
		Assert.assertEquals(Act_Text, Exp_Text);
	}
	 
	@BeforeTest
	public void open_browser() {
		driver = new ChromeDriver();
		//driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
	}
	
	@AfterTest
	public void close_browser() {
		driver.close();
	}
}
