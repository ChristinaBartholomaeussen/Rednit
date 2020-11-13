package com.example.demo.repositories;

import com.example.demo.models.Message;
import com.example.demo.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    ConnectionRepository connection = new ConnectionRepository();

    /**
     * Inserts an idUser, idUserMatch and message into the database
     * */

    public void insertMessageIntoDatabase(int idUser, int idUserMatch, String message) {
        String insertMessageSQL = "INSERT INTO messages (idUser, idUserMatch, message) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertMessageSQL);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idUserMatch);
            preparedStatement.setString(3, message);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Selects a single users matches and returns them as an ArrayList
     * */

    public List<Message> selectMessagesFromDatabase(int idUserMatch, int idUser) {

        String selectMatchMessages = "SELECT * FROM messages INNER JOIN users ON messages.idUser=users.idUser WHERE messages.idUserMatch = ? OR users.idUser = ?";

        List<Message> allMatchMessages = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectMatchMessages);
            preparedStatement.setInt(1, idUserMatch);
            preparedStatement.setInt(2, idUser);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message tmpMessage = new Message(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)
                );
                allMatchMessages.add(tmpMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allMatchMessages;
    }
}
