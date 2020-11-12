package com.example.demo.repositories;

import java.sql.*;

public class ConnectionRepository {

    //Finished

    public Connection establishConnection() {
        Connection connection = null;
        try {
             connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/Rednit", "rednit", "tinder!");
        } catch (SQLException e) {
            System.out.println("No Connection");
        }
        return connection;
    }
}
