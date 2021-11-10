package com.hms.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.hms.model.Roles;

public class UserDTO {

	private Long id;
	
    private String username;

  //  private String password;
    
    private String mobileNo;
    
    private Set<Roles> roles = new HashSet<Roles>();
    
    public UserDTO() {
    	
    }

	public UserDTO(Long id, String username, String password, String mobileNo, Set<Roles> roles) {
		this.id = id;
		this.username = username;
//		this.password = password;
		this.mobileNo = mobileNo;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

/*	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	
    
    
    
}
