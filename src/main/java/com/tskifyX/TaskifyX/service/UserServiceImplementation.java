package com.tskifyX.TaskifyX.service;


import com.tskifyX.TaskifyX.config.jwt.JwtProvider;
import com.tskifyX.TaskifyX.exception.UserException;
import com.tskifyX.TaskifyX.model.User;

import com.tskifyX.TaskifyX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customeUseDetailsImp;

    @Autowired
    private  JwtProvider jwtProvider;

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserException("User", "User already exists with this email");
        }
        return userRepository.save(user);
    }



    @Override
    public ResponseEntity<?> login(String email, String password) {
        try {
            UserDetails userDetails;
            try {
                userDetails = customeUseDetailsImp.loadUserByUsername(email);
            } catch (UsernameNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String jwt = jwtProvider.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public Optional<User> findUserProfileByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws Exception {
       Optional<User> user = userRepository.findByEmail(email);
       if(user == null){
           throw new Exception("user not found");
       }
        return user;
    }

    @Override
    public User findUserById(User userId) throws Exception {
     User optionalUser = UserRepository.findUserById(userId);
      if(optionalUser == null){
          throw new Exception("user not found");
      }
        return optionalUser;
    }

    @Override
    public User updateUsersProject(User user, int number) {
        user.setProjectSize(user.getProjectSize()+number);
        return userRepository.save(user);
    }

}
