package com.project.QuizApp.util;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException(String invalidOtp) {
        super(invalidOtp);
    }
}
