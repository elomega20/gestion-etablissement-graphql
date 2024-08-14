package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Etudiant;
import com.isi.web_service_graphql.model.Programme;
import com.isi.web_service_graphql.repository.ProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class ProgrammeController {
    private final ProgrammeRepository programmeRepository;

    // obtenir les programmes
    @QueryMapping
    public List<Programme> programmes() {
        return programmeRepository.findAll();
    }

    // obtenir un programme via son Id
    @QueryMapping
    public Programme programme(Long id) {
        return programmeRepository.findById(id).orElseThrow();
    }
    // ajouter un programme
    @MutationMapping
    public Programme ajouterUnProgramme(String nom, String description) {
        Programme programme = new Programme();
        programme.setNom(nom);
        programme.setDescription(description);
        return programmeRepository.save(programme);
    }

    // mettre a jour programme
    @MutationMapping
    public Programme mettreAjourUnProgramme(Long id, String nom, String description) {
        Programme programme = programmeRepository.findById(id).orElseThrow();

        if (nom != null) {
            programme.setNom(nom);
        }
        if (description != null){
            programme.setDescription(description);
        }

        return programmeRepository.save(programme);
    }

    // supprimer un programme
    @MutationMapping
    public Boolean supprimerUnProgramme(Long id) {
        programmeRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Set<Etudiant> etudiants(Programme programme) {
        return programme.getEtudiants();
    }
}
