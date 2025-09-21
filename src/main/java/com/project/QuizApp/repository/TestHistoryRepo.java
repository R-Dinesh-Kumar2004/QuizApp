package com.project.QuizApp.repository;

import com.project.QuizApp.model.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestHistoryRepo extends JpaRepository<TestHistory,Integer> {
    List<TestHistory> findAllByUserIdOrderByTestDateDesc(int id);
}
