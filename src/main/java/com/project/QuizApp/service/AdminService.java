package com.project.QuizApp.service;

import com.project.QuizApp.model.Admin;
import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.UnAuthorizedAdmin;
import com.project.QuizApp.repository.AdminRepo;
import com.project.QuizApp.repository.QuestionRepo;
import com.project.QuizApp.repository.UnAuthorizedAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UnAuthorizedAdminRepo  unAuthorizedAdminRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public String deleteQuestion(int id) {
        return questionRepo.findById(id)
                .map(q ->{
                            questionRepo.delete(q);
                            return "Question Deleted Successfully";
                        }
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Question Not Found"));
    }

    public String updateQuestion(int id, Question updateQuestion) {
        Optional<Question> existingQuestion = questionRepo.findById(id);

        if(existingQuestion.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Question Not Found");
        }

        updateQuestion.setId(existingQuestion.get().getId());
        questionRepo.save(updateQuestion);

        return "Question Updated Successfully";
    }

    public ResponseEntity<List<UnAuthorizedAdmin>> getAllUnAuthorizedAdmins() {
        return new ResponseEntity<>(unAuthorizedAdminRepo.findAll(), HttpStatus.OK);
    }

    public Admin addNewAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public ResponseEntity<List<Admin>> getAllAdmins() {
        return new ResponseEntity<>(adminRepo.findAll(), HttpStatus.OK);
    }
}