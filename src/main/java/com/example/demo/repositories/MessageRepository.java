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

    //Testing

    public void insertMessageIntoDatabase(String email, String message) {
        String insertMessageSQL = "INSERT INTO messages (email, message) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(insertMessageSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, message);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Requirement, we need to discuss how messages will be actioned

    public List<Message> selectMessagesFromDatabase(String email) {

        String selectMessages = "SELECT * FROM messages WHERE email = ?";

        List<Message> allMessages = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.establishConnection().prepareStatement(selectMessages);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message tmpMessage = new Message(
                resultSet.getString(1),
                resultSet.getString(2)
                );
                allMessages.add(tmpMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allMessages;
    }


}
