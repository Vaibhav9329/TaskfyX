package controller;


import config.JwtProvider;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;
import response.AuthResponse;
import service.CustomeUseDetailsImp;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomeUseDetailsImp customeUseDetailsImp;

    @PostMapping("/signup")
        public ResponseEntity<User> createUserHandler(@RequestBody User user) throws Exception {

            User isUserExist = userRepository.findByEmail(user.getEmail());
            if(isUserExist != null){
                throw new Exception("email already exist with another account");
            }
            User createdUser = new User();
            createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
            createdUser.setEmail(user.getEmail());
            createdUser.setFullname(user.getFullname());

            User savedUser = userRepository.save(createdUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("sign-up success");
        res.setJwt(jwt);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
}
