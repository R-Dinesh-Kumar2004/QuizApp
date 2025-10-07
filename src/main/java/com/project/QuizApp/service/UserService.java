package com.project.QuizApp.service;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.TestHistory;
import com.project.QuizApp.model.User;
import com.project.QuizApp.repository.QuestionRepo;
import com.project.QuizApp.repository.UserRepo;
import com.project.QuizApp.util.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    UserRepo userRepo;

    public List<Question> getAllQuestionsByTopic(String topic) {
        return questionRepo.findAllByTopic(topic);
    }

    public List<Question> getQuestionForTestByTopic(String topic,int numQuestions) {
        return questionRepo.findRandomQuestionByTopic(topic, (Pageable) PageRequest.of(0,numQuestions));
    }

    public List<Question> getMixedQuestion() {
        List<String> allTopic = questionRepo.getAllTopic();

        int limit = 5;

        List<Question> result = new ArrayList<>();

        for(String topic : allTopic){
            result.addAll(getQuestionForTestByTopic(topic,limit));
        }

        Random random = new Random();
        Collections.shuffle(result,random);

        return result;
    }

    public List<TestHistory> getAllTestHistory(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            List<TestHistory> result = user.get().getTestHistory().stream()
                    .sorted(Comparator.comparing(TestHistory::getTestDate).reversed())
                    .collect(Collectors.toList());

            return result;
        }
        else throw new InternalServerErrorException("User Not found with email "+email);
    }

    public ResponseEntity<Boolean> addNewTestScore(TestHistory testHistory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> user = userRepo.findByEmail(email);

        if(user.isPresent()){
            user.get().getTestHistory().add(testHistory);
            return ResponseEntity.ok(true);
        }
        else throw new InternalServerErrorException("User Not found with email "+email);
    }

    public ResponseEntity<List<String>> getAllTopic() {
        return new ResponseEntity<>(questionRepo.getAllTopic() ,HttpStatus.OK);
    }
}