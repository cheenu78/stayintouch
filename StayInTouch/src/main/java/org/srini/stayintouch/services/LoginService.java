package org.srini.stayintouch.services;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.srini.stayintouch.controllers.bo.UserBoImpl;
import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.controllers.model.UserDetails;
import org.srini.stayintouch.controllers.model.UserRole;
import org.srini.stayintouch.controllers.model.UserRoleMapping;
import org.srini.stayintouch.validators.UserDetailsValidator;

@Service("loginService")
@Transactional
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private UserBoImpl userBo;
	
	@Transactional
	public boolean save(UserDetailsValidator userDetailsValidator) throws Exception{
		
		String email = userDetailsValidator.getEntryEmail();
		String firstName = userDetailsValidator.getFirstName();
		String lastName = userDetailsValidator.getLastName();
		String password = userDetailsValidator.getEnterPassword();
		String gender = userDetailsValidator.getGender();
		Integer year = userDetailsValidator.getYear();
		Integer month = userDetailsValidator.getMonth() - 1;
		Integer day = userDetailsValidator.getDay();
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		UserDetails userDetails = new UserDetails();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date birthDay = calendar.getTime();
		userDetails.setBirthDay(birthDay);
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastName);
		userDetails.setGender(gender);
		
		userBo.saveUser(user);
		userDetails.setUser(user);
		userBo.saveUserDetails(userDetails);
		
		UserRoleMapping mapping = new UserRoleMapping();
		UserRole userRole = userBo.getRoleById(2);
		mapping.setUser(user);
		mapping.setUserRole(userRole);
		userBo.saveUserRoleMapping(mapping);
		
		logger.info("Saved User "+user.getEmail());
		
		return false;
	}
	
	@Transactional
	public boolean findUserbyNameandPassword(String email, String password){
		return userBo.findUserbyNameandPassword(email, password);
	}
	
	@Transactional
	public boolean findUserbyName(String email){
		return userBo.findUserbyName(email);
	}
	@Transactional
	public User findUserbyEmail(String email){
		return userBo.findUserbyEmail(email);
	}
}
