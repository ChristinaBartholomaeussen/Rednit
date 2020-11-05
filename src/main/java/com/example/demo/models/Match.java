package com.example.demo.models;


import java.util.ArrayList;
import java.util.List;

public class Match {

    private List<User> matches = new ArrayList<>();

    public void addToMatches(User user) {

        matches.add(user); //Hvis der er et match, skal det på denne liste
    }

    public void deleteMatch() {
        //INDSÆT KODE
    }

    public boolean isAMatch() {
        //INDSÆT KODE
        return true;
    }

    public List getMatches() {
        return matches;

    }



}
