package com.example.demo.controllers;


import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    User userToDisplay = new User();

    @GetMapping("/create")
    public String index(Model userModel)
    {
        userModel.addAttribute("userModel", userModel);
        return "createNewProfile";
    }

    @PostMapping("/postCreate")
    public String login(WebRequest dataFromForm)
    {
        try
        {
            var input = dataFromForm.getParameterNames();
            System.out.println(input);
        }
        catch(Exception e)
        {
            System.out.println("fejl: " + e);
            return "redirect:/createNewProfile";
        }

        return "redirect:/createNewProfile";
    }

}
