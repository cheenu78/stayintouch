package org.srini.stayintouch.validators;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.srini.stayintouch.validators.util.Password;

public class UserDetailsValidator {

	@NotEmpty(message = "First Name must not be empty")
	@Size(min = 1, max = 45, message = "First Name must be between 1 to 45 characters in size")
	private String firstName;
	
	@NotEmpty(message = "Last Name must not be empty")
	@Size(min = 1, max = 45, message = "Last Name must be between 1 to 45 characters in size")
	private String lastName;
	
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Not a valid email address")
	@Size(min = 10, max = 100, message = "Email size must be between 10 to 100 Characters")
	private String entryEmail;
	
	@NotEmpty(message = "Password must not be empty")
	@Password(message = "Password must contain atleast one numerical character")
	private String enterPassword;
	
	@NotEmpty(message = "Password must not be empty")
	@Password(message = "Password must contain atleast one numerical character")
	private String reenterPassword;
	
	@Min(value = 1, message = "Please Select Male or Female")
	private String gender;
	
	private List<Integer> years;
	private Map<Integer, String> months;
	private List<Integer> days;
	
	private Integer year;
	private Integer month;
	private Integer day;
	
	private String passwordError;
	
	private String signUpError;
	
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEntryEmail() {
		return entryEmail;
	}
	public void setEntryEmail(String entryEmail) {
		this.entryEmail = entryEmail;
	}
	public String getReenterPassword() {
		return reenterPassword;
	}
	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}
	public String getEnterPassword() {
		return enterPassword;
	}
	public void setEnterPassword(String enterPassword) {
		this.enterPassword = enterPassword;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	private String loginError;
	
	
	public String getLoginError() {
		return loginError;
	}
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	public Map<Integer, String> getMonths() {
		return months;
	}
	public void setMonths(Map<Integer, String> months) {
		this.months = months;
	}
	
	public String toString(){
		return "firstName: "+firstName+
		"lastName: "+lastName+
		"entryEmail: "+entryEmail+
		"reenterPassword: "+reenterPassword+
		"enterPassword: "+enterPassword+
		"gender: "+gender+
		"year: "+year+
		"month: "+month+
		"day: "+day;
	}
	public String getSignUpError() {
		return signUpError;
	}
	public void setSignUpError(String signUpError) {
		this.signUpError = signUpError;
	}
	public List<Integer> getDays() {
		return days;
	}
	public void setDays(List<Integer> days) {
		this.days = days;
	}
}
