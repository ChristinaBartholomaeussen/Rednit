package com.example.demo.repositories;

import com.example.demo.models.User;

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



}
