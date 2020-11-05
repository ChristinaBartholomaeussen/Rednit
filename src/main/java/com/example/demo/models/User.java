package com.example.demo.models;

import com.example.demo.services.MyUser;

import java.util.Date;
import java.util.List;

public class User extends Profil {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int gender;
    private int sexualPrefrerence;
    private String bio;
    private List photos;


    public User(String email, String password,
                String firstName, String lastName,
                Date dateOfBirth, int gender,
                int sexualPrefrerence, String bio)
    {
        super(email, password);
                this.firstName = firstName;
                this.lastName = lastName;
                this.dateOfBirth = dateOfBirth;
                this.gender = gender;
                this.sexualPrefrerence = sexualPrefrerence;
                this.bio = bio;
    }

    public User() {
       //Default constructor
    }

    public void createUser() {
        //TODO
        //INDSÆT KODE
    }









}
