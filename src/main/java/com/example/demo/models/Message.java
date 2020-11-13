package com.example.demo.models;

public class Message extends User {

    private String message;

    public Message(int idUser, int idUserMatch, String message) {
        super(idUser, idUserMatch);
        this.message = message;
    }

    public Message() {

    }


    public String getMessage() {
        return message;
    }



}
