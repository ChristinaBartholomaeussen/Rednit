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

    public List<User> getMatches(User user) {
        return matchRepository.selectUserMatchesFromDatabase(user.getIdUser());
    }

    public List<Match> getAllMatch() {
        return matchRepository.selectAllMatchesFromDatabase();
    }
    
    public ArrayList<Match> getAllMatches( User activeUser) {
		MatchService matchService = new MatchService();

		for (Match match : matchService.getAllMatch()) {

			if (match.getIdUserMatch() == activeUser.getIdUser() && match.getIdUser() == activeUser.getIdUserMatch()) {
				System.out.println("Burde være et match imellem: " + activeUser.getIdUser() + " og " + match.getIdUser());
				matchList.add(new Match(activeUser.getIdUser(), match.getIdUser()));
			}
		}
		return matchList;
	}
}
