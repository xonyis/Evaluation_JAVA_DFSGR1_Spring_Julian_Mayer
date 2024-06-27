package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.QuestionDao;
import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.dao.ReponseUtilisateurDao;
import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.model.Question;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.model.ReponseUtilisateur;
import edu.fbansept.demo.model.Utilisateur;
import edu.fbansept.demo.security.IsAdmin;
import edu.fbansept.demo.security.IsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponseUtilisateur")
public class ReponseUtilisateurController {

    @Autowired
    ReponseUtilisateurDao reponseUtilisateurDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @GetMapping
    public List<ReponseUtilisateur> getAll() {
        return reponseUtilisateurDao.findAll();
    }

    @IsUser
    @PostMapping("/reponsePossible/{reponseId}/utilisateur/{utilisateurId}")
    public ResponseEntity<ReponseUtilisateur> create(@RequestBody ReponseUtilisateur reponseUtilisateur,
                                                     @PathVariable int reponseId,
                                                     @PathVariable int utilisateurId) {

        Optional<Utilisateur> utilisateur = utilisateurDao.findById(utilisateurId);
        Optional<ReponsePossible> reponsePossible = reponsePossibleDao.findById(reponseId);

        if(utilisateur.isEmpty() && reponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            reponseUtilisateur.setUtilisateur(utilisateur.get());
            reponseUtilisateur.setReponsePossible(reponsePossible.get());
            reponseUtilisateurDao.save(reponseUtilisateur);
        }

        return new ResponseEntity<>(reponseUtilisateur, HttpStatus.CREATED);
    }
}
