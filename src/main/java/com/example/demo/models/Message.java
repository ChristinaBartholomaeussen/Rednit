package com.example.demo.models;

import java.time.LocalDateTime;

public class Message {

    private final LocalDateTime timestamp = LocalDateTime.now(); //Vi får forhåbenlig altid en ny tid.
    private String message;

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
