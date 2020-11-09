package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/myMatches")
    public String match(){
        return "myMatches";
    }

    @GetMapping("/myProfile")
    public String myProfil(){
        return "myProfile";
    }

    @GetMapping("/explore")
    public String explore(Model model)
    {
        Date date = new Date();
        User user = new User("email","password","Rune","Petersen",date,1,0,"hej med dig");

        model.addAttribute("user", user);
        return "explore";
    }

}
