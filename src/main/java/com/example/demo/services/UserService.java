package com.example.demo.services;

import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> allUsers = new ArrayList<>();


    /*****Kunne være en idé af bruge dette, så vi ikke oprettet en ny liste hver gang,
     * hvis det kan i forhold til vores database. På den på kan vi også bruge listen
     * og metoderne i klassen andre steder i vores kode, uden at den laver en ny liste****
     */
/*
    private static MyUser single_instance = null;

    public static MyUser getInstance()
    {
        if(single_instance == null)
        {
            single_instance = new MyUser();
        }

        return single_instance;

    }

 */

    public void createUser() {
        //TODO
        //INDSÆT KODE
    }

    public void addToAllUsers(User user) {
        allUsers.add(user); //Vi adder user til den fulde liste
    }

    public void deleteUser(User user) {
        //INDSÆT KODE
    }


    public void saveUserProfileChanges() {
        //TODO
        //INDSÆT KODE
    }

    public void uploadPhoto() {
        //TODO
        //INDSÆT KODE
    }

    public void getUser() {
        //TODO
        //INDSÆT KODE
    }

    public void getNextUser() {
        //TODO
        //INDSÆT KODE
    }

    public boolean likeUser(User user) {
        //TODO
        //INDSÆT KODE OBS PÅ MyCandidate

        return true;
    }

    public List getAllUsers(){
        return null;
    }


}
