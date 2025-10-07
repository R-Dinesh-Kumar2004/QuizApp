package com.project.QuizApp.repository;

import com.project.QuizApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin,String> {
    Optional<Admin> findByEmail(String email);
}
