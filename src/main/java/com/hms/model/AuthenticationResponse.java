package com.hms.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{
	
	private static final long serialVersionUID = 3142249070601570463L;

	private String user;
    
    private String token;
    
    public AuthenticationResponse() {}
    
    public AuthenticationResponse(String user, String token) {
    	this.user = user;
    	this.token = token;
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    

}
