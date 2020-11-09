package com.example.demo.controllers;

import com.example.demo.models.User;
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
    List<User> allUsers = new ArrayList<>();
    User userToDisplay = new User();

    UserService userService = new UserService();

    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {

        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("userToDisplay", userToDisplay);
        adminModel.addAttribute("adminModel", adminModel);

        //Dummy code - 45 nye Users
        for(int i = 0; i < 45; i++)
        {
            Date date = new Date(i);
            allUsers.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i,date,i,i,"bio"+i));
        }

        //User user = new User();


        return "adminPage";
    }

    @PostMapping("/postAdmin")
    public String adminPageDelete(WebRequest dataFromForm, Model userModel) throws FileNotFoundException {
        String firstname = String.valueOf(dataFromForm.getParameter("firstname"));

        userModel.addAttribute("userToDisplay", userToDisplay);

        for(User u : allUsers){
            if(firstname.equals(u.getFirstName())){

                userToDisplay.setFirstName(u.getFirstName());
                userToDisplay.setLastName(u.getLastName());
                userToDisplay.setEmail(u.getEmail());
            }
        }

        return "redirect:/admin";
    }



}
