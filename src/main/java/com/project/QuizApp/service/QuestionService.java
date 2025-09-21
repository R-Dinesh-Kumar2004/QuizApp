package com.project.QuizApp.service;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByTopic(String topic) {
        return questionRepo.findAllByTopic(topic);
    }

    public List<Question> getAllQuestionsByTopicAndDifficulty(String topic, String difficulty) {
        return questionRepo.findAllByTopicAfterAndDifficultyLevel(topic,difficulty);
    }

    public List<Question> getAllQuestionsByDifficulty(String difficulty) {
        return questionRepo.findAllByDifficultyLevel(difficulty);
    }

    public List<Question> getQuestionForTestByTopic(String topic,int numQuestions) {
        return questionRepo.findRandomQuestionByTopic(topic, (Pageable) PageRequest.of(0,numQuestions));
    }

    public List<Question> getMixedQuestion(int numQuestions) {
        List<String> allTopic = questionRepo.getAllTopic();

        int limit = (int)Math.ceil((double)numQuestions/allTopic.size());

        List<Question> result = new ArrayList<>();

        for(String topic : allTopic){
            result.addAll(getQuestionForTestByTopic(topic,limit));
        }

        Random random = new Random();
        Collections.shuffle(result,random);

        return result;
    }


    // handle exception globally

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
}