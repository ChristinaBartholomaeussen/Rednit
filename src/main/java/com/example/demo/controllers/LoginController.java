package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;


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
            String email = String.valueOf(dataFromForm.getParameter("email"));
            String password1 = String.valueOf(dataFromForm.getParameter("password1"));
            String password2 = String.valueOf(dataFromForm.getParameter("password2"));

            if(!password1.equals(password2))
            {
                System.out.println("Passwords not equal");
                return "redirect:/create";
            }

            String firstName = dataFromForm.getParameter("firstName");
            String lastName = dataFromForm.getParameter("lastName");
            int gender;
            if(dataFromForm.getParameter("gender").equals("male"))
            {
                gender = 1;
            }
            else
            {
                gender = 0;
            }



            Date age = new SimpleDateFormat("dd/MM/yyyy").parse(dataFromForm.getParameter("age"));
            String bio = dataFromForm.getParameter("bio");

            System.out.println(age);

//            User newUser = new User(email,password1,firstName,lastName,age,bio,age);

        }
        catch(Exception e)
        {
            System.out.println("fejl: " + e);
            return "redirect:/create";
        }

        return "redirect:/create";
    }

}
