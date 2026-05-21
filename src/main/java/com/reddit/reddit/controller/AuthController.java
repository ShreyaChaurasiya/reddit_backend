package com.reddit.reddit.controller;

import com.reddit.reddit.dto.LoginRequest;
import com.reddit.reddit.dto.RegisterRequest;
import com.reddit.reddit.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")

@CrossOrigin("*")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(

            @RequestBody
            RegisterRequest request
    ) {

        return authService.signup(
                request
        );
    }

    @PostMapping("/login")
    public String login(

            @RequestBody
            LoginRequest request
    ) {

        return authService.login(
                request
        );
    }
}