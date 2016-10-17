package com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="RoleMaster")
public class RoleMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",
	joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
	private List<UserMaster> userList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserMaster> getUserList() {
		return userList;
	}

	public void setUserList(List<UserMaster> userList) {
		this.userList = userList;
	}
	
	
}