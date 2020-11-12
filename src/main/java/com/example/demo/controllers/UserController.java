package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MatchService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class UserController {

	MatchService matchService = new MatchService();
	UserService userService = new UserService();
	List<User> allUsers = new ArrayList<>();
	User selectedUser = new User();

	ArrayList<String> messageList = new ArrayList();
	String message = "";

	UserRepository userRepository = new UserRepository();

	List<User> allUsersForExplore = userService.getAllUsers();
	int counter;
	List<User> listOfPotentialCandidates = new ArrayList<>();
	int activeUserId;

	User user = new User("oscar.vinther@gmail.com", "password1234", "Oscar", "Otterstad", new Date(), 1, 0, "Det her er min bio :)! \nHvad sagde Jesus til taxachaufføren langfredag?");

	public UserController()
	{
		for(User user : allUsersForExplore)
		{
			if(user.getIdUser() != allUsersForExplore.get(0).getIdUser() && user.getSexualPreference() != allUsersForExplore.get(0).getSexualPreference())
			{
				listOfPotentialCandidates.add(user);
			}
		}
		counter = listOfPotentialCandidates.size() - 1;

	}

    @GetMapping("/matches")
    public String match(Model userModel)
	{
		allUsers = userRepository.selectAllUsersFromDatabase();

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
	
    @GetMapping("/myProfile")
    public String myProfile(Model model, HttpServletRequest request){
    	
    	UserRepository userRepository = new UserRepository();
    	
    	int cookieId = UserService.getCookieId(request);
    	User user = userRepository.selectUserFromDatabase(cookieId);
    	
    	
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

		
    	int cookieId = UserService.getCookieId(request);
    	
				try {
					UserService.saveImage(imageFile, cookieId);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}

		return "redirect:/myProfile";
	}

    @GetMapping("/explore")
    public String explore(Model model, HttpServletRequest request)
    {
    	if(counter < 0)
			return "/explore/exploreNoMoreUsers";

		User potentialUser = listOfPotentialCandidates.get(counter);

		model.addAttribute("user",potentialUser);
		model.addAttribute("allUsers", listOfPotentialCandidates);
		return "/explore/explore";
    }

    @PostMapping("/postExploreLiked")
	public String postExploreLiked(WebRequest data, HttpServletRequest request)
	{
		matchService.insertPotentialMatch(allUsersForExplore.get(0),allUsersForExplore.get(counter));
		allUsersForExplore.remove(counter);
		counter--;
		return "redirect:/explore";
	}

	@PostMapping("/postExploreSkipped")
	public String postExploreSkipped(WebRequest data)
	{
		allUsersForExplore.remove(counter);
		counter--;
		return "redirect:/explore";
	}

}
