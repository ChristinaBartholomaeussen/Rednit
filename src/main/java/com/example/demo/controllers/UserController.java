package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

	

	User user = new User("oscar.vinther@gmail.com", "password1234", "Oscar", "Otterstad", new Date(), 1, 1, "Det her er min bio :)! \nHvad sagde Jesus til taxachaufføren langfredag?");

    @GetMapping("/myMatches")
    public String match(){
        return "myMatches";
    }

    @GetMapping("/myProfile")
    public String myProfile(Model model){
    	
    	model.addAttribute("user", user);
    	
        return "myProfile";
    }
    
    @PostMapping("/myProfilePost") 
	public String myProfilePost(WebRequest data) {
    	
    	//ToDo Oscar:
		// Updatere Date value? 
    	
    	if (!data.getParameter("name").equals("")) {
    		
    		String updatedName = String.valueOf(data.getParameter("name"));
			user.setFirstName(updatedName);
		}
    	
    	if (!data.getParameter("bio").equals("")) {
    		String updatedBio = String.valueOf(data.getParameter("bio"));
    		user.setBio(updatedBio);
		}
    	
    	
		
    	
    	
		return "redirect:/myProfile";
	}

    @GetMapping("/explore")
    public String explore(Model model)
    {
        Date date = new Date();
        User user = new User("email","password","Rune","Petersen",date,1,0,"hej med dig");

        model.addAttribute("user", user);
        return "explore";
    }

}
