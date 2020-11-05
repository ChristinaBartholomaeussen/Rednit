package com.example.demo;

import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.ConnectionRepository;
import com.example.demo.services.MyUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

        ConnectionRepository con = new ConnectionRepository();

        con.establishConnection();
    }


}
