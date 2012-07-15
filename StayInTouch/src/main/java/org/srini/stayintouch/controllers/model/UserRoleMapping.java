package org.srini.stayintouch.controllers.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USER_ROLE_MAPPING", catalog = "srini")
public class UserRoleMapping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private User user;
	
	private UserRole userRole;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_ROLE_MAPPING_SEQ")
	@SequenceGenerator(name="USER_ROLE_MAPPING_SEQ", sequenceName = "USER_ROLE_MAPPING_SEQ")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="USER_TABLE_ID", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}