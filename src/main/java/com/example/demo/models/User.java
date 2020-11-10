package com.example.demo.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User extends Profile {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int gender;
    private int sexualPreference;
    private String bio;
    private byte[] photo1, photo2, photo3;
    private List<User> likedUsers;
    private List<User> dislikedUsers;
    private List<User> potentialCandidates;


  
	
    public User(String email, String password,
                String firstName, String lastName,
                Date dateOfBirth, int gender,
                int sexualPrefrerence, String bio,
                byte[] photo1, byte[] photo2, byte[] photo3, List likedUsers, List dislikedUsers, List potentialCandidates)
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
        this.gender = gender;
        this.sexualPreference = sexualPreference;
        this.bio = bio;
    }

    public User(String email, String password, String firstName, String lastName, Date dateOfBirth, int gender, int sexualPreference, String bio, byte[] photo1, byte[] photo2, byte[] photo3)
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

    public byte[] getPhoto1(){
        return photo1;
    }

    public byte[] getPhoto2(){
        return photo2;
    }

    public byte[] getPhoto3(){
        return photo3;
    }

    public List<String> setPhoto(String photo1, String photo2, String photo3){
        ArrayList<String> photos = new ArrayList<>();

        photos.add(photo1);
        photos.add(photo2);
        photos.add(photo3);

        return photos;
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
    public String toString()
    {
        return "User{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", sexualPreference=" + sexualPreference + ", bio='" + bio + '\'' + '}';
    }

	public void saveImage(MultipartFile imageFile) throws Exception {
    	try {
    		//String folder = "/Documents/Dev/java/2.semester/Rednit/src/main/resources/static/photos";
			String folder = "./src/main/resources/static/photos";

    		byte[] imageBytesArray = imageFile.getBytes();
			Path path = Paths.get(folder, imageFile.getOriginalFilename());
			Files.write(path, imageBytesArray);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
