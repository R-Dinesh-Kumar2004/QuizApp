package com.project.QuizApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class OtpVerification {
    @Id
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
}

