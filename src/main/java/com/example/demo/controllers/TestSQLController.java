package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestSQLController {

    UserRepository userRepository = new UserRepository();

    AdminRepository adminRepository = new AdminRepository();

    MatchRepository matchRepository = new MatchRepository();

    MessageRepository messageRepository = new MessageRepository();

    User user = null;

    List<User> allUsersList = new ArrayList<>();

    @GetMapping("/test")
    public String test() {



        return "testSQL";
    }


}
