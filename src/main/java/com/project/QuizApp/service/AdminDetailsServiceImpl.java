package com.project.QuizApp.service;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.AdminPrincipal;
import com.project.QuizApp.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByEmail(email);

        if(admin.isPresent()){
            return new AdminPrincipal(admin.get());
        }

        throw new UsernameNotFoundException("Admin not found");
    }
}
