package com.example.demo.models;

import java.util.List;

public abstract class Profil {

    private String email;
    private String password;
    private List profiles;

    public Profil(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
    	return email;
	}
	
	public String getPassword() {
    	return password;
	}
	
	public void setPassword(String password) {this.password = password;}

	public void setEmail(String email){
        this.email = email;
    }

	public List getAllProfiles(){
        return profiles;
    }

    public Profil() {
        //Default constructor
    }




}
