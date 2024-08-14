package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Cours;
import com.isi.web_service_graphql.model.Etudiant;
import com.isi.web_service_graphql.model.Inscription;
import com.isi.web_service_graphql.repository.CoursRepository;
import com.isi.web_service_graphql.repository.EtudiantRepository;
import com.isi.web_service_graphql.repository.InscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class InscriptionController {
    private final InscriptionRepository inscriptionRepository;
    private final EtudiantRepository etudiantRepository;
    private final CoursRepository coursRepository;

    // pour obtenir les inscriptions
    @QueryMapping
    public List<Inscription> inscriptions() {
        return inscriptionRepository.findAll();
    }

    // pour obtenir un inscription via son Id
    @QueryMapping
    public Inscription inscription(Long id) {
        return inscriptionRepository.findById(id).orElseThrow();
    }

    // pour ajouter un inscription
    @MutationMapping
    public Inscription ajouterUnIncription(Long etudiantId, Long coursId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow();
        Cours cours = coursRepository.findById(coursId).orElseThrow();

        Inscription inscription = new Inscription();
        inscription.setEtudiant(etudiant);
        inscription.setCours(cours);

        return inscriptionRepository.save(inscription);
    }

    // pour supprimer un inscription
    @MutationMapping
    public Boolean supprimerUnIncription(Long id) {
        inscriptionRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Etudiant etudiant(Inscription inscription) {
        return inscription.getEtudiant();
    }

    @SchemaMapping
    public Cours course(Inscription inscription) {
        return inscription.getCours();
    }
}
