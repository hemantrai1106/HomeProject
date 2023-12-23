package com.srisaiHome.srisaiHome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotBlank(message="Name field is Required !!")
	String name;
	@NotBlank(message="UserName field is Required !!")
	String username;
	@NotBlank(message="Password field is Required !!")
	@Size(min=6,max=12,message="password length should be in between 6 to 12 characters")
	String password;
	
	private String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", phoneNo="
				+ phoneNo + ", gender=" + gender + ", email=" + email + "]";
	}
	String phoneNo;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String username, String password, String phoneNo, String gender, String email) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String gender;
	String email;
}
