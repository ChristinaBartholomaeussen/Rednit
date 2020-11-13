package com.example.demo.services;

import com.example.demo.models.Match;
import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.repositories.MessageRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    MessageRepository messageRepository = new MessageRepository();

    public void sendMessage(User userFrom, User userTo, Message message) {

        messageRepository.insertMessageIntoDatabase(userFrom.getIdUser(), userTo.getIdUser(), message.getMessage());
    }

    public List<Message> getMessages(User user) {

        return messageRepository.selectMessagesFromDatabase(user.getIdUser(), user.getIdUser());
    }

    public Match getMatch(User user1, User user2)
    {
        List<Match> listOfMatches = new ArrayList<>();

        listOfMatches.add(new Match(52, 59));
        listOfMatches.add(new Match(52, 64));
        listOfMatches.add(new Match(52, 67));
        listOfMatches.add(new Match(59, 52));

        for(Match match : listOfMatches) {
            if (match.getIdUser() == user1.getIdUser() && match.getIdUserMatch() == user2.getIdUser()) {
                return match;
            }
        }
        return null;
    }
}
