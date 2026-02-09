package com.example.blogapp.controller;

import com.example.blogapp.entity.UserRegisterEntity;
import com.example.blogapp.service.impl.UserRegisterEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRegisterEntityService userRegisterEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRegisterEntityService userRegisterEntityService, PasswordEncoder passwordEncoder) {
        this.userRegisterEntityService = userRegisterEntityService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user-register")
    public ResponseEntity<String> register(@RequestBody UserRegisterEntity userAuthDetails){
        //Hash the password before saving
        userAuthDetails.setPassword(passwordEncoder.encode(userAuthDetails.getPassword()));
        // save user
        userRegisterEntityService.save(userAuthDetails);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/users")
    public String getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "fetched user data successfully";
    }
}
