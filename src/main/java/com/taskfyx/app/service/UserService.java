package com.taskfyx.app.service;

import com.taskfyx.app.model.User;
import org.springframework.http.ResponseEntity;


public interface UserService {

    User registerUser(User user);
    ResponseEntity<?> login(String email, String password);

}
