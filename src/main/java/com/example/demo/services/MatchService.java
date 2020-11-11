package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.UserRepository;

import java.util.List;

public class MatchService {

    MatchRepository matchRepository = new MatchRepository();
    UserRepository userRepository = new UserRepository();



   /*public List<User> showAllMatches() {

        /List<User> matches = matchRepository.getAllMatchesFromDatabase();

        return matches;

   }

    public void addToMatches(User loggedInUser, User potentiallyMatch) {

        if(isAMatch(loggedInUser, potentiallyMatch)){

            //matchRepository.addToMatchTableInDatabase(loggedInUser, potentiallyMatch);

        }
    }

    public void deleteMatch(User match ) {

        //TODO
        //Skal muligvis have to parameter med sig, så den ved,
        //hvilke to lister matchet skal slettes fra

        String choice = null;

        if(choice.equalsIgnoreCase("yes")){
            //matchRepository.deleteMatchFromTableInDatabase(match);
        }
    }


    /*public boolean isAMatch(User loggedInUser, User potentiallyMatch) {

        List<User> loggedInUserList = userRepository.getLikedListFromDatabase(loggedInUser);
        List<User> potentiallyMatchList = userRepository.getLikedListFromDatabase(potentiallyMatch);


        if(loggedInUserList.contains(potentiallyMatch) && potentiallyMatchList.contains(loggedInUser)){

            System.out.println("Der er et match");
            return true;
        }
        else{
            return false;
        }

    }*/
}
