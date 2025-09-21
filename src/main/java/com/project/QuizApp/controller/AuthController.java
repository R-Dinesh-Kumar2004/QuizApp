package com.project.QuizApp.controller;

import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.model.User;
import com.project.QuizApp.service.UnAuthorizedAdminService;
import com.project.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UnAuthorizedAdminService unAuthorizedAdminService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/addUnAuthorizedAdmin")
    public ResponseEntity<String> addUnAuthorizedAdmin(@RequestBody UnAuthorizedAdmin admin){
        return unAuthorizedAdminService.addAdmin(admin);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<Boolean> userlogin(@RequestBody User user){
        return userService.verifyUser(user);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<Boolean> adminLogin(@RequestBody User user){
        System.out.println(user.toString());
        return userService.verifyAdmin(user);
    }
}