package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class LoginController
{
    UserService userServiceToDisplay = new UserService();
    UserRepository userRepository = new UserRepository();

    @GetMapping("/create")
    public String index(Model userModel)
    {
        userModel.addAttribute("userModel", userModel);
        return "create/createNewProfile";
    }

    @PostMapping("/postCreate")
    public String postCreate(WebRequest dataFromForm, HttpServletResponse response)
    {
        try
        {
            String email = String.valueOf(dataFromForm.getParameter("email"));
            String password1 = String.valueOf(dataFromForm.getParameter("password1"));
            String password2 = String.valueOf(dataFromForm.getParameter("password2"));

            if(!password1.equals(password2))
            {
                System.out.println("Passwords not equal");
                return "redirect:/create";
            }

            String firstName = dataFromForm.getParameter("firstName");
            String lastName = dataFromForm.getParameter("lastName");

            int gender;
            if(dataFromForm.getParameter("gender").equals("male"))
                gender = 1;
            else
                gender = 0;

            int sexualPreference;
            if(dataFromForm.getParameter("sexualPreference").equals("female"))
                sexualPreference = 0;
            else if(dataFromForm.getParameter("sexualPreference").equals("male"))
                sexualPreference = 1;
            else
                sexualPreference = 2;

            Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dataFromForm.getParameter("dateOfBirth"));

            //TODO Fil implementering

            String bio = dataFromForm.getParameter("bio");

            User user = new User(email,password1,firstName,lastName,dateOfBirth,gender,sexualPreference,bio,"","", "");

            userRepository.insertUserIntoDatabase(user);

        }
        catch(Exception e)
        {
            System.out.println("fejl: " + e);
            return "redirect:/create";
        }

        String id = "" + userRepository.selectUserFromDatabaseFromEmail(dataFromForm.getParameter("email")).getIdUser();
        Cookie cookie = new Cookie("id", id);
        response.addCookie(cookie);
        return "create/uploadPhoto";
    }

    @GetMapping("/postCreate")
	@ResponseBody
    public String uploadPicture(Model model, HttpServletRequest request)
    {
    	
    	
		//model.addAttribute("user", user);
    	return "create/uploadPhoto"; // HTML side
    }

    @PostMapping("/uploadPicture")
    public String uploadPicture(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) throws Exception {

		int activeUserId = UserService.getCookieId(request);
		UserService.createDir(activeUserId);
		
		UserService.saveImage(imageFile, activeUserId);
        return "redirect:/create/uploadPhoto";
    }


    @GetMapping("/login")
    public String postCreate(Model userModel){

        User userToDisplay = new User();
        userModel.addAttribute("userToDisplay", userToDisplay);
        userModel.addAttribute("userServiceToDisplay", userServiceToDisplay);

        return "loginPage";
    }

    //Postmapping til login - henter email og password fra html
    @PostMapping("/postLogin")
    public String userLogin(WebRequest dataFromForm, HttpServletResponse response) throws FileNotFoundException {

        List<User> userFromDB = userServiceToDisplay.getAllUsersLoginInformation();

        String useremail = dataFromForm.getParameter("email");
        String userpassword = dataFromForm.getParameter("password");

        if(useremail.equals("admin@rednit.dk") && userpassword.equals("admin")){
            return "redirect:/admin";
        }

        try{
			

            for(User user : userFromDB)
            {
            	
                if(user.getEmail().equals(useremail) && user.getPassword().equals(userpassword))
                {
					String id = "" + userRepository.selectUserFromDatabaseFromEmail(useremail).getIdUser();
					Cookie cookie = new Cookie("id", id);
					response.addCookie(cookie);
                    return "redirect:/explore";
                }
            }
            
        }catch(Exception e){
            System.out.println("fejl: " + e);
            return "redirect:/login";
        }

        System.out.println("Bruger findes ikke - redirecter til /create");
        return "redirect:/create";

    }


}
