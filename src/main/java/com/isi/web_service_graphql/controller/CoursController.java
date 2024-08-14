package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Cours;
import com.isi.web_service_graphql.model.Departement;
import com.isi.web_service_graphql.model.Enseignant;
import com.isi.web_service_graphql.repository.CoursRepository;
import com.isi.web_service_graphql.repository.DepartementRepository;
import com.isi.web_service_graphql.repository.EnseignantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CoursController {

    private final CoursRepository coursRepository;
    private final DepartementRepository departementRepository;
    private final EnseignantRepository enseignantRepository;

    // pour abtenir tout les cours
    @QueryMapping
    public List<Cours> courss() {
        return coursRepository.findAll();
    }

    // pour obtenir un cours via son Id
    @QueryMapping
    public Cours cours(Long id) {
        return coursRepository.findById(id).orElseThrow();
    }
    // pour ajouter un cours
    @MutationMapping
    public Cours ajouterUnCours(String nom, String description, Long departementId, Long enseignantId) {
        Departement departement = departementRepository.findById(departementId).orElseThrow();
        Enseignant enseignant = enseignantRepository.findById(enseignantId).orElseThrow();

        Cours course = new Cours();
        course.setNom(nom);
        course.setDescription(description);
        course.setDepartement(departement);
        course.setEnseignant(enseignant);

        return coursRepository.save(course);
    }

    // pour mettre a jour un cours
    @MutationMapping
    public Cours mettreAjourUnCours(Long id, String nom, String description, Long departementId, Long enseignantId) {
        Cours cours = coursRepository.findById(id).orElseThrow();

        if (nom != null){
            cours.setNom(nom);
        }
        if (description != null){
            cours.setDescription(description);
        }

        if (departementId != null) {
            Departement department = departementRepository.findById(departementId).orElseThrow();
            cours.setDepartement(department);
        }

        if (enseignantId != null) {
            Enseignant enseignant = enseignantRepository.findById(enseignantId).orElseThrow();
            cours.setEnseignant(enseignant);
        }

        return coursRepository.save(cours);
    }

    // pour supprimer un cours
    @MutationMapping
    public Boolean supprimerUnCours(Long id) {
        coursRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Departement departement(Cours cours) {
        return cours.getDepartement();
    }

    @SchemaMapping
    public Enseignant enseignant(Cours cours) {
        return cours.getEnseignant();
    }
}
