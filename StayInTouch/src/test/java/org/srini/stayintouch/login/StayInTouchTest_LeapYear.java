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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(value=Parameterized.class)
public class StayInTouchTest_LeapYear {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	String year;
	String month;
	String finalValue;
	
	public StayInTouchTest_LeapYear(String year, String month, String finalValue){
		this.year = year;
		this.month = month;
		this.finalValue = finalValue;
	}
	
	@Parameters
	public static Collection<Object[]> createData(){
		Object [][] retObj = {
				{"1998","February", "28"},
				{"2000","February", "29"}
		};
		return Arrays.asList(retObj);
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	@Category(org.srini.stayintouch.login.StayInTouchTest_LeapYear.class)
	public void testStayInTouchTest_LeapYear() throws Exception {
		driver.get(baseUrl + "/stayintouch/app/spring/welcome");

		new Select(driver.findElement(By.id("year"))).selectByVisibleText(this.year);
		new Select(driver.findElement(By.id("month"))).selectByVisibleText(this.month);
		assertEquals(this.finalValue,driver.findElement(By.cssSelector("option[value='"+this.finalValue+"']")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}