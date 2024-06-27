package edu.fbansept.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReponseUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private ReponsePossible reponsePossible;
}
