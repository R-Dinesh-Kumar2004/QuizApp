package com.project.QuizApp.service;

import com.project.QuizApp.model.TestHistory;
import com.project.QuizApp.model.User;
import com.project.QuizApp.repository.TestHistoryRepo;
import com.project.QuizApp.repository.UserRepo;
import com.project.QuizApp.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TestHistoryService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TestHistoryRepo testHistoryRepo;

    public ResponseEntity<String> addNewTestHistory(TestHistory testHistory) {
        testHistoryRepo.save(testHistory);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<List<TestHistory>> getAllTestHistory(int id) {
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        List<TestHistory> testHistories = testHistoryRepo.findAllByUserIdOrderByTestDateDesc(id);

        return new ResponseEntity<>(testHistories, HttpStatus.OK);
    }
}