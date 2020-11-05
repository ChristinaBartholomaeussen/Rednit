package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private List<User> likedUsers;
    private List<User> dislikedUsers;
    private List<User> potentialCandidates;

    public Candidate(){

    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public List<User> getDislikedUsers() {
        return dislikedUsers;
    }

    public List<User> getPotentialCandidates() {
        return potentialCandidates;
    }





}
