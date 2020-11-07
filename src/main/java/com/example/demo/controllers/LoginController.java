package com.example.demo.controllers;


import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    User userToDisplay = new User();

    @GetMapping("/")
    public String index()
    {
        return "loginPage";
    }

    @GetMapping("/loginpage")
    public String login(Model userModel)
    {
        userModel.addAttribute("userToDisplay", userToDisplay);
        return "loginPage";
    }

}
