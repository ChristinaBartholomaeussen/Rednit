package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController
{
    List<User> allUsers = new ArrayList<>();

    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {
        //Dummy code - 45 nye Users
        for(int i = 0; i < 45; i++)
        {
            Date date = new Date(i);
            allUsers.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i,date,i,i,"bio"+i));
        }

        User user = new User();

        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("user",user);
        adminModel.addAttribute("adminModel", adminModel);
        return "adminPage";
    }

    @PostMapping("/admin")
    public String adminPageDelete(@ModelAttribute User user, Model model)
    {
        model.addAttribute("user", "user");
        return "adminPage";
    }
}
