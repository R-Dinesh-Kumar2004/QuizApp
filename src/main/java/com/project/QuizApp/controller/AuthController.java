package com.project.QuizApp.controller;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.model.User;
import com.project.QuizApp.service.AuthService;
import com.project.QuizApp.service.UnAuthorizedAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UnAuthorizedAdminService unAuthorizedAdminService;

    @PostMapping("/add/user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return authService.addUser(user);
    }

    @PostMapping("/add/unAuthorizedAdmin")
    public ResponseEntity<String> addUnAuthorizedAdmin(@RequestBody UnAuthorizedAdmin admin){
        return unAuthorizedAdminService.addAdmin(admin);
    }

    @PostMapping("/login/user")
    public ResponseEntity<Boolean> userlogin(@RequestBody User user){
        return authService.verifyUser(user);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<Boolean> adminLogin(@RequestBody Admin admin){
        return authService.verifyAdmin(admin);
    }
}