package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.UserService;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController
{
    List<User> allUsers = new ArrayList<>();
    User userToDisplay = new User();
    AdminService admin = new AdminService();

    UserService userService = new UserService();

    @GetMapping("/admin")
    public String adminPage(Model adminModel)
    {

        adminModel.addAttribute("users", allUsers);
        adminModel.addAttribute("userToDisplay", userToDisplay);
        adminModel.addAttribute("admin", admin);


        //Dummy code - 45 nye Users
        for(int i = 0; i < 45; i++)
        {
            Date date = new Date(i);
            allUsers.add(new User("email"+i,"password"+i,"firstName"+i,"lastName"+i,date,i,i,"bio"+i));
        }

        //User user = new User();


        return "adminPage";
    }

    @PostMapping("/postAdmin")
    public String adminPageDelete(WebRequest dataFromForm) throws FileNotFoundException {

        String firstname = dataFromForm.getParameter("firstname");



        for(User u : allUsers){
            if(firstname.equals(u.getFirstName()))
            {
                userToDisplay = u;



                String image1 = userToDisplay.getPhoto1();
                String image2 = userToDisplay.getPhoto2();
                String image3 = userToDisplay.getPhoto3();
                while(image1 != null && image2 != null & image3 != null){
                    String i1 = new String(image1);
                    String i2 = new String(image2);
                    String i3 = new String(image3);
                    userToDisplay.setPhoto(i1, i2, i3);
                }

            }
        }

        return "redirect:/admin";
    }

    //TODO CB indsæt metode til at håndtere bruger + adminPage



}
