package com.example.demo.repositories;

import com.example.demo.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdminRepository {

    ConnectionRepository connection = new ConnectionRepository();

    //Implementation

    public void insertUserIntoBlacklistInDatabase(User user) {
        String insertUserIntoBlackList = "INSERT INTO blacklists (idUser) VALUES (?)";

        System.out.println(user);

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertUserIntoBlackList);
            preparedStatement.setInt(1, user.getIdUser());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Implementation

    public void deleteUserFromBlacklistInDatabase(int idUser) {
        String deleteUserFromBlackList = "DELETE FROM blacklists WHERE idUser = ?";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(deleteUserFromBlackList);
            preparedStatement.setInt(1, idUser);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getBlacklistFromDatabase(){

        return null;
    }

}
