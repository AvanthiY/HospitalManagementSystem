package com.hms.model;

import java.io.Serializable;
import java.util.List;

public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 2188763327256647648L;

	private String username;
	
	private String password;
	
	private String mobileNo;
	
	private List<Integer> roles;
	
	public AuthenticationRequest() {}
	
	public AuthenticationRequest(String username, String password, String mobileNo, List roles) {
		this.password = password;
		this.username = username;
		this.mobileNo = mobileNo;
		this.roles = roles;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
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
	

}
