package org.srini.stayintouch.controllers.dao;

import java.io.Serializable;
import java.sql.DatabaseMetaData;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.controllers.model.UserDetails;
import org.srini.stayintouch.util.CustomHibernateDaoSupport;


@Repository("userDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {
	
	public boolean findUser(String email, String password){
		Session session = getSession();
		
		//========== checking the isolation details------------------------------------------------------------
		try{
			DatabaseMetaData meta = session.connection().getMetaData();
			logger.info("Default Tx Isolation: " + meta.getDefaultTransactionIsolation());
			logger.info("Current Tx Isolation: " + getSession().connection().getTransactionIsolation());
		}catch(Exception e){
			e.printStackTrace();
		}
		//=====================================================================================================
		
		Query query = session.createQuery("from org.srini.stayintouch.controllers.model.User user WHERE user.email=? and user.password=?");
		query = query.setString(0, email);
		query = query.setString(1, password);
		User user =  (User)query.uniqueResult();
		if(user == null){
			return false;
		}else{
			return true;
		}
	}
	
	public Serializable saveUser(User user){
		return getHibernateTemplate().save(user);
	}

	public Serializable saveUserDetails(UserDetails userDetails) {
		return getHibernateTemplate().save(userDetails);
	}

	public boolean findUser(String email) {
		Session session = getSession();
		Query query = session.createQuery("from org.srini.stayintouch.controllers.model.User user WHERE user.email=?");
		query = query.setString(0, email);
		User user =  (User)query.uniqueResult();
		if(user == null){
			return false;
		}else{
			return true;
		}
	}
	
	public User findUserByEmail(String email) {
		Session session = getSession();
		Query query = session.createQuery("from org.srini.stayintouch.controllers.model.User user WHERE user.email=?");
		query = query.setString(0, email);
		User user =  (User)query.uniqueResult();
		return user;
	}
}
