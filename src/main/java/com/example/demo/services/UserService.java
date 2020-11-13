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

import javax.servlet.AsyncContext;
import javax.servlet.Servlet;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	/**
	 * Tjekker om brugens login kredintialer matcher en bruger.
	 * @param email
	 * @param password
	 * @return
	 */
    public User loggedInUser(String email, String password){

        for(User u : allUsers){
            if(email.equals(u.getEmail()) && password.equals(u.getPassword())){
                user = u;
            }
        }

        return user;
    }

	/**
	 * Sender en user tilbage.
	 * @return
	 */
    public User userToDisplay(){
       return user;
    }

	/**
	 * Sender en ny blank bruger tilbage.
	 */
    public void setUserToDefault(){
        user = new User();
    }

	/**
	 * Sletter en bruger fra databasen
	 * @param id
	 */
    public void deleteUser(int id) {

        userRepository.deleteUserFromDatabase(id);
    }

	/**
	 * Sletter et photo fra databasen
	 * @param user
	 */
	public void deletePhoto(User user){
        user.setPhoto1(null);
        userRepository.updatePhotoInDatabase(user);
    }

	/**
	 * Sender en liste med alle bruger
	 * @return
	 */
	public List<User> getAllUsers() {

        UserRepository userRepository = new UserRepository();

        List<User> allUsers = userRepository.selectAllUsersFromDatabase();

        return allUsers;
    }

	/**
	 * Sender alle user login info tilbage i en liste.
	 * @return
	 */
    public List<User> getAllUsersLoginInformation() {
        return userRepository.selectAllUsersLoginInformationFromDatabase();
    }

	/**
	 * Sender en enkelt bruger tilbage, som bliver hentet fra databasen med en email.
	 * @param email
	 * @return
	 */
	public User getSingleUser(String email){
        return userRepository.selectUserFromDatabaseFromEmail(email);
    }

	/**
	 * Gemmer et fil stien til brugersn profil billede i databasen, og gemmer billedet på computerens storage.
	 * @param imageFile
	 * @param cookie
	 * @throws Exception
	 */
	public void saveImage(MultipartFile imageFile, int cookie) throws Exception {
		try {
            User user = userRepository.selectUserFromDatabase(cookie);

		    String folder = "./src/main/resources/static/photos/fotos" + cookie;
			String imgPath = "./photos/fotos" + cookie+ "/" + imageFile.getOriginalFilename();



			byte[] imageBytesArray = imageFile.getBytes();
			Path path = Paths.get(folder, imageFile.getOriginalFilename());
			Files.write(path, imageBytesArray);

			user.setPhoto1(imgPath);
			userRepository.updatePhotoInDatabase(user);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Henter en cookie fra browseren som har brugerens id.
	 * @param request
	 * @return
	 */
	public int getCookieId(HttpServletRequest request) {

		Cookie cookie[] = request.getCookies();

		Cookie cookieId = new Cookie("id", "");

		for (Cookie cookie1 : cookie) {
			if (cookie1.getName().equals("id")) {
				cookieId.setValue(cookie1.getValue());
			}
		}
		
		return Integer.parseInt(cookieId.getValue());
	}

	/**
	 * Laver en mapper hvor billederne kan gemmes.
	 * @param cookie
	 */
	public  void createDir(int cookie) {
		String path = "./src/main/resources/static/photos/fotos" + cookie;
		File file = new File(path);
		file.mkdir();
	}

	/**
	 * Henter en user med id'et
	 * @param idUser
	 * @return
	 */
    public User getUserByID(int idUser)
    {
        return userRepository.selectUserFromDatabase(idUser);
    }

	/**
	 * Gemmer en user i databasen.
	 * @param user
	 */
	public void insertNewUser(User user)
    {
        userRepository.insertUserIntoDatabase(user);
    }

	/**
	 * Updatere den aktive brugers data.
	 * @param user
	 */
    public void updateUser(User user) {
        userRepository.updateUserInfoInDatabase(user);
    }
}
