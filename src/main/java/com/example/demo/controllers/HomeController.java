package com.example.demo.controllers;


import com.example.demo.models.Profil;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.ProfileService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController
{

    User userToDisplay = new User();




    //TODO Loginmapping bør ligge i LoginController - Home skal kun indeholde hvad vi viser i "Udforsk"
    //TODO "Udforsk" efter filnavnet er unødvendigt, det ser lidt rodet ud?

    //TODO indsæt metode, der tjekker om man er logget ind og hvis man ikke er, så skal redirecte til loginsiden







    }














