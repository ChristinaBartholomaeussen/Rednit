package com.example.demo.models;

import com.example.demo.repositories.MatchRepository;

import java.util.List;

public class Match extends User {

    public Match(int idUser, int idUserMatch) {
        super(idUser, idUserMatch);
    }

    public Match() {

    }
}
