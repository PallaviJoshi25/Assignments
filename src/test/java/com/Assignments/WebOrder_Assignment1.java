package com.Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Assignments.WebOrder_TestData;

public class WebOrder_Assignment1 extends WebOrder_TestData{
	WebDriver driver;
	@BeforeTest
	public void openBrowser() throws InterruptedException {
		//WebDriverManager.firefoxdriver().setup();
		driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
//		Thread.sleep(3000);
		
		
	}
//	@Test(dataProvider = "product")
//	public void getData(String product,String quantity) {
//		driver.findElement(By.linkText("View all products")).click();
//		String MyMoney_ExpVal = driver.findElement(By.xpath("//td[normalize-space()='$100']")).getText();
//		String FamilyAlbum_ExpVal = driver.findElement(By.xpath("//td[normalize-space()='$80']")).getText();
//		String ScreenSaver_ExpVal = driver.findElement(By.xpath("//td[normalize-space()='$20']")).getText();
//		//driver.findElement(By.linkText("Order")).click();
//
//		driver.findElement(By.linkText("Order")).click();
//		Select se = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
//		se.selectByValue(product);
//		String ActVal = "$" + driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtUnitPrice']"));
//		Assert.assertEquals(MyMoney_ExpVal, ActVal);
//		Assert.assertEquals(FamilyAlbum_ExpVal, ActVal);
//		Assert.assertEquals(ScreenSaver_ExpVal, ActVal);
//	
//	}
	
	@Test(dataProvider="product1",dataProviderClass=WebOrder_TestData.class)
	public void Create_Order(String select) {
		driver.findElement(By.linkText("View all products")).click();
		String expValue = driver.findElement(By.xpath("//td[normalize-space()='"+select+"']/following-sibling::td[1]")).getText();
		expValue = expValue.substring(1);
		driver.findElement(By.linkText("Order")).click();
		Select se = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		se.selectByValue(select);
		String actValue = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtUnitPrice']")).getAttribute("value");	
		Assert.assertEquals(expValue, actValue);
	}
	
	
	@AfterTest
	public void post_condition() {
		
		driver.close();
	}
	
}
