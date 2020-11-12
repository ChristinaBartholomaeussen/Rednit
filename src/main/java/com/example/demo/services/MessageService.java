package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    MessageRepository messageRepository = new MessageRepository();

    public List<Message> messages;

    public void sendMessage(User userFrom, User userTo, Message message) {

        messageRepository.insertMessageIntoDatabase(userFrom.getIdUser(), userTo.getIdUser(), message.getMessage());
    }
}
