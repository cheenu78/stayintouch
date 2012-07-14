package org.srini.stayintouch.controllers.bo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.srini.stayintouch.controllers.dao.UserDao;
import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.controllers.model.UserDetails;

@Service("userBo")
@Transactional
public class UserBoImpl {
	
	@Autowired
	private UserDao userDao;

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public boolean findUserbyNameandPassword(String email, String password){
		return userDao.findUser(email, password);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public boolean findUserbyName(String email){
		return userDao.findUser(email);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public User findUserbyEmail(String email){
		return userDao.findUserByEmail(email);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
	public Serializable saveUser(User user){
		return userDao.saveUser(user);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
	public Serializable saveUserDetails(UserDetails userDetails){
		return userDao.saveUserDetails(userDetails);
	}
}
