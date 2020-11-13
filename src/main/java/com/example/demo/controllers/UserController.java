package com.example.demo.controllers;

import com.example.demo.models.Match;
import com.example.demo.models.User;
import com.example.demo.services.MatchService;
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
	User selectedUser = new User();
	User activeUser;
	List<String> messageList = new ArrayList();
	List<User> allUsers = new ArrayList<>();
	List<User> allUsersForExplore = userService.allUsers;
	List<User> listOfPotentialCandidates = new ArrayList<>();
	List<User> straightWomens = new ArrayList<User>();
	List<User> gayWomens = new ArrayList<User>();
	List<User> straightMens = new ArrayList<User>();
	List<User> gayMens = new ArrayList<User>();
	List<User> matchList = new ArrayList<>();
	int cookieId;
	int counterStraightWomen = 0;
	int counterStraightMen;
	int counterGayWomen;
	int counterGayMen;

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

	/**
	 * Methoden tager to parameter ind. Model og HttpServeltRequest.
	 * Model bruges til at sende bruger data til thymeleaf, som genere de rigtige html elementer.
	 * HttpServeltRequest bruges til at hente en cookie ned med.
	 * Inde i methoden, bliver er iteraret over alle de bruger der har liket nogen. 
	 * Og derefter sortes i en ny liste, som inden holder alle dem som den aktive bruger har liket og alle dem som har liket den aktive bruger.
	 * 
	 * @param userModel
	 * @param request
	 * @return 
	 * 
	 */
	
	@GetMapping("/matches")
	public String match(Model userModel, HttpServletRequest request)
	{
		ArrayList<Match> potentialMatch = new ArrayList<>();

		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

		for (Match match : matchService.getAllMatch()) {
			
			if (activeUser.getIdUser() != match.getIdUser() && match.getIdUserMatch() == activeUser.getIdUser() ) {

				potentialMatch.add(match);
				for (Match potential : matchService.getAllMatch()) {

					if (activeUser.getIdUser() == potential.getIdUser() && match.getIdUserMatch() == potential.getIdUserMatch() ) {
						matchList.add(userService.getUserByID(match.getIdUser()));
					}
				}
			}
		}
		
		userModel.addAttribute("selectedUser", selectedUser);
		userModel.addAttribute("listOfMessages", messageList);
		userModel.addAttribute("mathces", matchList);
		return "matches";
	}

	/**
	 * Den tager to parameter ind. WebRequest og Model.
	 * WebRequest henter data som er indsendt i html form. 
	 * Model sender data fra formen til thymeleaf 
	 * Der er et loop der løber over alle mathces, og viser den valgte bruger.
	 * @param dataFromForm
	 * @param userModel
	 * @return
	 */

    @PostMapping("/postMatches")
	public String matchSelect(WebRequest dataFromForm, Model userModel)
	{
		String firstName = String.valueOf(dataFromForm.getParameter("submitBtn"));

		for(User u : matchList)
		{
			if(firstName.equals(u.getFirstName()))
			{
				selectedUser = u;
			}
		}
		userModel.addAttribute("selectedUser", selectedUser);
		return "redirect:/matches";
	}

	/**
	 * Sender en besked til messageList.
	 * @param data
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sendMessage", method={RequestMethod.GET, RequestMethod.POST})
	public String sendMessage(WebRequest data, Model model) {
    	messageList.add(data.getParameter("send"));
		
    	return "redirect:/matches";	
	}

	/**
	 * henter den aktive bruger ind via en cookie, og derefter sender alle user data til en model
	 * som Thymeleaf genere 
	 * @param model
	 * @param request
	 * @return
	 */
    @GetMapping("/myProfile")
    public String myProfile(Model model, HttpServletRequest request){
		
		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

		model.addAttribute("user", activeUser);

        return "myProfile";
    }

	/**
	 * Tager data ind fra en form, og sender den til det til databasen, som så retter på den aktive bruges user data.
	 * @param data
	 * @param request
	 * @return
	 */
	@PostMapping("/myProfilePost") 
	public String myProfilePost(WebRequest data, HttpServletRequest request) {

		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

		if (!data.getParameter("name").equals("")) {
    		
    		String updatedName = String.valueOf(data.getParameter("name"));
			activeUser.setFirstName(updatedName);
		}
		
		if(!data.getParameter("lastName").equals("")) {
			String updatedLastName = String.valueOf(data.getParameter("lastName"));
			activeUser.setLastName(updatedLastName);
		}
    	
    	if (!data.getParameter("bio").equals("")) {
    		String updatedBio = String.valueOf(data.getParameter("bio"));
    		activeUser.setBio(updatedBio);
		}
    	
    	if (!data.getParameter("password").equals("") && !data.getParameter("password").equals(activeUser.getPassword()) && data.getParameter("password").equals(data.getParameter("passwordTwo"))) {
    		String updatedPassword = data.getParameter("password");
    		activeUser.setPassword(updatedPassword);
		}
    	
    	userService.updateUser(activeUser);
    	
		return "redirect:/myProfile";
	}

	/**
	 * Loader et billede fil ind, fra en hmtl form, og sender det til databasen. 
	 * @param imageFile
	 * @param request
	 * @return
	 */
	@PostMapping("/imageFile")
	public String imageFile(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {

    	int cookieId = userService.getCookieId(request);
    	
				try {
					userService.saveImage(imageFile, cookieId);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}
				
		return "redirect:/myProfile";
	}

	/**
	 * Tager alle bruger ind der har liket nogen, og derefter sorter dem efter deres sexual preference. og sender dem til explore siden.
	 * @param model
	 * @param request
	 * @return
	 */
    @GetMapping("/explore")
    public String explore(Model model, HttpServletRequest request)
    {
		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

		User potentialUser = null;
		
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

		model.addAttribute("user",potentialUser);

		return "/explore/explore";
    }

	/**
	 * Her har du liket brugern, og sender liket til databasen, og redirecter tilbage til explore siden.
	 * @param data
	 * @param request
	 * @return
	 */
    
    @PostMapping("/postExploreLiked")
	public String postExploreLiked(WebRequest data, HttpServletRequest request)
	{
		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

		User potentialUser = null;

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

	/**
	 * Her har du ikke liket brugeren og brugeren bliver fjernet fra di explore liste.
	 * @param data
	 * @param request
	 * @return
	 */

	@PostMapping("/postExploreSkipped")
	public String postExploreSkipped(WebRequest data, HttpServletRequest request)
	{
		int cookieId = userService.getCookieId(request);
		User activeUser = userService.getUserByID(cookieId);

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
