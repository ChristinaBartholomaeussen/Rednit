package com.example.demo.services;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;

import java.util.ArrayList;
import java.util.List;


public class AdminService {

    private final List<User> allUsers;
    private final List<User> blacklistedUser;


    AdminRepository adminRepository = new AdminRepository();

    public AdminService()
    {
        this.blacklistedUser = new ArrayList<>();
        this.allUsers = new ArrayList<>();
    }

    public void addToBlacklist(int id)
    {
        adminRepository.addUserToBlacklistToDatabase(id);
    }

    public List<User> restoreUser(User user)
    {
        blacklistedUser.remove(user);
        return blacklistedUser;
    }

    public List<User> getBlacklist()
    {
        //TODO Get List from Database
        return null;
    }


    public String getSingleUser(String firstname){

        return "ok";
    }



}
