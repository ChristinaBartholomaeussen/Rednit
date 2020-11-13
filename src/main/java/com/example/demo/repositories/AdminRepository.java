package com.example.demo.repositories;

import com.example.demo.models.Admin;
import com.example.demo.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    ConnectionRepository connection = new ConnectionRepository();

    Admin admin = new Admin();

    /**Inserts the users idUser attribute into the blacklist table, in the database */

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

    /**Deletes a users idUser attribute from the blacklist table, in the database*/

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

    /**Selects all blacklisted users in the database and returns it as an ArrayList*/

    public List<User> selectAllBlackListUsersFromDatabase() {
        String selectAllBlacklistedUsers = "SELECT * FROM users INNER JOIN blacklists ON users.idUser=blacklists.idUser";

        List<User> allBlacklistUsers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllBlacklistedUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User tmpBlacklistUser = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10)
                );
                allBlacklistUsers.add(tmpBlacklistUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allBlacklistUsers;
    }

    /**Selects all admins in the database and returns it as an ArrayList*/

    public List<Admin> selectAllAdminsFromDatabase() {

        String selectAdmin = "SELECT * FROM admins";

        List<Admin> allAdmins = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAdmin);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Admin tmpAdmin = new Admin(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
                allAdmins.add(tmpAdmin);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allAdmins;
    }
}
