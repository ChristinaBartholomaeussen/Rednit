package com.example.demo.repositories;

import com.example.demo.models.Match;
import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    ConnectionRepository connection = new ConnectionRepository();

    public void deleteMatchFromTableInDatabase(User user){

    }

    public void addToMatchTableInDatabase(User loggedInUser, User potentiallyMatch){
        //TODO
        //Husk, at begge users lister skal opdateres
    }

    public List<User> getAllMatchesFromDatabase(){
        List<User> allMatches = new ArrayList<User>();

        //TODO

        return allMatches;
    }
}
