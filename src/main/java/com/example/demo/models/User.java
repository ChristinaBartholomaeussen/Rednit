package com.example.demo.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User extends Profile {

    private int idUser;
    private int idUserMatch;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int gender;
    private int sexualPreference;
    private String bio;
    private String photo1, photo2, photo3;
    private List<User> likedUsers;
    private List<User> dislikedUsers;
    private List<User> potentialCandidates;
    private int cookie;

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
                String photo1, String photo2, String photo3, List likedUsers, List dislikedUsers, List potentialCandidates)
    {
        super(email, password);
                this.firstName = firstName;
                this.lastName = lastName;
                this.dateOfBirth = dateOfBirth;
                this.gender = gender;
                this.sexualPreference = sexualPrefrerence;
                this.bio = bio;
                this.photo1 = photo1;
                this.photo2 = photo2;
                this.photo3 = photo3;
                this.likedUsers = new ArrayList<>();
                this.dislikedUsers = new ArrayList<>();
                this.potentialCandidates = new ArrayList<>();

    }
    
    /*  Mangler at sætte en liste med billeder.  */

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

    public User(String email, String password, String firstName, String lastName, Date dateOfBirth, int gender, int sexualPreference, String bio, String photo1, String photo2, String photo3)
    {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.sexualPreference = sexualPreference;
        this.bio = bio;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public User() {
       //Default constructor
    }

    public User(String email) {
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

    public User(String email, String firstName, String lastName, Date dateOfBirth, String bio, int gender, int sexualPreference, String photo1)
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

    public String getPhoto1(){
        return photo1;
    }

    public String getPhoto2(){
        return photo2;
    }

    public String getPhoto3(){
        return photo3;
    }

   public void setPhoto1(String photo1) {
    	this.photo1 = photo1;
   }

    public List getLikedUsers() {
        return likedUsers;
    }

    public List getDislikedUsers() {
        return dislikedUsers;
    }

    public List getPotentialCandidates() {
        return potentialCandidates;
    }

  
    @Override
	public String toString() {
    	return "Id: " + idUser + "\nIdUserMatch: " + idUserMatch + "\nNavn: " + getFirstName() + "\nEmail: " + getEmail() + "\nGender: " + gender + "\nSexual Prefrence: " + sexualPreference + "\nFilsti til foto: " + photo1 + "\nBio: " + bio;
	}


}
