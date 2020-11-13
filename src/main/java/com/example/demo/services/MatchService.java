package com.example.demo.services;

import com.example.demo.models.Match;

import com.example.demo.repositories.MatchRepository;



import java.util.List;

public class MatchService {

    MatchRepository matchRepository = new MatchRepository();

    public void insertPotentialMatch(int userFrom, int userTo) {
        matchRepository.InsertMatchIntoMatchListInDatabase(userFrom, userTo);
    }

    public List<Match> getAllMatch() {
        return matchRepository.selectAllMatchesFromDatabase();
    }
}
