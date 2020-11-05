package com.example.demo.models;

public abstract class Profil {

    private String email;
    private String password;

    public Profil(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public Profil() {
        //Default constructor
    }


}
