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
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController
{

    UserService userService = new UserService();
    AdminService admin = new AdminService();
    List<User> allUsers;





    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {
        allUsers = userService.getAllUsers();

        //metoder kald her til user!


        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("userToDisplay", userService.userToDisplay());
        adminModel.addAttribute("admin", admin);
        adminModel.addAttribute("userService", userService);



        return "adminPage";
    }



    @PostMapping("/postAdmin")
    public String adminStart(WebRequest dataFromForm) throws FileNotFoundException {

        userService.setUserToDefault();

        try{
            String firstname = dataFromForm.getParameter("firstname");

                for(User u : allUsers){
                    if(firstname.equals(u.getFirstName()))
                    {
                        userService.userToDisplay().setFirstName(u.getFirstName());
                        userService.userToDisplay().setLastName(u.getLastName());
                        userService.userToDisplay().setEmail(u.getEmail());
                        userService.userToDisplay().setDateOfBirth(u.getDateOfBirth());
                        userService.userToDisplay().setBio(u.getBio());
                        userService.userToDisplay().setPhoto1(u.getPhoto1());

                    }
                }


        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
        }

        return "redirect:/admin";
    }


   @PostMapping("/adminDeleteUser")
    public String deleteUser(WebRequest dateFromForm){



        String delete = String.valueOf(dateFromForm.getParameter("deleteUser"));

        try{
            if(delete.equals("Slet bruger")) {
                for (User u : allUsers) {
                    if (userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())) {

                        System.out.println(u.getIdUser() + " deleted");
                        userService.deleteUser(u.getIdUser());
                        allUsers.remove(u);
                    }
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
        }

        userService.setUserToDefault();
        return "redirect:/admin";
    }

    @PostMapping("/adminBlacklistUser")
    public String blacklistUser(WebRequest dateFromForm)
    {

        String blacklist = String.valueOf(dateFromForm.getParameter("blacklistUser"));

        try{
            if(blacklist.equals("Blacklist bruger")){
                for(User u : allUsers){
                    if(userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())){
                        admin.addToBlacklist(u);
                        System.out.println(u.getIdUser() + " Blacklisted");
                    }
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
        }
        userService.setUserToDefault();
        return "redirect:/admin";
    }


    @PostMapping("/adminDeletePhoto")
    public String deletePhone(WebRequest dataFromForm){

        String deletePhoto = String.valueOf(dataFromForm.getParameter("deletePhoto"));

        if(deletePhoto.equals("Slet billede"))
        {
            try{
                for(User u : allUsers){
                    if(userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())){
                        userService.deletePhoto(u);

                        System.out.println(u);
                    }
                }
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }

        }
        userService.setUserToDefault();
        return "redirect:/admin";
    }



}
