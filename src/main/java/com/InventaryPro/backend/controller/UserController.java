package com.InventaryPro.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @GetMapping("/profile")
    public Map<String,String> getProfile(){
        return Map.of(
                "name","Jaya Krishna",
                "email","jk@gmail.com"
        );
    }
}
