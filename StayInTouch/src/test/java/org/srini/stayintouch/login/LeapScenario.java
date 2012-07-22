package org.srini.stayintouch.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;


public class LeapScenario {
	
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void prepare(){
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		((HtmlUnitDriver)driver).setJavascriptEnabled(true);
		//driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Given("^the user goes to the login page (.*)$")
	public void gotoHomePage(String runId){
		driver.get(baseUrl + "/stayintouch/app/spring/welcome");
	}
	
	@And("^the user selects the year (.*)$")
	public void selectYear(String year){
		new Select(driver.findElement(By.id("year"))).selectByVisibleText(year);
	}
	
	@When("^the user selects a month (.*) (.*)$")
	public void selectMonth(String month, String id){
		new Select(driver.findElement(By.id("month"))).selectByVisibleText(month);
	}
	
	@Then("^the user should have this day to select (.*)$")
	public void checkPresenseofDate(String dateCheck){
		assertEquals(dateCheck,driver.findElement(By.cssSelector("option[value='"+dateCheck+"']")).getText());
	}
	
	@After
	public void closeAll(){
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}