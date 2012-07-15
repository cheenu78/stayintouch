package org.srini.stayintouch.controllers.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles", catalog = "srini")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleName;
	private Integer id;
	
	private List<UserRoleMapping> userRoleMapping;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="ROLE_ID")
	public List<UserRoleMapping> getUserRoleMapping() {
		return userRoleMapping;
	}

	public void setUserRoleMapping(List<UserRoleMapping> userRoleMapping) {
		this.userRoleMapping = userRoleMapping;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLES_SEQ")
	@SequenceGenerator(name = "USER_ROLES_SEQ", sequenceName = "USER_ROLES_SEQ")
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLE_NAME", unique = false, nullable = true, length = 45)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}