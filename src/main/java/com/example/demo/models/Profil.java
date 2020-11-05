package com.example.demo.models;

public abstract class Profil {

    private String email;
    private String password;

    public Profil(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
    	return this.email;
	}
	
	public String getPassword() {
    	return this.password;
	}
    

    public Profil() {
        //Default constructor
    }


}
