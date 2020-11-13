package com.example.demo.models;

public abstract class Profile {

    private String email;
    private String password;

    public Profile(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Profile(String email) {
        this.email = email;
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

    public Profile()
    {
    }
}
