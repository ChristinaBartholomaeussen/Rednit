package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.UserRepository;

import java.util.ArrayList;
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

    public List getAllUsers(){
        return null;
    }

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

    AdminRepository adminRepository = new AdminRepository();

    //Tjekker om vores email og password passer til det i vores database
    public boolean doesEmailMatchPassword(String email, String password) {


        List<User> usersFromDatabase = adminRepository.getAllUsersFromDatabase(); //Gemmer oplysningerne fra databasen i listen

        if (password.equalsIgnoreCase("admin") && email.equalsIgnoreCase("admin@admin.dk")) {
            return true;
        } else
            {
            for (User u : usersFromDatabase) {
                if (email.equalsIgnoreCase(u.getPassword()) && password.equals(u.getPassword())) {
                    return true;
                }
            }
        }

        return false;
    }

    //Skal returnere den bruger fra databasen, som har det matchende password + email.
    public User isLoggedIn(String email, String password){

        UserRepository userRepository = new UserRepository();

        User user = null;

        if(doesEmailMatchPassword(email, password))
        {
            user = userRepository.getSingleUserFromDatabase();
        }

        return user;
    }


}
