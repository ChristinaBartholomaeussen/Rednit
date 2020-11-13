package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserService{

    UserRepository userRepository = new UserRepository();
    public List<User> allUsers = userRepository.selectAllUsersFromDatabase();
    User user = new User();

    public User loggedInUser(String email, String password){

        for(User u : allUsers){
            if(email.equals(u.getEmail()) && password.equals(u.getPassword())){
                user = u;
            }
        }

        return user;
    }


    public User userToDisplay(){
       return user;
    }

    public void setUserToDefault(){
        user = new User();
    }


    public void createUser() {
        //TODO
        //INDSÆT KODE
    }

    public void deleteUser(int id) {

        userRepository.deleteUserFromDatabase(id);
    }

    public void deletePhoto(User user){
        user.setPhoto1(null);
        userRepository.updatePhotoInDatabase(user);
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

    public List<User> getAllUsers() {

        UserRepository userRepository = new UserRepository();

        List<User> allUsers = userRepository.selectAllUsersFromDatabase();

        return allUsers;
    }

    public List<User> getAllUsersLoginInformation() {
        return userRepository.selectAllUsersLoginInformationFromDatabase();
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
        return password.length() > 4 && password.length() < 20;
    }

    public boolean checkEmail(String email) {
        //INDSÆT kode, der laver op til vores email krav og om den allerede er i brug.

        if (email == null || email.isBlank()) {

            return false;
        } else return email.length() > 4 && email.indexOf('@') > 0 && email.indexOf('.') > email.indexOf('@');
    }

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

    public User isLoggedIn(String email, String password, int idUser) throws FileNotFoundException {

        UserRepository userRepository = new UserRepository();

        User user = new User();

        if(doesEmailMatchPassword(email, password))
        {
            user = userRepository.selectUserFromDatabase(idUser);
        }

        return user;
    }


	public static void saveImage(MultipartFile imageFile, int cookie) throws Exception {
		try {
			String folder = "./src/main/resources/static/photos/fotos" + cookie;
			String imgPath = "./photos/fotos" + cookie+ "/" + imageFile.getOriginalFilename();

			UserRepository userRepository = new UserRepository();

			User user = userRepository.selectUserFromDatabase(cookie);

			byte[] imageBytesArray = imageFile.getBytes();
			Path path = Paths.get(folder, imageFile.getOriginalFilename());
			Files.write(path, imageBytesArray);
			
			user.setPhoto1(imgPath);
			userRepository.updatePhotoInDatabase(user);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static int getCookieId(HttpServletRequest request) {

		Cookie cookie[] = request.getCookies();

		Cookie cookieId = new Cookie("id", "");

		for (Cookie cookie1 : cookie) {
			if (cookie1.getName().equals("id")) {
				cookieId.setValue(cookie1.getValue());
			}
		}
		
		return Integer.parseInt(cookieId.getValue());
	}

	public static void createDir(int cookie) {
		String path = "./src/main/resources/static/photos/fotos" + cookie;
		File file = new File(path);
		file.mkdir();
	}

    public User getUserByID(int idUser) {
        for (User u : allUsers) {
            if (idUser == u.getIdUser()) {
                user = u;
            }
        }

        return user;
    }
}
