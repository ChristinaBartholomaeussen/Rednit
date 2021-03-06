package com.example.demo.repositories;

import com.example.demo.models.Match;
import com.example.demo.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    ConnectionRepository connection = new ConnectionRepository();

    /**
     * Deletes a match between two users in the database
     * @param idUser
     * @param idUserMatch
     * */

    public void deleteMatchFromMatchlistInDatabase(int idUser, int idUserMatch) {
        String deleteUserMatch = "DELETE FROM matchlists WHERE idUser = ? AND idUserMatch = ?";

        try{
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(deleteUserMatch);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idUserMatch);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a match after a user has 'liked' another user
     * @param idUser
     * @param idUserMatch
     * */

    public void InsertMatchIntoMatchListInDatabase(int idUser, int idUserMatch) {
        String insertUserMatch = "INSERT INTO matchlists (idUser, idUserMatch) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertUserMatch);
            preparedStatement.setInt(1,idUser);
            preparedStatement.setInt(2, idUserMatch);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Selects a single users matches and returns it as an ArrayList
     * @param idUser
     * */

    public List<User> selectUserMatchesFromDatabase(int idUser) {
        String selectUserMatches = "SELECT * FROM matchlists INNER JOIN users ON matchlists.idUser=users.idUser WHERE users.idUser = ?  OR matchlists.idUserMatch = ?";

        List<User> userMatches = new ArrayList<User>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectUserMatches);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idUser);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User tmpUsers = new User(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(4),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDate(8),
                        resultSet.getString(9),
                        resultSet.getInt(10),
                        resultSet.getInt(11),
                        resultSet.getString(12)
                );
                userMatches.add(tmpUsers);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userMatches;
    }

    /**
     * Selects all matches in the database and returns them as an ArrayList
     * */

    public List<Match> selectAllMatchesFromDatabase() {
        String selectAllMatches = "SELECT * FROM matchlists";

        List<Match> allMatches = new ArrayList<Match>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllMatches);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Match tmpMatches = new Match(
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );
                allMatches.add(tmpMatches);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allMatches;
    }
}
