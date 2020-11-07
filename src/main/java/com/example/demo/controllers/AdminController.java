package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController
{
    @GetMapping("/admin")
    public String index(Model adminModel)
    {
        List<User> users = new ArrayList<>();
        Date date = new Date();

        for(int i = 0; i < 45; i++)
        {
            users.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i,date,i,i,"bio"+i));
        }

        adminModel.addAttribute("users", users);
        adminModel.addAttribute("adminModel", adminModel);


        return "adminPage";
    }

    @PostMapping("/postAdmin")
    public String index(WebRequest webRequest)
    {

        return "adminPage";
    }
}
