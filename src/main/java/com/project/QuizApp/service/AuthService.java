package com.project.QuizApp.service;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.User;
import com.project.QuizApp.repository.UserRepo;
import com.project.QuizApp.util.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public ResponseEntity<String> addUser(User user) {
        Optional<User> isEmailExist = userRepo.findByEmail(user.getEmail());

        if (isEmailExist.isPresent()) {
            throw new UserAlreadyExistException("User exist");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<Boolean> verifyUser(User user) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(),user.getPassword()
            ));

            return ResponseEntity.ok(true);

        }catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

    public ResponseEntity<Boolean> verifyAdmin(Admin admin) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    admin.getEmail(),admin.getPassword()
            ));

            return ResponseEntity.ok(true);

        }catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
}