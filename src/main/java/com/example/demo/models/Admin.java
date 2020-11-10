package com.example.demo.models;

public class Admin extends Profile {

    //Det vil give mening so far at lave den abstact, da vi ikke skal lave flere objekter af den.

    public Admin(String email, String password) {
        super(email, password);
        email = "admin";
        password = "admin";
    }

    public Admin() {

    }
}
