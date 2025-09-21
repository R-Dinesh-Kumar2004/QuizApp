package com.project.QuizApp.controller;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/learn/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/learn/questions/topic/{topic}")
    public ResponseEntity<List<Question>> getAllQuestionsByTopic(@PathVariable String topic) {
        return new ResponseEntity<>(questionService.getAllQuestionsByTopic(topic),HttpStatus.OK);
    }

    @GetMapping("/learn/questions/difficulty/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDifficulty(@PathVariable String difficulty) {
        return new ResponseEntity<>(questionService.getAllQuestionsByDifficulty(difficulty), HttpStatus.OK);
    }

    @GetMapping("/learn/questions/{topic}/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByTopicAndDifficulty(
            @PathVariable String topic,
            @PathVariable String difficulty) {
        return new ResponseEntity<>(questionService.getAllQuestionsByTopicAndDifficulty(topic, difficulty), HttpStatus.OK);
    }

    @GetMapping("/test/questions/topic/{topic}/{numQuestions}")
    public ResponseEntity<List<Question>> getQuestionForTestByTopic(@PathVariable String topic,@PathVariable int numQuestions) {
        return new ResponseEntity<>(questionService.getQuestionForTestByTopic(topic,numQuestions), HttpStatus.OK);
    }

    @GetMapping("/test/questions/mixedQuestions/{numQuestions}")
    public ResponseEntity<List<Question>> getMixedQuestions(@PathVariable int numQuestions) {
        return new ResponseEntity<>(questionService.getMixedQuestion(numQuestions),HttpStatus.OK);
    }
}
