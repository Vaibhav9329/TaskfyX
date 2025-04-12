package com.tskifyX.TaskifyX.service;


import com.tskifyX.TaskifyX.model.User;

import org.springframework.http.ResponseEntity;


public interface UserService {

    User registerUser(User user);
    ResponseEntity<?> login(String email, String password);

}
