package com.example.demo.repositories;

import com.example.demo.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    ConnectionRepository connection = new ConnectionRepository();

    //Implementation

    public void insertUserIntoBlacklistInDatabase(User user) {
        String insertUserIntoBlackList = "INSERT INTO blacklists (idUser) VALUES (?)";

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

    //Implementation

    public List<User> selectAllBlackListUsersFromDatabase() {
        String selectAllBlacklistedUsers = "SELECT * FROM users INNER JOIN blacklists ON users.idUser=blacklists.idUser";

        List<User> allBlackListUsers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllBlacklistedUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User tmpBlacklistUser = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10)
                );
                allBlackListUsers.add(tmpBlacklistUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allBlackListUsers;
    }

}
