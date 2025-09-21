package com.project.QuizApp.repository;

import com.project.QuizApp.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findAllByTopic(String topic);

    List<Question> findAllByTopicAfterAndDifficultyLevel(String topic, String difficulty);

    List<Question> findAllByDifficultyLevel(String difficulty);

    @Query(value = "SELECT DISTINCT q.topic FROM Question q",nativeQuery = true)
    List<String> getAllTopic();

    @Query(value = "SELECT * FROM question q WHERE q.topic= :topic ORDER BY RAND()",nativeQuery = true)
    List<Question> findRandomQuestionByTopic(@Param("topic") String topic, Pageable pageable);
}