package com.project.QuizApp.controller;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.TestHistory;
import com.project.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/learn/questions/topic/{topic}")
    public ResponseEntity<List<Question>> getAllQuestionsByTopic(@PathVariable String topic) {
        return new ResponseEntity<>(userService.getAllQuestionsByTopic(topic),HttpStatus.OK);
    }

    @GetMapping("/test/questions/topic/{topic}/{numQuestions}")
    public ResponseEntity<List<Question>> getQuestionForTestByTopic(@PathVariable String topic,@PathVariable int numQuestions) {
        return new ResponseEntity<>(userService.getQuestionForTestByTopic(topic,numQuestions), HttpStatus.OK);
    }

    @GetMapping("/test/mixedQuestions")
    public ResponseEntity<List<Question>> getMixedQuestion() {
        return new ResponseEntity<>(userService.getMixedQuestion(),HttpStatus.OK);
    }

    @GetMapping("/testHistory")
    public ResponseEntity<List<TestHistory>> testHistory(Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(userService.getAllTestHistory(email),HttpStatus.OK);
    }

    @PutMapping("/addNewTestScore")
    public ResponseEntity<Boolean> addNewTestScore(@RequestBody TestHistory testHistory){
        return userService.addNewTestScore(testHistory);
    }

    public ResponseEntity<List<String>> getAllTopic(){
        return userService.getAllTopic();
    }
}