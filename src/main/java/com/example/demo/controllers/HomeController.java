package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{

    User userToDisplay = new User();

@GetMapping("/home")
    public String home(){
    return "udforsk";
}


    //TODO Loginmapping bør ligge i LoginController - Home skal kun indeholde hvad vi viser i "Udforsk"
    //TODO "Udforsk" efter filnavnet er unødvendigt, det ser lidt rodet ud?

    //TODO indsæt metode, der tjekker om man er logget ind og hvis man ikke er, så skal redirecte til loginsiden







    }














