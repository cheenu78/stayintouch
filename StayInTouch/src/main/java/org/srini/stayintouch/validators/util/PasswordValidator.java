package org.srini.stayintouch.validators.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	private static final String str = ".*\\d.*";
	
	public void initialize(Password constraintAnnotation) {
		
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile(str);
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
