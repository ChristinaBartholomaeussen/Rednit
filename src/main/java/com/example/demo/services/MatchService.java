package com.example.demo.services;

import com.example.demo.models.Match;

import com.example.demo.repositories.MatchRepository;



import java.util.List;

public class MatchService {

    MatchRepository matchRepository = new MatchRepository();

	/**
	 * Sætter et match i databasen.
	 * @param userFrom
	 * @param userTo
	 */
	public void insertPotentialMatch(int userFrom, int userTo) {
        matchRepository.InsertMatchIntoMatchListInDatabase(userFrom, userTo);
    }

	/**
	 * Henter alle matches fra databasen
	 * @return
	 */
    public List<Match> getAllMatch() {
        return matchRepository.selectAllMatchesFromDatabase();
    }
}
