package com.example.demo.services;
import com.example.demo.models.Admin;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;

import java.util.ArrayList;
import java.util.List;


public class AdminService {

    private List<User> allUsers;
    public List<User> blacklistedUser;

    AdminRepository adminRepository = new AdminRepository();

    public AdminService()
    {
        this.blacklistedUser = new ArrayList<>();
        this.allUsers = new ArrayList<>();
    }

    public void addToBlacklist(User user)
    {
        adminRepository.insertUserIntoBlacklistInDatabase(user);
    }

    public void restoreUser(int id)
    {
        adminRepository.deleteUserFromBlacklistInDatabase(id);
    }

    public List<User> getBlacklist()
    {
        blacklistedUser = adminRepository.selectAllBlackListUsersFromDatabase();

        return blacklistedUser;
    }

    public List<User> getBlacklistedUsers() {
        return adminRepository.selectAllBlackListUsersFromDatabase();
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.selectAllAdminsFromDatabase();
    }
}
