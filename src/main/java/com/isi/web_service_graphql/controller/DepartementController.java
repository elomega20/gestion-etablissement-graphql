package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Cours;
import com.isi.web_service_graphql.model.Departement;
import com.isi.web_service_graphql.model.Enseignant;
import com.isi.web_service_graphql.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class DepartementController {

    private final DepartementRepository departementRepository;

    // pour obtenir les departements
    @QueryMapping
    public List<Departement> departements() {
        return departementRepository.findAll();
    }

    // pour obtenir un departement via son Id
    @QueryMapping
    public Departement departement(Long id) {
        return departementRepository.findById(id).orElseThrow();
    }

    // pour ajouter un departement
    @MutationMapping
    public Departement ajouterUnDepartement(String nom) {
        Departement departement = new Departement();
        departement.setNom(nom);
        return departementRepository.save(departement);
    }

    // pour mettre a jour un departement
    @MutationMapping
    public Departement mettreAjourUnDepartment(Long id, String nom) {
        Departement departement = departementRepository.findById(id).orElseThrow();

        if (nom != null){
            departement.setNom(nom);
        }

        return departementRepository.save(departement);
    }

    // pour supprimer un departement
    @MutationMapping
    public Boolean supprimerUnDepartment(Long id) {
        departementRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Set<Cours> departements(Departement departement) {
        return departement.getCours();
    }

    @SchemaMapping
    public Set<Enseignant> enseignants(Departement departement) {
        return departement.getEnseignant();
    }
}
