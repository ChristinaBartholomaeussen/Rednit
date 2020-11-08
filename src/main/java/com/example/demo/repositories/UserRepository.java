package com.example.demo.repositories;

import com.example.demo.models.User;

import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        FileOutputStream photoOutout = new FileOutputStream(file);

        byte b[];
        Blob blob;


        try{
            PreparedStatement ps = connection.establishConnection().prepareStatement("Select * FROM users");

            ResultSet rs = ps.executeQuery();
            InputStream binaryStream;

            while(rs.next()){

                User user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),

                        binaryStream = rs.getBinaryStream(9),
                        rs.getBinaryStream(10),
                        rs.getBinaryStream(11)

                );

                allUsers.add(user);

            }

        }catch (SQLException | IOException e){
            return null;
        }

        return allUsers;
    }



}
