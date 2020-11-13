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

    /**
     * Tilføjer 2 lister, allUsers og blackListedUsers, samt admin, så de kan tilgås via Thymeleaf i HTML koden
     * @param adminModel
     * @return
     */
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

    /**
     * Modtager en parameter fra html-forms, som tager et userid og skabes et User objekt ud fra dette.
     * Derefter itereres der over Blacklisted-listen, og hvis objektets firstName findes i brugerlisten,
     * så fjernes objektet fra blacklistedUser og fjernes fra Blacklistedre brugere på databasen
     *
     * Derefter redirectes tilbage til Admin page.
     * @param dataFromForm
     * @return
     */
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

    /**
     * Skaber en ny User i userServices. Henter parametren firstname fra HTML-koden. Itererer over allUsers
     * liste og hvis firstname er lig med den blacklistede User, så populeres den nye Users attributter med data fra den blacklistede
     * brugers informationer.
     * @param dataFromForm
     * @return
     */
    @PostMapping("/postAdmin")
    public String adminStart(WebRequest dataFromForm) {

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

    /**
     * Opretter et nyt User objekt i userService. Henter firstname2 fra dataFromForm og itererer over en blackListedUser
     * liste. Hvis firstname2 er lgi med den itererede bruger, så sættes attributterne i det nye user objekt til
     * useren fra blacklisteduser.
     *
     * Derefter redirectes tilbage til Admin.
     * @param dataFromForm
     * @return
     */
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

    /**
     * Henter en Delete-string fra forms i HTML i kode. Itererer over en liste af allUsers og hvis den iterede
     * bruger i allUsers listen har samme navn som Delete-string, så slettes denne bruger fra allUsers
     * og databasen. Derefter redirectes til Admin-siden
     * @param dateFromForm
     * @return
     */
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

    /**
     * OPretter en blacklist-string og itererer over en allUsers liste. Hvis brugeren i allUsers listen har samme
     * navn som blacklist-string, så fjernes brugeren fra databasen.
     * @param dateFromForm
     * @return
     */
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


    /**
     * Modtager en deletePhoto-string fra forms i HTML. Hvis deletePhoto-string er "Slet billede", så itereres
     * der over en allUsers liste. Hvis den itererede bruger har samme firstName som userToDisplay, så slettes den itererede
     * brugers billede-lokation fra databasen. Derefter redirectes tilbage til Admin.
     * @param dataFromForm
     * @return
     */
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
