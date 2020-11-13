package com.example.demo.controllers;

import com.example.demo.models.Match;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MatchService;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

	List<User> allUsersForExplore = userService.allUsers;
	int counterStraightWomen = 0;
	int counterStraightMen;
	int counterGayWomen;
	int counterGayMen;
	ArrayList<User> straightWomens = new ArrayList<User>();
	ArrayList<User> gayWomens = new ArrayList<User>();
	ArrayList<User> straightMens = new ArrayList<User>();
	ArrayList<User> gayMens = new ArrayList<User>();

	List<User> listOfPotentialCandidates = new ArrayList<>();
	int activeUserId;
	
	User user = new User("oscar.vinther@gmail.com", "password1234", "Oscar", "Otterstad", new Date(), 1, 0, "Det her er min bio :)! \nHvad sagde Jesus til taxachaufføren langfredag?");

	public UserController()
	{
		for(User user : allUsersForExplore)
		{
			if (user.getGender() == 0 && user.getSexualPreference() == 1) {
				
				straightWomens.add(user);
			}
			
			if (user.getGender() == 0 && user.getSexualPreference() == 0) {
				gayWomens.add(user);
			}
			
			if (user.getGender() == 1 && user.getSexualPreference() == 0) {
				straightMens.add(user);
			}

			if (user.getGender() == 1 && user.getSexualPreference() == 1) {
				gayMens.add(user);
			}
		}

		counterStraightMen = straightWomens.size() -1;
		counterStraightWomen = straightMens.size() -1;
		counterGayMen = gayMens.size() -1;
		counterGayWomen = gayWomens.size() -1;
	}

	@GetMapping("/matches")
	public String match(Model userModel, HttpServletRequest request)
	{
		allUsers = userRepository.selectAllUsersFromDatabase();
		Match matcha = null;
		ArrayList<Integer> matchId = new ArrayList<Integer>();
		ArrayList<Match> potentialMatch = new ArrayList<>();
		ArrayList<Match> potentialMatchActiveUser = new ArrayList<>();
		ArrayList<User> matchList = new ArrayList<>();
		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);
		MessageService messageService = new MessageService();

		for (Match match : matchService.getAllMatch()) {
			if (activeUser.getIdUser() != match.getIdUser() && match.getIdUserMatch() == activeUser.getIdUser() ) {
				potentialMatch.add(match);
				for (Match potential : matchService.getAllMatch()) {
					if (activeUser.getIdUser() == potential.getIdUser() && match.getIdUserMatch() == potential.getIdUserMatch() ) {
						matchList.add(userService.getUserByID(match.getIdUser()));
					}
				}
			}
			if (activeUser.getIdUser() == match.getIdUser() && match.getIdUserMatch() != activeUser.getIdUser() ) {
			}
		}

		userModel.addAttribute("allUsers", allUsers);
		userModel.addAttribute("selectedUser", selectedUser);
		userModel.addAttribute("listOfMessages", messageList);
		userModel.addAttribute("mathces", matchList);
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
    	
    	if (!data.getParameter("name").equals("")) {
    		
    		String updatedName = String.valueOf(data.getParameter("name"));
			user.setFirstName(updatedName);
		}
    	
    	if (!data.getParameter("bio").equals("")) {
    		String updatedBio = String.valueOf(data.getParameter("bio"));
    		user.setBio(updatedBio);
		}
    	
    	if (!data.getParameter("password").equals("") && !data.getParameter("password").equals(user.getPassword()) && data.getParameter("password").equals(data.getParameter("passwordTwo"))) {
    		String updatedPassword = data.getParameter("password");
    		user.setPassword(updatedPassword);
		}
    	
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
		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);
		User potentialUser = new User();
		model.addAttribute("user",potentialUser);

		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0)
		{
			for (User user : straightWomens)
			{
				listOfPotentialCandidates.add(user);

				if(counterStraightMen < 0)
					return "/explore/exploreNoMoreUsers";
			}
			potentialUser = listOfPotentialCandidates.get(counterStraightMen);
		}

		if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0)
		{
			for (User user : gayWomens)
			{
				listOfPotentialCandidates.add(user);
				if(counterGayWomen < 0)
					return "/explore/exploreNoMoreUsers";
			}

			potentialUser = listOfPotentialCandidates.get(counterGayWomen);
			model.addAttribute("user",potentialUser);
		}

		if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1)
		{
			for (User user : straightMens)
			{
				listOfPotentialCandidates.add(user);
					
				if(counterStraightWomen < 0)
					return "/explore/exploreNoMoreUsers";
			}

			potentialUser = listOfPotentialCandidates.get(counterStraightWomen);
			model.addAttribute("user",potentialUser);
		}

		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1)
		{
			for (User user : gayMens)
			{
				listOfPotentialCandidates.add(user);

				if(counterGayMen < 0)
					return "/explore/exploreNoMoreUsers";
			}

			potentialUser = listOfPotentialCandidates.get(counterGayMen);
			model.addAttribute("user",potentialUser);
		}

		return "/explore/explore";
    }

    @PostMapping("/postExploreLiked")
	public String postExploreLiked(WebRequest data, HttpServletRequest request)
	{ 
		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);

		User potentialUser = new User();

		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0)
		{
			potentialUser = listOfPotentialCandidates.get(counterStraightMen);
			matchService.insertPotentialMatch(activeUser.getIdUser(), potentialUser.getIdUser());
			listOfPotentialCandidates.remove(counterStraightMen);
			counterStraightMen--;
		}
		else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0)
		{
			counterGayWomen--;
			listOfPotentialCandidates.remove(counterGayWomen);
			potentialUser = listOfPotentialCandidates.get(counterGayWomen);
			matchService.insertPotentialMatch(activeUser.getIdUser(),potentialUser.getIdUser());
		}
		else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1)
		{
			potentialUser = listOfPotentialCandidates.get(counterStraightWomen);
			matchService.insertPotentialMatch(activeUser.getIdUser(), potentialUser.getIdUser());
			listOfPotentialCandidates.remove(counterStraightWomen);
			counterStraightWomen--;
		}
		else if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1)
		{
			counterGayMen--;
			listOfPotentialCandidates.remove(counterGayMen);
			potentialUser = listOfPotentialCandidates.get(counterGayMen);
			matchService.insertPotentialMatch(activeUser.getIdUser(),potentialUser.getIdUser());
		}

		activeUser.setIdUserMatch(potentialUser.getIdUser());

		return "redirect:/explore";
	}

	@PostMapping("/postExploreSkipped")
	public String postExploreSkipped(WebRequest data, HttpServletRequest request)
	{
		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);

		User potentialUser = new User();

		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0) {
			
			listOfPotentialCandidates.remove(counterStraightMen);
			counterStraightMen--;
			
		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0) {
			
			listOfPotentialCandidates.remove(counterGayWomen);
			counterGayWomen--;

		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1) {
			
			listOfPotentialCandidates.remove(counterStraightWomen);
			counterStraightWomen--;

		} else if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1) {
			
			listOfPotentialCandidates.remove(counterGayMen);
			counterGayMen--;
		}

		return "redirect:/explore";
	}
}
