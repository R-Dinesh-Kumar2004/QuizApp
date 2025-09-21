package com.project.QuizApp.controller;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/questions/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<String> updateQuestion(
            @PathVariable int id,
            @RequestBody Question updatedQuestion) {
        String response = questionService.updateQuestion(id, updatedQuestion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/questions/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        String response = questionService.deleteQuestion(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
