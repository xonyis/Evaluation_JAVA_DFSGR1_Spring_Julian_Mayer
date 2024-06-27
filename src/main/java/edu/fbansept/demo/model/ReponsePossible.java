package edu.fbansept.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReponsePossible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotBlank
    @NotNull
    protected String texte;


    protected Boolean isVrai;

    @ManyToOne
    protected Question question;
}
