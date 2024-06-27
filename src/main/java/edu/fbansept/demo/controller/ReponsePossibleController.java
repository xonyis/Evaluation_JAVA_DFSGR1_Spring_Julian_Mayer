package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.QuestionDao;
import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.model.Question;
import edu.fbansept.demo.model.Quizz;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.security.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponsePossible")
public class ReponsePossibleController {
    @Autowired
    ReponsePossibleDao repository;

    @Autowired
    QuestionDao questionDao;

    @GetMapping
    public List<ReponsePossible> getAll() {
        return repository.findAll();
    }

    @IsAdmin
    @PostMapping("/question/{questionId}")
    public ResponseEntity<ReponsePossible> create(@PathVariable int questionId ,@RequestBody ReponsePossible reponsePossible ) {
        Optional<Question> question = questionDao.findById(questionId);
        if(question.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            reponsePossible.setTexte(reponsePossible.getTexte());
            reponsePossible.setIsVrai(reponsePossible.getIsVrai());
            reponsePossible.setQuestion(question.get());
            repository.save(reponsePossible);
        }

        return new ResponseEntity<>(reponsePossible, HttpStatus.CREATED);
    }
}
