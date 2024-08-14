package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Etudiant;
import com.isi.web_service_graphql.model.Programme;
import com.isi.web_service_graphql.repository.EtudiantRepository;
import com.isi.web_service_graphql.repository.ProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class EtudiantController {

    private final EtudiantRepository etudiantRepository;
    private final ProgrammeRepository programmeRepository;

    @QueryMapping
    public List<Etudiant> etudiants() {
        return etudiantRepository.findAll();
    }

    @QueryMapping
    public Etudiant etudiant(Long id) {
        return etudiantRepository.findById(id).orElseThrow();
    }

    @MutationMapping
    public Etudiant ajouterUnEtudiant(String nom, String prenom, String email, Long programmeId) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);

        if (programmeId != null) {
            Programme programme = programmeRepository.findById(programmeId).orElseThrow();
            etudiant.setProgramme(programme);
        }

        return etudiantRepository.save(etudiant);
    }

    @MutationMapping
    public Etudiant mettreAjourUnEtudiant(Long id, String nom, String prenom, String email, Long programmeId) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();

        if (nom != null){
            etudiant.setNom(nom);
        }
        if (prenom != null){
            etudiant.setPrenom(prenom);
        }
        if (email != null){
            etudiant.setEmail(email);
        }

        if (programmeId != null) {
            Programme programme = programmeRepository.findById(programmeId).orElseThrow();
            etudiant.setProgramme(programme);
        }

        return etudiantRepository.save(etudiant);
    }

    @MutationMapping
    public Boolean supprimerUnEtudiant(Long id) {
        etudiantRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Programme programme(Etudiant etudiant) {
        return etudiant.getProgramme();
    }
}
