package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    User userToDisplay = new User();

    @GetMapping("/")
    public String firstPage() {
        return "loginPage";
    }

    @GetMapping("/home")
    public String home(){
        return "explore";
    }

}














