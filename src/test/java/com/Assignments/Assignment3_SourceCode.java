package com.Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_SourceCode {
	EdgeDriver driver;
	// WebDriver driver;

	@Test(priority=2)
	public void EnterCustomerID() throws InterruptedException {
		Thread.sleep(5000);
		driver.switchTo().frame("login_page");
		//driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("1000");
		//driver.findElementByXPath("//img[@alt='continue']").click();
		//driver.findElement(By.xpath(""));
		driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='Password/IPIN']")).isDisplayed();
		//driver.switchTo().frame(1);
		//driver.findElement(By.xpath("Terms and Conditions")).click();
		
	}
	

	@Test(priority=1)
	public void Donot_Enter_CustomerID() throws InterruptedException {
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//a[contains(text(),'CONTINUE')]")).click();
		String ExpAlertMsg="Customer ID  cannot be left blank.";
		String ActAlertMsg=driver.switchTo().alert().getText();
		Assert.assertEquals(ExpAlertMsg, ActAlertMsg);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		// This will take you out from frame to outside
		driver.switchTo().defaultContent();
	}

	// Pre-Condition
	@BeforeTest
	public void LaunchBrowser() {
		//WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		// This will wait for Page to load
		// driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		// This will store or rememember the cookies or navigation in terms of
		// back and forward
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
	}

	// Post Condition
	@AfterTest
	public void CloseBrowser() {
		//driver.quit();
	}
}
