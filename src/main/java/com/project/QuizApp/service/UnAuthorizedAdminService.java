package com.project.QuizApp.service;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.repository.AdminRepo;
import com.project.QuizApp.repository.UnAuthorizedAdminRepo;
import com.project.QuizApp.util.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnAuthorizedAdminService {

    @Autowired
    private UnAuthorizedAdminRepo unAuthorizedAdminRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private AdminRepo adminRepo;

    public ResponseEntity<String> addAdmin(UnAuthorizedAdmin unAuthorizedAdmin) {

        Optional<UnAuthorizedAdmin> UAAdmin = unAuthorizedAdminRepo.findByEmail(unAuthorizedAdmin.getEmail());
        if(UAAdmin.isPresent()){
            throw new UserAlreadyExistException("User already exist");
        }

        Optional<Admin> existUser =  adminRepo.findByEmail(unAuthorizedAdmin.getEmail());
        if(existUser.isPresent()){
            throw new UserAlreadyExistException("User already exist");
        }

        unAuthorizedAdmin.setPassword(encoder.encode(unAuthorizedAdmin.getPassword()));

        unAuthorizedAdminRepo.save(unAuthorizedAdmin);
        return new ResponseEntity<String>("Account Created Successfully!",HttpStatus.CREATED);
    }
}
