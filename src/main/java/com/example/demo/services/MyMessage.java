package com.example.demo.services;

import com.example.demo.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MyMessage {

    public List<Message> messages = new ArrayList<>();

    public void sendMessage() {
        //INDSÆT KODE
    }

    public void saveMessage(Message message) {
        messages.add(message);
    }
}
