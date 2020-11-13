package com.example.demo.services;

import java.util.ArrayList;

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

