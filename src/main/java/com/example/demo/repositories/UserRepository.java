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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class UserRepository {

    ConnectionRepository connection = new ConnectionRepository();

    public void insertUserIntoDatabase(User user){

    }

    public void updateUserInfoInDatabase(User user){

    }

    public void deleteUserFromDatabase(User user){

    }

    public User selectUserFromDatabase(String input){
        return null;
    }

    public void selectAllUsersFromDatabase(){

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
