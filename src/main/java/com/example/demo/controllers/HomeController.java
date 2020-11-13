package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/**
	 * Giver vores loginside index.html funktionalitet
	 * @return
	 */
    @GetMapping("/")
    public String firstPage() {
        return "loginPage";
    }

}














