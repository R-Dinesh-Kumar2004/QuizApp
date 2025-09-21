package com.project.QuizApp.controller;

import com.project.QuizApp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        otpService.sendOtp(email);
        return ResponseEntity.ok("OTP sent successfully to " + email);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String email,
                                              @RequestParam String otp) {
        return otpService.validateOtp(email, otp);
    }
}