package com.example.demo.services;

import com.example.demo.models.User;

import java.util.ArrayList;

public class MyProfile {
	
	public ArrayList<String> testList = new ArrayList<>();
	
	
	

    public ArrayList<String> deletePhoto(int indexOfPhoto) {

    	//Husk at tilføje en User parameter
    	
		testList.add("billede1");
		testList.add("billede2");
		testList.add("Billede3");
		
		testList.remove(indexOfPhoto);

		System.out.println(testList.toString());
		
		return testList;
    }

    public boolean checkPassword(String password) {

    	//ToDo 
		/* 
		*  Tilføj password securities.
		*  */
    	
		if( password.length() > 4 && password.length() < 20 ) {
			return true;
		} 
		
        return false;
    }

    public boolean checkEmail(String email) {
        //INDSÆT kode, der laver op til vores email krav og om den allerede er i brug.
		
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
