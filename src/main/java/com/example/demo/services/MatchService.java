package com.example.demo.services;

import com.example.demo.models.Match;
import com.example.demo.models.User;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MatchService {
	ArrayList<Match> matchList = new ArrayList<Match>();
    MatchRepository matchRepository = new MatchRepository();

    public void insertPotentialMatch(int userFrom, int userTo) {
        matchRepository.InsertMatchIntoMatchListInDatabase(userFrom, userTo);
    }

    public List<Match> getAllMatch() {
        return matchRepository.selectAllMatchesFromDatabase();
    }
}
