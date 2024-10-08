package com.isi.web_service_graphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class SalleDeClasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String localisation;

    @OneToMany(mappedBy = "salleDeClasse")
    private Set<Cours> cours;
}
