package com.Assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//public class Assignment_4 {
/*
 * Go to Demo Web Shop
 * Click on the Computer -> Desktop
 * Add the desktop to the cart whose Price is 1800
 * Verify the price
 * Click Add to cart
 * Go to shopping cart and verify the cost
 * Change the quantity
 * Check the check box and Click on the Update Shopping carts  
 * Verify the Total price
 * 
 * Add product to the cart
 * Delete product from the cart
 * */
//	WebDriver driver;
	@Test
//	public void GoToShoppingCart(){
//		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
//		String Act_Val = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
//		String Exp_Val = "1800.00";
//		Assert.assertEquals(Act_Val,Exp_Val);
//		
//		driver.findElement(By.xpath("(//input[@name='itemquantity4608130'])")).clear();
//		driver.findElement(By.xpath("(//input[@name='itemquantity4608130'])")).sendKeys("2");
//		driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
//		
//		String Act_Val_1 = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
//		System.out.println(Act_Val_1);
//		String Exp_Val_1= "3600.00";
//		Assert.assertEquals(Act_Val_1,Exp_Val_1);
//		
//		String total_val = driver.findElement(By.xpath("//span[@class='product-price'][normalize-space()='3600.00']")).getText();
//		Assert.assertEquals(total_val,Act_Val_1);
//		
//		driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
//		driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
//		
//		String Exp_Msg = driver.findElement(By.xpath("//div[@class='order-summary-content']")).getText();
//		String Act_Msg = "Your Shopping Cart is empty!";
//		Assert.assertEquals(Exp_Msg,Act_Msg);
//	}
	
//	@BeforeTest
//	public void open_browser() {
//		driver = new ChromeDriver();
//		//driver = new EdgeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://netbanking.hdfcbank.com/netbanking/");
//	}
	


	public class Assignment_4 {
		WebDriver driver;
		WebElement tblAllOrders;
		@Test
		public void login() throws InterruptedException {

			Thread.sleep(3000);
			driver.findElement(By.className("ico-login")).click();
			driver.findElement(By.className("email")).sendKeys("darshithtn@gmail.com");
			driver.findElement(By.className("password")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			
			Thread.sleep(5000);
			driver.findElement(By.className("cart-label")).click();
			Thread.sleep(3000);
			
			if (!driver.findElement(By.xpath("//div[@class='order-summary-content']")).isDisplayed()) {
				List<WebElement> checkboxs = tblAllOrders.findElements(By.xpath("//td/span[normalize-space()=\"Remove:\"]/following-sibling::input"));
				for (WebElement checked : checkboxs) {
					if (checked.isSelected() != true) {
						checked.click();
					}
				}
			}
			else {
				
			}

			WebElement MyAccount = driver.findElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(MyAccount).perform();
			driver.findElement(By.linkText("Desktops")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//span[@class='price actual-price' and text()='1800.00']/ancestor::div[@class='add-info']//input[@class='button-2 product-box-add-to-cart-button']"))
					.click();
			Thread.sleep(5000);
			driver.findElement(By.name("product_attribute_74_5_26")).click();
			String act = driver.findElement(By.className("price-value-74")).getText();
			String exp = "1800.00";
			Assert.assertEquals(act, exp);
			driver.findElement(By.xpath("(//input[@id='add-to-cart-button-74'])[1]")).click();
			Thread.sleep(3000);
			String Actual = driver.findElement(By.xpath("(//p[@class='content'])[1]")).getText();
			String Exp = "The product has been added to your shopping cart";
			Assert.assertEquals(Actual, Exp);
			

		}

		@Test(priority = 1)
		public void GoToShoppingCart(){
			driver.findElement(By.linkText("Shopping cart")).click();
			String Act_Val = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
			String Exp_Val = "1800.00";
			Assert.assertEquals(Act_Val,Exp_Val);
			
			driver.findElement(By.xpath("//span[normalize-space()='Qty.:']/following-sibling::input")).clear();
			driver.findElement(By.xpath("//span[normalize-space()='Qty.:']/following-sibling::input")).sendKeys("2");
			driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
			
			String Act_Val_1 = driver.findElement(By.xpath(" //span[@class='product-subtotal']")).getText();
			System.out.println(Act_Val_1);
			String Exp_Val_1= "3600.00";
			Assert.assertEquals(Act_Val_1,Exp_Val_1);
			
			String total_val = driver.findElement(By.xpath("//span[@class='product-price'][normalize-space()='3600.00']")).getText();
			Assert.assertEquals(total_val,Act_Val_1);
			
			driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
			driver.findElement(By.xpath(" //input[@name='updatecart']")).click();
			
			String Exp_Msg = driver.findElement(By.xpath("//div[@class='order-summary-content']")).getText();
			String Act_Msg = "Your Shopping Cart is empty!";
			Assert.assertEquals(Exp_Msg,Act_Msg);
		}


		@AfterTest
		public void close() {
			driver.quit();
		}

		@BeforeTest
		public void launch() {
			driver = new ChromeDriver();
			driver.get("https://demowebshop.tricentis.com/");
			driver.manage().window().maximize();	
		}
	}

	/*
	 * Empty the cart before adding
	 * Use DataProvider
	 * 
	 * */
