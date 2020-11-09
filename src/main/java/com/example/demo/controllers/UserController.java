package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
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
    	
    	/*  Mangler updateAge */
    	
		// Tjekker om bioen ikke er tom, og sætter en ny bio.
    	if (!data.getParameter("bio").equals("")) {
    		String updatedBio = String.valueOf(data.getParameter("bio"));
    		user.setBio(updatedBio);
		}
    	
    	
    	// Tjekker at password ikke er null
		// Tjekker om det nye passsword er det samme som det gamle
		// Tjekker om password er det samme som passwordTwo
    	if (!data.getParameter("password").equals("") && !data.getParameter("password").equals(user.getPassword()) && data.getParameter("password").equals(data.getParameter("passwordTwo"))) {
    		String updatedPassword = data.getParameter("password");
    		user.setPassword(updatedPassword);
		}
		
		

		// sender en POST request, og reloader siden igen. 
		return "redirect:/myProfile";
	}

	@PostMapping("/imageFile")
	public String imageFile(@RequestParam("imageFile") MultipartFile imageFile) {
    	
    	boolean flag = false; 
    	String fileFormatTest = "";
    	
    		// tester at der er et '.' som den fjerde sidste character.
			if (imageFile.getOriginalFilename().charAt(imageFile.getOriginalFilename().length()-4) == '.') {
				//Looper efter de tre sidste karaktere, og lægger dem i en string. 
				for (int i = imageFile.getOriginalFilename().length() -1; i > imageFile.getOriginalFilename().length()-4; i--) {
					fileFormatTest += imageFile.getOriginalFilename().charAt(i);
					
				}
			}

			//Tester om de tre karaktere som kommer fra loopet, er en reverse udgave af enten 'gif', 'png' eller 'jpg'
			if (fileFormatTest.equals("gpj") && fileFormatTest.equals("gnp") && fileFormatTest.equals("fig") ) {
				flag = true; // skifter til true, så billedet kan uploades.
			}
			if (flag) {
				try {
					user.saveImage(imageFile);
					System.out.println(User.class.getProtectionDomain().getCodeSource().getLocation().getPath());
					flag = false;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}
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
