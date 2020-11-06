package com.example.demo.models;



import java.util.Date;
import java.util.List;

public class User extends Profil {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int gender;
    private int sexualPreference;
    private String bio;
    private List photos;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSexualPreference() {
        return sexualPreference;
    }

    public void setSexualPreference(int sexualPreference) {
        this.sexualPreference = sexualPreference;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List getPhotos() {
        return photos;
    }

    public void setPhotos(List photos) {
        this.photos = photos;
    }

    public User(String email, String password,
                String firstName, String lastName,
                Date dateOfBirth, int gender,
                int sexualPrefrerence, String bio)
    {
        super(email, password);
                this.firstName = firstName;
                this.lastName = lastName;
                this.dateOfBirth = dateOfBirth;
                this.gender = gender;
                this.sexualPreference = sexualPrefrerence;
                this.bio = bio;
    }
    
    /*  Mangler at sætte en liste med billeder.  */

    public User() {
       //Default constructor
    }











}
