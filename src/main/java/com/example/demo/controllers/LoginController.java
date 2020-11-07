package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.repositories.UserRepository;
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
                gender = 1;
            else
                gender = 0;

            int sexualPreference;
            if(dataFromForm.getParameter("sexualPreference").equals("female"))
                sexualPreference = 0;
            else if(dataFromForm.getParameter("sexualPreference").equals("male"))
                sexualPreference = 1;
            else
                sexualPreference = 3;

            Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dataFromForm.getParameter("dateOfBirth"));
            String bio = dataFromForm.getParameter("bio");

            //TODO RBP: Opret funktionalitet som kan sætte denne bruger ind på Databasen
            User newUser = new User(email,password1,firstName,lastName,dateOfBirth,gender,sexualPreference,bio);
            UserRepository userRepository = new UserRepository();
            userRepository.insertUserIntoDatabase(newUser);


        }
        catch(Exception e)
        {
            System.out.println("fejl: " + e);
            return "redirect:/create";
        }

        return "redirect:/loginpage";
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
