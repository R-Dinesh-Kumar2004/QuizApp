package com.project.QuizApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;
    private double scorePercentage;
    private int correctAnswer;
    private int totalQuestion;
    private LocalDate testDate;

    @ManyToOne
    private User user;
}