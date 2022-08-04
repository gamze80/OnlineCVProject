package com.example.onlinecvproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MainController {


    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

}
