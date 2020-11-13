package com.example.demo.controllers;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.List;

@Controller
public class AdminController
{
    UserService userService = new UserService();
    AdminService admin = new AdminService();
    List<User> allUsers;
    List<User> blacklistedUsers;

    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {
        allUsers = userService.getAllUsers();
        blacklistedUsers = admin.getBlacklist();

        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("userToDisplay", userService.userToDisplay());
        adminModel.addAttribute("admin", admin);
        adminModel.addAttribute("blacklist", blacklistedUsers);
        adminModel.addAttribute("userService", userService);

        return "adminPage";
    }

    @PostMapping("restoreUserFromBlackList")
    public String restoreUserFromBlackList(WebRequest dataFromForm){

        String userID = dataFromForm.getParameter("restore");

        try{
            if(userID.equals("Genaktiver")) {
                for (User u : blacklistedUsers) {
                    if (userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())) {

                        admin.restoreUser(u.getIdUser());
                        blacklistedUsers.remove(u);
                    }
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
            userService.setUserToDefault();
            return "redirect:/admin";
        }catch (NullPointerException e){
            return "redirect:/admin";
        }

        userService.setUserToDefault();
        return "redirect:/admin";
    }

    @PostMapping("/postAdmin")
    public String adminStart(WebRequest dataFromForm) throws FileNotFoundException {

        userService.setUserToDefault();

        try{
            String firstname = dataFromForm.getParameter("firstname");

                for(User u : allUsers){
                    if(firstname.equals(u.getFirstName()))
                    {
                        userService.userToDisplay().setFirstName(u.getFirstName());
                        userService.userToDisplay().setLastName(u.getLastName());
                        userService.userToDisplay().setEmail(u.getEmail());
                        userService.userToDisplay().setDateOfBirth(u.getDateOfBirth());
                        userService.userToDisplay().setBio(u.getBio());
                        userService.userToDisplay().setPhoto1(u.getPhoto1());
                    }
                }


        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
            userService.setUserToDefault();
            return "redirect:/admin";
        }

        return "redirect:/admin";
    }

    @PostMapping("/showBlacklist")
    public String showBlack(WebRequest dataFromForm) {

        userService.setUserToDefault();

        try{
            String firstname2 = dataFromForm.getParameter("firstname2");

            for(User u : blacklistedUsers){
                if(firstname2.equals(u.getFirstName()))
                {
                    userService.userToDisplay().setFirstName(u.getFirstName());
                    userService.userToDisplay().setLastName(u.getLastName());
                    userService.userToDisplay().setEmail(u.getEmail());
                    userService.userToDisplay().setDateOfBirth(u.getDateOfBirth());
                    userService.userToDisplay().setBio(u.getBio());
                    userService.userToDisplay().setPhoto1(u.getPhoto1());
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
            userService.setUserToDefault();
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }


   @PostMapping("/adminDeleteUser")
    public String deleteUser(WebRequest dateFromForm){

        String delete = String.valueOf(dateFromForm.getParameter("deleteUser"));

        try{
            if(delete.equals("Slet bruger")) {
                for (User u : allUsers) {
                    if (userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())) {

                        userService.deleteUser(u.getIdUser());
                        allUsers.remove(u);
                    }
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
            userService.setUserToDefault();
            return "redirect:/admin";
        }catch (NullPointerException e){
            return "redirect:/admin";
        }

        userService.setUserToDefault();
        return "redirect:/admin";
    }

    @PostMapping("/adminBlacklistUser")
    public String blacklistUser(WebRequest dateFromForm)
    {
        String blacklist = String.valueOf(dateFromForm.getParameter("blacklistUser"));

        try{
            if(blacklist.equals("Blacklist bruger")){
                for(User u : allUsers){
                    if(userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())){
                        admin.addToBlacklist(u);
                    }
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println(e.getMessage());
            userService.setUserToDefault();
            return "redirect:/admin";

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return "redirect:/admin";
        }

        userService.setUserToDefault();
        return "redirect:/admin";
    }


    @PostMapping("/adminDeletePhoto")
    public String deletePhoto(WebRequest dataFromForm){

        String deletePhoto = String.valueOf(dataFromForm.getParameter("deletePhoto"));

        if(deletePhoto.equals("Slet billede"))
        {
            try{
                for(User u : allUsers){
                    if(userService.userToDisplay().getFirstName().equals(u.getFirstName()) && userService.userToDisplay().getEmail().equals(u.getEmail())){
                        userService.deletePhoto(u);
                    }
                }
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
                userService.setUserToDefault();
                return "redirect:/admin";
            }

        }
        userService.setUserToDefault();
        return "redirect:/admin";
    }
}
