package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.UserService;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController
{

    UserService userService = new UserService();
    User userToDisplay = new User();
    AdminService admin = new AdminService();

    List<User> allUsers = userService.getAllUsers();


    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {


        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("userToDisplay", userToDisplay);
        adminModel.addAttribute("admin", admin);
        adminModel.addAttribute("userService", userService);


        return "adminPage";
    }



    @PostMapping("/postAdmin")
    public String adminPageDelete(WebRequest dataFromForm) throws FileNotFoundException {

        String firstname = dataFromForm.getParameter("firstname");

        /*for(int i = 0; i < 45; i++)
        {
            Date date = new Date(i);
            allUsers.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i, date,i,i,"bio"+i));
        }*/

        for(User u : allUsers){
            if(firstname.equals(u.getFirstName()))
            {
                userToDisplay.setFirstName(u.getFirstName());
                userToDisplay.setLastName(u.getLastName());
                userToDisplay.setEmail(u.getEmail());
                userToDisplay.setDateOfBirth(u.getDateOfBirth());
                userToDisplay.setBio(u.getBio());
            }
        }
        return "redirect:/admin";
    }


    @PostMapping("/adminDeleteUser")
    public String deleteUser(WebRequest dateFromForm){

        String delete = String.valueOf(dateFromForm.getParameter("deleteUser"));
        String blakclist = dateFromForm.getParameter("blacklistUser");
        //String deletePhoto = dateFromForm.getParameter("deletePhoto");

        if(delete.equals("Slet bruger")){
            for(User u : allUsers){
                if(userToDisplay.getFirstName().equals(u.getFirstName()) && userToDisplay.getEmail().equals(u.getEmail())){

                    System.out.println(u.getIdUser() + " deleted");
                    userService.deleteUser(u.getIdUser());

                }
            }
        }
        if(blakclist.equals("Blacklist bruger")){
            for(User u : allUsers){
                if(userToDisplay.getFirstName().equals(u.getFirstName()) && userToDisplay.getEmail().equals(u.getEmail())){
                    admin.addToBlacklist(u.getIdUser());
                    System.out.println("Blacklisted");
                }
            }
        }
/*
        if(deletePhoto.equals("Slet billede"))
        {
            for(User u : allUsers){
                if(userToDisplay.getFirstName().equals(u.getFirstName()) && userToDisplay.getEmail().equals(u.getEmail())){
                    userService.deletePhoto(u);
                    System.out.println(u);
                }
            }
        }*/


//TODO lav redirect
        return "redirect:/admin";
    }





}
