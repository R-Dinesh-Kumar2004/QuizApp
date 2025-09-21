package com.project.QuizApp.service;

import com.project.QuizApp.model.OtpVerification;
import com.project.QuizApp.repository.OtpRepo;
import com.project.QuizApp.util.InvalidOtpException;
import com.project.QuizApp.util.OtpExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepo otpRepo;

    public void sendOtp(String email) {
        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // 6 digits

        // OTP valid for 15 mins
        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(15));
        otpRepo.save(otpEntity);

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + " (valid for 15 minutes)");
        mailSender.send(message);
    }

    public ResponseEntity<String> validateOtp(String email, String enteredOtp) {
        Optional<OtpVerification> record = otpRepo.findById(email);
        OtpVerification otpData = record.get();

        if (otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpRepo.deleteById(email);
            throw new OtpExpiredException("OTP expired");
        }

        boolean isValid = otpData.getOtp().equals(enteredOtp);
        if (isValid) otpRepo.deleteById(email); // remove after success
        else{
            otpRepo.deleteById(email);
            throw new InvalidOtpException("Invalid OTP");
        }
        return new ResponseEntity<>("OTP verified", HttpStatus.OK);
    }
}
