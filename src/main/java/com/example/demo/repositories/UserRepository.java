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

    //Done

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

    //Implementation



    //Implementation

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

    //Implementation

    public void deleteUserFromDatabase(int idUser){
        String deleteUserSQL = "DELETE FROM users WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(deleteUserSQL);
            preparedStatement.setInt(1, idUser);

            preparedStatement.execute();

            System.out.println("deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Implementation

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

    //Done

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

    //Implementation

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

    public void selectUserLikedListFromDatabase() {

    }

    public void insertUserIntoLikedListInDatabase() {

    }

    public void deleteUserFromLikedListInDatabase() {

    }

    public void insertUserIntoDislikedInDatabase(){

    }

    public List<User> getLikedListFromDatabase(User user){
        List<User> likedList = new ArrayList<User>();

        //TODO
        //der skal på en måde findes begges brugeres lister

        return likedList;
    }

}
