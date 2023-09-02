package com.movies.hussein.controllers;

import com.movies.hussein.models.UserLoginRequest;
import com.movies.hussein.models.UserRegistrationRequest;
import com.movies.hussein.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) throws NoSuchAlgorithmException {
        String username = request.getUsername();
        String email = request.getEmail();
        String password = request.getPassword();

        String message = userService.createAccount(username,email,password);


            return ResponseEntity.ok(message);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) throws NoSuchAlgorithmException {
        String username = request.getUsername();
        String password = request.getPassword();

        boolean success = userService.login(username,password);

        if(success){
            return ResponseEntity.ok("Login Successful!");
        }else{
            return ResponseEntity.ok("Login Failed!");
        }
    }

}
