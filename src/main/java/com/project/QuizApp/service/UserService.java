package com.project.QuizApp.service;

import com.project.QuizApp.model.User;
import com.project.QuizApp.repository.UserRepo;
import com.project.QuizApp.util.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public ResponseEntity<String> addUser(User user) {
        Optional<User> isEmailExist = userRepo.findByUserEmail(user.getUserEmail());

        if (isEmailExist.isPresent()) {
            throw new UserAlreadyExistException("User exist");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<Boolean> verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUserEmail(),user.getPassword()
        ));

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authentication.isAuthenticated()) {
            for(GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_USER")) {
                    return ResponseEntity.ok(true);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Boolean> verifyAdmin(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUserEmail(),user.getPassword()
        ));

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authentication.isAuthenticated()) {
            for(GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return ResponseEntity.ok(true);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}