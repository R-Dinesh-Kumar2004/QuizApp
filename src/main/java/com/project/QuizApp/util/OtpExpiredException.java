package com.project.QuizApp.util;

public class OtpExpiredException extends RuntimeException {
    public OtpExpiredException(String otpExpeired) {
        super(otpExpeired);
    }
}
