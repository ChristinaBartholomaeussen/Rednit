package com.example.demo.controllers;


import com.example.demo.services.MyProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/createProfile")
    public String createProfile(){
        return "createProfile";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/match")
    public String match(){
        return "match";
    }

    @GetMapping("/myProfile")
    public String myProfil(){
        return "myProfile";
    }


}




