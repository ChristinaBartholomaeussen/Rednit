package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService extends ProfileService{

    public List<User> allUsers = new ArrayList<>();




    /*****Kunne være en idé af bruge dette, så vi ikke oprettet en ny liste hver gang,
     * hvis det kan i forhold til vores database. På den på kan vi også bruge listen
     * og metoderne i klassen andre steder i vores kode, uden at den laver en ny liste****
     */
/*
    private static MyUser single_instance = null;

    public static MyUser getInstance()
    {
        if(single_instance == null)
        {
            single_instance = new MyUser();
        }

        return single_instance;

    }

 */

    public void createUser() {
        //TODO
        //INDSÆT KODE
    }

    public void addToAllUsers(User user) {
        allUsers.add(user); //Vi adder user til den fulde liste
    }

    public void deleteUser(User user) {
        //INDSÆT KODE
    }


    public void saveUserProfileChanges() {
        //TODO
        //INDSÆT KODE
    }

    public void uploadPhoto() {
        //TODO
        //INDSÆT KODE
    }

    public void getUser() {
        //TODO
        //INDSÆT KODE
    }

    public void getNextUser() {
        //TODO
        //INDSÆT KODE
    }

    public boolean likeUser(User user) {
        //TODO
        //INDSÆT KODE OBS PÅ MyCandidate

        return true;
    }

    public static List<User> getAllUsers() {

        UserRepository userRepository = new UserRepository();

        List<User> allUsers = userRepository.selectAllUsersFromDatabase();

        return allUsers;
    }


    /*public int calculateAge(){



        LocalDate birthday = user.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();



        return Period.between(currentDate, birthday).getYears();
    }*/









    public User getSingleUser(String email){
        //TODO
        return null;
    }
    public void addToLikedList(User user) {

    }

    public void addToDislikedList(User user) {

    }

    public void addToPotentialCandidates(User user) {

    }

    public boolean checkPassword(String password) {

        //ToDo
        /*
         *  Tilføj password securities.
         *  */

        return password.length() > 4 && password.length() < 20;
    }

    public boolean checkEmail(String email) {
        //INDSÆT kode, der laver op til vores email krav og om den allerede er i brug.

        if (email == null || email.isBlank()) {

            return false;
        } else return email.length() > 4 && email.indexOf('@') > 0 && email.indexOf('.') > email.indexOf('@');
    }

    UserRepository userRepository = new UserRepository();

    //Tjekker om vores email og password passer til det i vores database
    public boolean doesEmailMatchPassword(String email, String password) throws FileNotFoundException {


        List<User> usersFromDatabase = userRepository.selectAllUsersFromDatabase(); //Gemmer oplysningerne fra databasen i listen

            for (User u : usersFromDatabase) {
                if (email.equalsIgnoreCase(u.getPassword()) && password.equals(u.getPassword())) {
                    return true;
                }
            }

        return false;
    }

    //Skal returnere den bruger fra databasen, som har det matchende password + email.
    public User isLoggedIn(String email, String password, int idUser) throws FileNotFoundException {

        UserRepository userRepository = new UserRepository();

        User user = null;

        if(doesEmailMatchPassword(email, password))
        {
            user = userRepository.selectUserFromDatabase(idUser);
        }

        return user;
    }


	public static void saveImage(MultipartFile imageFile, User user) throws Exception {
		try {
			String folder = "./src/main/resources/static/photos";

			byte[] imageBytesArray = imageFile.getBytes();
			Path path = Paths.get(folder, imageFile.getOriginalFilename());
			Files.write(path, imageBytesArray);
			user.setPhoto1(String.valueOf(Files.write(path, imageBytesArray)));
			System.out.println(user.getPhoto1());

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
