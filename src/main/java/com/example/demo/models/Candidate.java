package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

    public List<User> likedUsers = new ArrayList<>();
    public List<User> dislikedUsers = new ArrayList<>();
    public List<User> potentialCandidates = new ArrayList<>();

    public void addToLikedList(User user){
        likedUsers.add(user); //Vi adder user til en liste over brugere, brugeren har liked
    }

    public void addToDislikedList(User user){
        dislikedUsers.add(user);
    }

    public void addToPotentialCandidates(User user){
        potentialCandidates.add(user);
    }


}
