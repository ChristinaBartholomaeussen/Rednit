package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
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
    UserService userServiceToDisplay = new UserService();

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

    @GetMapping("/loginpage")
    public String login(Model userModel){

        userModel.addAttribute("userToDisplay", userToDisplay);
        userModel.addAttribute("userServiceToDisplay", userServiceToDisplay);

        return "loginPage";
    }


    @PostMapping("/postLoginpage")
    public String userLogin(WebRequest dataFromForm){

        try{
            UserService userService = new UserService();

            String useremail = dataFromForm.getParameter("email");
            String userpassword = dataFromForm.getParameter("password");

            if(userService.doesEmailMatchPassword(useremail, userpassword)){

                System.out.println("godkendt");
                return "redirect:/loginpage";
            }

        }catch(Exception e){
            System.out.println("fejl");
            return "redirect:/loginpage";
        }

        return "redirect:/create";

    }

}
