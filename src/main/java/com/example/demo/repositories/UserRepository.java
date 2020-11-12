package com.example.demo.repositories;

import com.example.demo.models.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

public class UserRepository {

    ConnectionRepository connection = new ConnectionRepository();

    /**Inserts a newly created user into the database*/

    public void insertUserIntoDatabase(User user) {
        String insertUserSQL ="INSERT INTO users (email, password, firstName, lastName, dateOfBirth, bio, gender, sexualPreference) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertUserSQL);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(6, user.getBio());
            preparedStatement.setInt(7, user.getGender());
            preparedStatement.setInt(8, user.getSexualPreference());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Updates a single user in the database*/

    public void updateUserInfoInDatabase(User user) {
        String updateUserSQL = "UPDATE users SET email = ?, firstName = ?, lastName = ?, bio = ?, gender = ?, sexualPreference = ? WHERE idUser = ?";
        try {

            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(updateUserSQL);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getBio());
            preparedStatement.setInt(5, user.getGender());
            preparedStatement.setInt(6, user.getSexualPreference());
            preparedStatement.setInt(7, user.getIdUser());

            System.out.println(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     }

    /**Deletes a user in the database*/
    public void deleteUserFromDatabase(int idUser){
        String deleteUserSQL = "DELETE FROM users WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(deleteUserSQL);
            preparedStatement.setInt(1, idUser);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Selects a single user in the database from their idUSer and retuns an object of User*/

    public User selectUserFromDatabase(int idUser) {

        User userToReturn = new User();

        String selectUser = "SELECT * FROM users WHERE idUser = ?";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectUser);
            preparedStatement.setInt(1, idUser);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userToReturn = new User(
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userToReturn;
    }

    /**Selects a single user in the database from their email and returns an object of User*/

    public User selectUserFromDatabaseFromEmail(String email) {

        User userToReturn = new User();

        String selectUser = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectUser);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userToReturn = new User(
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userToReturn;
    }

    /**Selects all users in the database and returns them as an ArrayList*/

    public List<User> selectAllUsersFromDatabase() {

        String selectAllUsers = "SELECT * FROM users";

        List<User> allUsers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User tmpUser = new User(
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
                allUsers.add(tmpUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

    /**Selects all users emails and passwords in the databse and returns them as an ArrayList*/

    public List<User> selectAllUsersLoginInformationFromDatabase() {

        String selectAllUsers = "SELECT * FROM users";

        List<User> allUsersLoginInformation = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User tmpUser = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                allUsersLoginInformation.add(tmpUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsersLoginInformation;
    }

    /**Updates photo1 in the database*/

    public void updatePhotoInDatabase(User user) {
        String insertPhotoSQL = "UPDATE users SET photo1 = ? WHERE idUser = ?";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertPhotoSQL);
            preparedStatement.setString(1, user.getPhoto1());
            preparedStatement.setInt(2, user.getIdUser());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
