package com.project.QuizApp.repository;

import com.project.QuizApp.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OtpVerification, String> {}
