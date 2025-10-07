package com.project.QuizApp.controller;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/question")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return new ResponseEntity<>(adminService.getAllQuestions(),HttpStatus.OK);
    }

    @PostMapping("/questions/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(adminService.addQuestion(question), HttpStatus.OK);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<String> updateQuestion(
            @PathVariable int id,
            @RequestBody Question updatedQuestion) {
        String response = adminService.updateQuestion(id, updatedQuestion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/questions/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        String response = adminService.deleteQuestion(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.addNewAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/unAuthorizedAdmin")
    public ResponseEntity<List<UnAuthorizedAdmin>>  getAllUnAuthorizedAdmins() {
        return adminService.getAllUnAuthorizedAdmins();
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}