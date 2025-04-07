package com.taskfyx.app.controller;

import com.taskfyx.app.config.jwt.JwtProvider;
import com.taskfyx.app.model.User;
import com.taskfyx.app.service.CustomUserDetailsService;
import com.taskfyx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customeUseDetailsImp;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserController(
            UserService userService,
            PasswordEncoder passwordEncoder,
            CustomUserDetailsService customeUseDetailsImp,
            JwtProvider jwtProvider) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customeUseDetailsImp = customeUseDetailsImp;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userService.registerUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        return userService.login(email, password);
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getProfile(Authentication auth) {
        return ResponseEntity.ok("Hello, " + auth.getName());
    }


}
