package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.UserRepository;

import java.util.List;

public class MatchService {

    MatchRepository matchRepository = new MatchRepository();
    UserRepository userRepository = new UserRepository();

    public void deleteMatch(User userFrom, User userTo) {
        matchRepository.deleteMatchFromMatchlistInDatabase(userFrom.getIdUser(), userTo.getIdUser());
    }

    public void insertMatch(User userFrom, User userTo) {
        matchRepository.InsertMatchIntoMatchListInDatabase(userFrom.getIdUser(), userTo.getIdUser());
    }

    public List<User> getMatches(User user) {
        return matchRepository.selectUserMatchesFromDatabase(user.getIdUser());
    }
}
