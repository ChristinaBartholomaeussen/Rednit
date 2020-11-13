package com.example.demo.models;

import java.util.Date;


public class User extends Profile {

    private int idUser;
    private int idUserMatch;
    private int gender;
    private int sexualPreference;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String bio;
    private String photo1;

    public User(int idUser, int idUserMatch) {
        this.idUser = idUser;
        this.idUserMatch = idUserMatch;
    }

    public User(int idUser, String email, String password) {
        super(email, password);
        this.idUser = idUser;
    }

    public User(int idUser) {
        this.idUser = idUser;
    }
  
	
    public User(String email, String password,
                String firstName, String lastName,
                Date dateOfBirth, int gender,
                int sexualPrefrerence, String bio,
                String photo1)
    {
        super(email, password);
                this.firstName = firstName;
                this.lastName = lastName;
                this.dateOfBirth = dateOfBirth;
                this.gender = gender;
                this.sexualPreference = sexualPrefrerence;
                this.bio = bio;
                this.photo1 = photo1;
    }
    
    public User(String email, String password, String firstName, String lastName, Date dateOfBirth, int gender, int sexualPreference, String bio)
    {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.gender = gender;
        this.sexualPreference = sexualPreference;
    }

    public User() {
    }


    public User(int idUser, int idUserMatch, String email, String firstName, String lastName, Date dateOfBirth, String bio, int gender, int sexualPreference, String photo1) {
        super(email);
        this.idUser = idUser;
        this.idUserMatch = idUserMatch;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.gender = gender;
        this.sexualPreference = sexualPreference;
        this.photo1 = photo1;
    }

    public User(String email, String firstName, String lastName, Date dateOfBirth, String bio, int gender, int sexualPreference)
    {
        super(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.gender = gender;
        this.sexualPreference = sexualPreference;
    }

    public User(int idUser, String email, String password, String firstName, String lastName, Date dateOfBirth, String bio, int gender, int sexualPreference, String photo1) {
        super(email, password);
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.gender = gender;
        this.sexualPreference = sexualPreference;
        this.photo1 = photo1;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserMatch() {
        return idUserMatch;
    }

    public void setIdUserMatch(int idUserMatch) {
        this.idUserMatch = idUserMatch;
    }

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

    public int getSexualPreference() {
        return sexualPreference;
    }

    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto1(){
        return photo1;
    }

   public void setPhoto1(String photo1) {
    	this.photo1 = photo1;
   }

}
