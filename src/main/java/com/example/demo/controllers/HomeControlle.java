package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.services.ProfileService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeControlle {

    User userToDisplay = new User();

    //TODO Loginmapping bør ligge i LoginController - Home skal kun indeholde hvad vi viser i "Udforsk"
    //TODO "Udforsk" efter filnavnet er unødvendigt, det ser lidt rodet ud?
//    @GetMapping("/loginpage")
//    public String login(Model userModel){
//
//        userModel.addAttribute("userToDisplay", userToDisplay);
//        return "loginPage";
//    }

    @PostMapping("/postLoginpage")
    public boolean userLogin(WebRequest dataFromForm){

        ProfileService profileService = new ProfileService();

        String useremail = dataFromForm.getParameter("email");
        String userpassword = dataFromForm.getParameter("password");

        if(profileService.doesEmailMatchPassword(useremail, userpassword)){
            return true;
        }
        else {
            return false;
        }

    }






    }














