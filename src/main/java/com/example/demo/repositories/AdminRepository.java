package com.example.demo.repositories;

import com.example.demo.models.User;

import java.util.List;

public class AdminRepository {

    ConnectionRepository connection = new ConnectionRepository();

    public void addUserToBlacklistToDatabase(int id)
    {
        //TODO
    }

    public void deleteUserFromBlacklistFromDatabase(User user){

    }


    public void deleteUserPhotoFromDatabase(User user, int indexOfPhoto){

    }

    public List<User> getBlacklistFromDatabase(){

        return null;
    }

    public User getSingleUserFromDatabase(){

        //TODO
        return null;
    }



}
