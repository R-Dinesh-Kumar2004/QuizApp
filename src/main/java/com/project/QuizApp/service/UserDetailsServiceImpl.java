package com.project.QuizApp.service;

import com.project.QuizApp.model.User;
import com.project.QuizApp.model.UserPrincipal;
import com.project.QuizApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);

        if(user.isPresent()){
            return new UserPrincipal(user.get());
        }

        throw new UsernameNotFoundException("User not found");
    }
}