package com.example.demo.controllers;

import com.example.demo.models.Match;
import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class TestController {

    UserRepository userRepository = new UserRepository();

    AdminRepository adminRepository = new AdminRepository();

    MatchRepository matchRepository = new MatchRepository();

    MessageRepository messageRepository = new MessageRepository();

    Message message = new Message();

    MessageService messageService = new MessageService();

    User user = new User();

    List<User> allUsersList = new ArrayList<>();

    Match match = new Match(123,1234);

    @GetMapping("/test")
    public String test() {

        User user1 = new User(59);
        User user2 = new User(52);

        match = messageService.getMatch(user1, user2);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        messageRepository.insertMessageIntoDatabase(match.getIdUser(), match.getIdUserMatch(), message);

        List<Message> messages = messageRepository.selectMessagesFromDatabase(match.getIdUser(), match.getIdUser());

        System.out.println(messages);

        return "test";
    }


}
