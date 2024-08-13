package com.isi.web_service_graphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @OneToMany(mappedBy = "etudiant")
    private Set<Inscription> inscriptions;
}
