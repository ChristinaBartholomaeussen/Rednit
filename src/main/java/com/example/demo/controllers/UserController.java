package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

	List<User> allUsers = new ArrayList<>();
	User selectedUser = new User();

	ArrayList<String> messageList = new ArrayList();
	String message = "";

    @GetMapping("/matches")
    public String match(Model userModel)
	{
		for(int i = 0; i < 45; i++)
		{
			Date date = new Date(i);
			allUsers.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i, date,i,i,"bio"+i));
		}

		userModel.addAttribute("allUsers", allUsers);
		userModel.addAttribute("selectedUser", selectedUser);

		userModel.addAttribute("listOfMessages", messageList);

		return "matches";
    }

    @PostMapping("/postMatches")
	public String matchSelect(WebRequest dataFromForm, Model userModel)
	{
		String firstName = String.valueOf(dataFromForm.getParameter("submitBtn"));

		userModel.addAttribute("selectedUser", selectedUser);

		for(User u : allUsers)
		{
			if(firstName.equals(u.getFirstName()))
			{
				selectedUser = u;
			}
		}
		
		
		return "redirect:/matches";
	}

	
	
	@RequestMapping(value="/sendMessage", method={RequestMethod.GET, RequestMethod.POST})
	public String sendMessage(WebRequest data, Model model) {
    	messageList.add(data.getParameter("send"));
		
    	return "redirect:/matches";	
	}
	
	User user = new User("oscar.vinther@gmail.com", "password1234", "Oscar", "Otterstad", new Date(), 1, 1, "Det her er min bio :)! \nHvad sagde Jesus til taxachaufføren langfredag?");

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
	public String imageFile(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {

		
    	Cookie cookieId = UserService.getCookieId(request);
    	
				try {
					UserService.saveImage(imageFile, cookieId);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
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
