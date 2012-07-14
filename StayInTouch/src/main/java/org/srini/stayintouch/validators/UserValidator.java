package org.srini.stayintouch.validators;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.srini.stayintouch.validators.util.Password;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("userValidator")
public class UserValidator {

	@NotEmpty(message = "Email must not be empty.")
	@Email(message = "Not a valid email address.")
	@Size(min = 10, max = 100, message = "Email must be between 10 to 100 characters in size.")
	private String j_username;
	
	@NotEmpty(message = "Password must not be empty.")
	@Password(message = "Password must contain atleast one numerical character.")
	private String j_password;
	
	private String loginError;
	
	private String authSuccess;
	
	private boolean _spring_security_remember_me;
	
	public String getAuthSuccess() {
		return authSuccess;
	}
	public void setAuthSuccess(String authSuccess) {
		this.authSuccess = authSuccess;
	}
	public String getLoginError() {
		return loginError;
	}
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	public boolean is_spring_security_remember_me() {
		return _spring_security_remember_me;
	}
	public void set_spring_security_remember_me(boolean _spring_security_remember_me) {
		this._spring_security_remember_me = _spring_security_remember_me;
	}
}
