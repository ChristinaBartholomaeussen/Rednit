package com.example.demo.services;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;

import java.util.ArrayList;
import java.util.List;


public class AdminService {

    private List<User> allUsers;
    private List<User> blacklistedUser;
    private AdminRepository adminRepository;

    public AdminService()
    {
        this.allUsers = adminRepository.getAllUsersFromDatabase();
        this.blacklistedUser = new ArrayList<>();
        this.adminRepository = new AdminRepository();
    }

    public List<User> addToBlacklist(User user)
    {
        blacklistedUser.add(user);
        return blacklistedUser;
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

    public void deleteUser(User user)
    {

    }

    public User getSingleUser(){
        //TODO
        return null;
    }

}
