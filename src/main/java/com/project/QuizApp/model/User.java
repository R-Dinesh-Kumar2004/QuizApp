package com.project.QuizApp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TestHistory> testHistory;
}