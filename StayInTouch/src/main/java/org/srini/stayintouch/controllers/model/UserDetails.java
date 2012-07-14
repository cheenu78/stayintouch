package org.srini.stayintouch.controllers.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "user_details", catalog = "srini")
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private Date birthDay;
	private String gender;
	
	private Integer id;
	
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_details_seq")
	@SequenceGenerator(name="user_details_seq", sequenceName = "user_details_seq")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="USER_TABLE_ID", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/*@Column(name = "USER_TABLE_ID", unique = false, nullable = false)
	public Integer getUserTableId() {
		return userTableId;
	}

	public void setUserTableId(Integer userTableId) {
		this.userTableId = userTableId;
	}*/

	@Column(name = "FIRST_NAME", unique = false, nullable = true, length = 45)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", unique = false, nullable = true, length = 45)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "GENDER", unique = false, nullable = true, length = 1)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String toString(){
		return "First Name "+ firstName+" Last Name "+lastName+ " Birthday "+birthDay+" Gender"+gender;
	}
}