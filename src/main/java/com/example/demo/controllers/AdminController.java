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

    //TODO CB indsæt metode til at håndtere bruger + adminPage



}
