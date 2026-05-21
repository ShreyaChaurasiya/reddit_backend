package com.reddit.reddit.service;

import com.reddit.reddit.dto.LoginRequest;
import com.reddit.reddit.dto.RegisterRequest;
import com.reddit.reddit.entity.User;
import com.reddit.reddit.repository.UserRepository;
import com.reddit.reddit.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String signup(
            RegisterRequest request
    ) {

        User user = new User();

        user.setUsername(
                request.getUsername()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(

                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(
            LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(
                        request.getEmail()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"
                        )
                );

        boolean valid =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!valid) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        return jwtUtil.generateToken(
                user.getEmail()
        );
    }
}