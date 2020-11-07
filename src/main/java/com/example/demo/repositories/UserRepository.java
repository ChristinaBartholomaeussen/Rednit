package com.example.demo.repositories;

import com.example.demo.models.User;

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

    public List<User> getAllUserFromDatabase(){

        List<User> allUsers = new ArrayList<User>();

        try{
            PreparedStatement ps = connection.establishConnection().prepareStatement("Select * FROM users");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                        //rs.getObject(8, List.class),
                        //rs.getObject(10, List.class),
                        //rs.getObject(11, List.class),
                        //rs.getObject(12, List.class)
                );

                allUsers.add(user);

            }

        }catch (SQLException e){
            return null;
        }

        return allUsers;
    }



}
