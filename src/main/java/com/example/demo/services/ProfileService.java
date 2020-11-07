package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ProfileService {

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


}

