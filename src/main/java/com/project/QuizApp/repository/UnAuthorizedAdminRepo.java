package com.project.QuizApp.repository;

import com.project.QuizApp.model.UnAuthorizedAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnAuthorizedAdminRepo extends JpaRepository<UnAuthorizedAdmin, Integer> {
    Optional<UnAuthorizedAdmin> findByEmail(String email);
}