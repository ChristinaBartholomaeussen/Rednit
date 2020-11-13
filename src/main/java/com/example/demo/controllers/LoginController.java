package com.example.demo.controllers;
import com.example.demo.models.Admin;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController
{
    UserService userService = new UserService();
    AdminService adminService = new AdminService();

	/**
	 * Redirecter til createNewPRofile.
	 * @param userModel
	 * @return
	 */
	@GetMapping("/create")
    public String create(Model userModel)
    {
        userModel.addAttribute("userModel", userModel);
        return "create/createNewProfile";
    }

	/**
	 *Laver en user udfra data som bliver sendt fra et html form
	 * @param dataFromForm
	 * @param response
	 * @return
	 */
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

            String bio = dataFromForm.getParameter("bio");

            User user = new User(email,password1,firstName,lastName,dateOfBirth,gender,sexualPreference,bio);

            userService.insertNewUser(user);

        }
        catch(Exception e)
        {
            System.out.println("fejl: " + e);
            return "redirect:/create";
        }

        String id = "" + userService.getSingleUser(dataFromForm.getParameter("email")).getIdUser();
        Cookie cookie = new Cookie("id", id);
        response.addCookie(cookie);
        return "create/uploadPhoto";
    }

	/**
	 * redirecter til siden hvor du uploader billeder
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/postCreate")
	@ResponseBody
    public String uploadPicture(Model model, HttpServletRequest request)
    {
    	return "create/uploadPhoto";
    }

	/**
	 * Uploader et billede fra html til databasen
	 * @param imageFile
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/uploadPicture")
    public String uploadPicture(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) throws Exception {

		int activeUserId = userService.getCookieId(request);
        userService.createDir(activeUserId);

        userService.saveImage(imageFile, activeUserId);
        return "redirect:/explore	";
    }

	/**
	 * Viser login siden
	 * @param userModel
	 * @return
	 */
    @GetMapping("/login")
    public String postCreate(Model userModel)
    {
        User userToDisplay = new User();
        userModel.addAttribute("userToDisplay", userToDisplay);

        userModel.addAttribute("adminService", adminService);

        return "loginPage";
    }

	/**
	 * HEnter data fra login siden og checker databasen igennem, om det matcher en bruger i databasen.
	 * @param dataFromForm
	 * @param response
	 * @return
	 */
    @PostMapping("/postLogin")
    public String userLogin(WebRequest dataFromForm, HttpServletResponse response)
    {

        List<User> blacklistedUsersFromDB = adminService.getBlacklistedUsers();
        List<Admin> adminsFromDB = adminService.getAllAdmins();
        List<User> usersFromDB = userService.getAllUsersLoginInformation();

        String enteredEmail = dataFromForm.getParameter("email");
        String enteredPassword = dataFromForm.getParameter("password");

        for(User blacklistedUser : blacklistedUsersFromDB)
            if(blacklistedUser.getEmail().equals(enteredEmail) && blacklistedUser.getPassword().equals(enteredPassword))
                return "redirect:/blacklist";

        for(Admin admin : adminsFromDB)
            if(admin.getEmail().equals(enteredEmail) && admin.getPassword().equals(enteredPassword))
                return "redirect:/admin";

        for(User user : usersFromDB)
            if(user.getEmail().equals(enteredEmail) && user.getPassword().equals(enteredPassword))
            {
                user = userService.loggedInUser(enteredEmail, enteredPassword);

				String id = "" + userService.getSingleUser(dataFromForm.getParameter("email")).getIdUser();
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie);
				
                return "redirect:/explore";
            }

        return "redirect:/create";
    }

	/**
	 * Redirecter til en blacklist side
	 * @return
	 */
	@GetMapping("/blacklist")
    public String userBlacklisted(){

        return "blacklistPage";
    }
}
