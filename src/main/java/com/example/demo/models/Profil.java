package com.example.demo.models;

public abstract class Profil {

    private String email;
    private String password;

    public Profil(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public Profil() {
        //Default constructor
    }

    public void deletePhoto(int indexOfPhoto, User user) {
        //TODO
        //INDSÆT KODE
    }

    public boolean checkPassword(String password) {
        //INDSÆT KODE så password lever op til vores krav.

        return true;
    }

    public boolean checkEmail(String email) {
        //INDSÆT kode, der laver op til vores email krav og om den allerede er i brug.

        return true;
    }
}
