package org.srini.stayintouch.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



@RunWith(value=Parameterized.class)
public class StayInTouchTest2 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	String email;
	String password;
	
	//@Before
	public void setUp() throws Exception {
		
		driver = new InternetExplorerDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public StayInTouchTest2(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	
	@Parameters
	public static Collection createData(){
		Object [][] retObj = {
				{"cheenu78@gmail.com","njally_123"},
				{"selenium@123.com","selenium_123"}
		};
		return Arrays.asList(retObj);
	}

	@Test
	@Category(org.srini.stayintouch.login.StayInTouchTest2.class)
	public void testStayInTouchTest2() throws Exception {
		setUp();
		driver.get(baseUrl + "stayintouch/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys(email);
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys(password);
		driver.findElement(By.linkText("Login")).click();

		assertEquals("Logout",driver.findElement(By.linkText("Logout")).getAttribute("value"));
		driver.findElement(By.linkText("Logout")).click();
		tearDown();
	}

	//@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
