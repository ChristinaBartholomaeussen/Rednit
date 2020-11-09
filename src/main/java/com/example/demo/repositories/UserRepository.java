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

public class UserRepository {

    ConnectionRepository connection = new ConnectionRepository();

    //Finished

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

    //Testing

    public void updateUserInfoInDatabase(User user) {
        String updateUserSQL = "UPDATE users SET (firstName, lastName, bio, gender, sexualPreference) VALUES (?, ?, ?, ?, ?) WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(updateUserSQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getBio());
            preparedStatement.setInt(4, user.getGender());
            preparedStatement.setInt(5, user.getSexualPreference());
            preparedStatement.setString(6, user.getEmail());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Implementation

    public void deleteUserFromDatabase(String email){
        String deleteUserSQL = "DELETE FROM users WHERE (email = ?)";
        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(deleteUserSQL);
            preparedStatement.setString(1, email);

            preparedStatement.execute();

            System.out.println("deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Failed test

    public User selectUserFromDatabase(String email){

        User userToReturn = new User();

        String selectUser = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectUser);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userToReturn = new User(
                resultSet.getString(1),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getDate(5),
                resultSet.getString(6),
                resultSet.getInt(7),
                resultSet.getInt(8)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userToReturn;
    }

    //Testing

    public List<User> selectAllUsersFromDatabase() {

        String selectAllUsers = "SELECT * FROM users";

        List<User> allUsers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectAllUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User tmpUser = new User(
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)
                );
                allUsers.add(tmpUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

    public void selectPhotoFromDatabase() {

    }

    public void addPhotoToDatabase() {

    }

    public void deletePhotoFromDatabase() {

    }

    public void addToLikedTableInDatabase(){

    }

    public void addToDislikedTableInDatabase(){

    }

    public List<User> getLikedListFromDatabase(User user){
        List<User> likedList = new ArrayList<User>();

        //TODO
        //der skal på en måde findes begges brugeres lister

        return likedList;


    }

    public User getSingleUserFromDatabase(){
        //TODO
        return null;
    }

    public List<User> getAllUserFromDatabase() throws FileNotFoundException {

        List<User> allUsers = new ArrayList<User>();

        File file = new File("src/main/resources/photos"); //Path til vores gemte billeder


        try{
            PreparedStatement ps = connection.establishConnection().prepareStatement("Select * FROM users");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                User user = new User(

                );

                allUsers.add(user);

            }

        }catch (SQLException e){
            return null;
        }

        return allUsers;
    }
}
