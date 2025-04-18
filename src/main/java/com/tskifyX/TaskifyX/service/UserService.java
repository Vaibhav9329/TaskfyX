package com.tskifyX.TaskifyX.service;

import com.tskifyX.TaskifyX.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);
    ResponseEntity<?> login(String email, String password);

    User findUserProfileByJwt(String jwt)  throws Exception;

    Optional<User> findUserByEmail(String email)throws Exception;

    User findUserById(User id)throws Exception;

    User updateUsersProject(User user,int number);

}
