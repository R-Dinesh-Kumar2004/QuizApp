package com.project.QuizApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UnAuthorizedAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String userEmail;
    private String username;
    private String password;
}
