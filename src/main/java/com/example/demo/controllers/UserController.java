package com.example.demo.controllers;

import com.example.demo.models.Match;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MatchService;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.AsyncContext;
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

	List<User> allUsersForExplore = userService.allUsers;
	int counterStraightWomnes = 0;
	int counterStraightMens;
	int counterGayWomens;
	int counterGayMens;
	List<User> listOfPotentialCandidates = new ArrayList<>();
	int activeUserId;
	
	int cookieId;
	User activeUser;
	
	int counter;

	User user = new User("oscar.vinther@gmail.com", "password1234", "Oscar", "Otterstad", new Date(), 1, 0, "Det her er min bio :)! \nHvad sagde Jesus til taxachaufføren langfredag?");

	ArrayList<User> straightWomens = new ArrayList<User>();
	ArrayList<User> gayWomens = new ArrayList<User>();
	ArrayList<User> straightMens = new ArrayList<User>();
	ArrayList<User> gayMens = new ArrayList<User>();
	
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
			
			
			/*
			if(user.getIdUser() != activeUser.getIdUser() && user.getSexualPreference() != activeUser.getSexualPreference())
			{
				System.out.println(activeUser.getFirstName());
				listOfPotentialCandidates.add(user);
			}*/ 
		}

	
		
		counterStraightMens = straightWomens.size() -1;
		counterStraightWomnes = straightMens.size() -1;
		counterGayMens = gayMens.size() -1;
		counterGayWomens = gayWomens.size() -1;

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

		//System.out.println("Active id : " + activeUser.getIdUser() + " active matchId: " + activeUser.getIdUserMatch());

		MessageService messageService = new MessageService();

		for (Match match : matchService.getAllMatch()) {

			//System.out.println("matchID: " + match.getIdUser() + " matchUserID: " + match.getIdUserMatch());
			//System.out.println(activeUser.getIdUser());
			if (activeUser.getIdUser() != match.getIdUser() && match.getIdUserMatch() == activeUser.getIdUser() ) {
				//System.out.println("activeID: " + activeUser.getIdUser() + " matchUderID: " + match.getIdUser());

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

		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);

		User potentialUser = null;
		
		

			if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0) {
		
				for (User user : straightWomens) {
					listOfPotentialCandidates.add(user);
					
					if(counterStraightMens < 0)
						return "/explore/exploreNoMoreUsers";
					
				}
				potentialUser = listOfPotentialCandidates.get(counterStraightMens);
				model.addAttribute("user",potentialUser);
			}
			if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0) {
				for (User user : gayWomens) {
					listOfPotentialCandidates.add(user);
					if(counterGayWomens < 0)
						return "/explore/exploreNoMoreUsers";
					
				}

				potentialUser = listOfPotentialCandidates.get(counterGayWomens);
				model.addAttribute("user",potentialUser);
			}
			if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1) {
				for (User user : straightMens) {
					listOfPotentialCandidates.add(user);
					
					if(counterStraightWomnes < 0)
						return "/explore/exploreNoMoreUsers";

				}

				potentialUser = listOfPotentialCandidates.get(counterStraightWomnes);
				model.addAttribute("user",potentialUser);
			}
			if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1) {
				for (User user : gayMens) {
					listOfPotentialCandidates.add(user);

					if(counterGayMens < 0)
						return "/explore/exploreNoMoreUsers";
					
				}
				potentialUser = listOfPotentialCandidates.get(counterGayMens);
				model.addAttribute("user",potentialUser);
			}

			

		return "/explore/explore";
    }

    @PostMapping("/postExploreLiked")
	public String postExploreLiked(WebRequest data, HttpServletRequest request)
	{ 
		
		

		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);

		User potentialUser = null;
		
		
		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0) {

			potentialUser = listOfPotentialCandidates.get(counterStraightMens);
			matchService.insertPotentialMatch(activeUser.getIdUser(), potentialUser.getIdUser());
			System.out.println("Active user: " + activeUser.getIdUser());
			System.out.println("User I liked: " + potentialUser.getIdUser());
			listOfPotentialCandidates.remove(counterStraightMens);
			counterStraightMens--;
			
			
		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0) {
			counterGayWomens--;
			listOfPotentialCandidates.remove(counterGayWomens);
			potentialUser = listOfPotentialCandidates.get(counterGayWomens);
			matchService.insertPotentialMatch(activeUser.getIdUser(),potentialUser.getIdUser());
		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1) {
			
			potentialUser = listOfPotentialCandidates.get(counterStraightWomnes);
			matchService.insertPotentialMatch(activeUser.getIdUser(), potentialUser.getIdUser());
			System.out.println("Active user: " + activeUser.getIdUser());
			System.out.println("User I liked: " + potentialUser.getIdUser());
			listOfPotentialCandidates.remove(counterStraightWomnes);
			counterStraightWomnes--;
			
		} else if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1) {
			counterGayMens--;
			listOfPotentialCandidates.remove(counterGayMens);
			potentialUser = listOfPotentialCandidates.get(counterGayMens);
			matchService.insertPotentialMatch(activeUser.getIdUser(),potentialUser.getIdUser());
		}

		System.out.println("Gender: " + activeUser.getGender() + " Sexualpref: " +  activeUser.getSexualPreference());
		activeUser.setIdUserMatch(potentialUser.getIdUser());
		ArrayList<Match> test = matchService.getAllMatches(activeUser);
		System.out.println(test);
		
		return "redirect:/explore";
	}

	@PostMapping("/postExploreSkipped")
	public String postExploreSkipped(WebRequest data, HttpServletRequest request)
	{
		/*
		allUsersForExplore.remove(counter);
		counter--; 
		return "redirect:/explore"; */





		int cookieId = UserService.getCookieId(request);
		User activeUser = userRepository.selectUserFromDatabase(cookieId);

		User potentialUser = null;


		if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 0) {
			
			listOfPotentialCandidates.remove(counterStraightMens);
			counterStraightMens--;
			
		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 0) {
			
			listOfPotentialCandidates.remove(counterGayWomens);
			counterGayWomens--;

		} else if (activeUser.getGender() == 0 && activeUser.getSexualPreference() == 1) {
			
			listOfPotentialCandidates.remove(counterStraightWomnes);
			counterStraightWomnes--;

		} else if (activeUser.getGender() == 1 && activeUser.getSexualPreference() == 1) {
			
			listOfPotentialCandidates.remove(counterGayMens);
			counterGayMens--;

		}

		return "redirect:/explore";
	}

}
