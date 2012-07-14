package org.srini.stayintouch.controllers.dao;

import java.io.Serializable;

import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.controllers.model.UserDetails;

public interface UserDao{
	
	public boolean findUser(String email, String password);
	public boolean findUser(String email);
	public User findUserByEmail(String email);
	public Serializable saveUser(User user);
	public Serializable saveUserDetails(UserDetails userDetails);
	
}
