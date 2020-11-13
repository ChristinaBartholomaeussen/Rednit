package com.example.demo.models;

import java.time.LocalDateTime;

public class Message extends User {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    public Message(int idUser, int idUserMatch, String message) {
        super(idUser, idUserMatch);
        this.message = message;
    }

    public Message() {

    }

    public Message(String string, String string1) {

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
