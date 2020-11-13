package com.example.demo.services;

import java.util.ArrayList;

public class MyProfile {
	
	public ArrayList<String> testList = new ArrayList<>();
	
    public ArrayList<String> deletePhoto(int indexOfPhoto) {

		return testList;
    }

    public boolean checkPassword(String password) {

		if( password.length() > 4 && password.length() < 20 ) {
			return true;
		} 
		
        return false;
    }

    public boolean checkEmail(String email) {

		if (email == null || email.isBlank()) {
			return false;
		} else if (email.length() > 4 && email.indexOf('@') > 0 && email.indexOf('.')  > email.indexOf('@')) {
			return true;
		}
		
        return false;
    }

    public boolean isLoggedIn(){
        return true;
    }

}
