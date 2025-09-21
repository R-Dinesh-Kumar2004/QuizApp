package com.project.QuizApp.service;

import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.model.User;
import com.project.QuizApp.repository.UnAuthorizedAdminRepo;
import com.project.QuizApp.repository.UserRepo;
import com.project.QuizApp.util.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnAuthorizedAdminService {

    @Autowired
    private UnAuthorizedAdminRepo unAuthorizedAdminRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<String> addAdmin(UnAuthorizedAdmin unAuthorizedAdmin) {

        Optional<UnAuthorizedAdmin> UAAdmin = unAuthorizedAdminRepo.findByUserEmail(unAuthorizedAdmin.getUserEmail());
        if(UAAdmin.isPresent()){
            throw new UserAlreadyExistException("User already exist");
        }

        Optional<User> existUser =  userRepo.findByUserEmail(unAuthorizedAdmin.getUserEmail());
        if(existUser.isPresent()){
            throw new UserAlreadyExistException("User already exist");
        }
        unAuthorizedAdminRepo.save(unAuthorizedAdmin);
        return new ResponseEntity<String>("Account Created Successfully!",HttpStatus.CREATED);
    }
}
