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

    /**
     * Sender et User objekt til adminRepository
     * @param user
     */
    public void addToBlacklist(User user)
    {
        adminRepository.insertUserIntoBlacklistInDatabase(user);
    }

    /**
     * sender et Integer value til Admin Repository
     * @param id
     */
    public void restoreUser(int id)
    {
        adminRepository.deleteUserFromBlacklistInDatabase(id);
    }

    /**
     * Henter en liste af User objekter fra Admin Repository og returnerer en Liste af User Objekter.
     * @return
     */
    public List<User> getBlacklist()
    {
        blacklistedUser = adminRepository.selectAllBlackListUsersFromDatabase();

        return blacklistedUser;
    }

    /**
     * Henter en liste af User objekter i Admin Repository og returnerer en liste af User objekter
     * @return
     */
    public List<User> getBlacklistedUsers() {
        return adminRepository.selectAllBlackListUsersFromDatabase();
    }

    /**
     * Henter en liste af Admin-objekter fra Admin Repository og returnerer en Liste af Admin-objekter
     * @return
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.selectAllAdminsFromDatabase();
    }
}
