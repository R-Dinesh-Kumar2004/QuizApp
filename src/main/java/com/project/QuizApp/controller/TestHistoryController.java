package com.project.QuizApp.controller;

import com.project.QuizApp.model.TestHistory;
import com.project.QuizApp.service.TestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testHistory")
public class TestHistoryController {

    @Autowired
    TestHistoryService testHistoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addHistory(@RequestBody TestHistory testHistory) {
        return testHistoryService.addNewTestHistory(testHistory);
    }

    @GetMapping("/getTestHistory/{id}")
    public ResponseEntity<List<TestHistory>> getTestHistory(@PathVariable int id){
        return testHistoryService.getAllTestHistory(id);
    }
}
