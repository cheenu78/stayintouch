package org.srini.stayintouch.controllers.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@Table(name = "user_table", catalog = "srini")
@XStreamAlias("user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private Integer id;
	
	private UserDetails userDetails;

	@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@Fetch(FetchMode.JOIN)
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_table_seq")
	@SequenceGenerator(name="user_table_seq", sequenceName = "user_table_seq")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EMAIL", unique = true, nullable = false, length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false, length = 40)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return "User Name "+ email+" Password "+password;
	}
}
