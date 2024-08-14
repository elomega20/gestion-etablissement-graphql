package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.Cours;
import com.isi.web_service_graphql.model.Enseignant;
import com.isi.web_service_graphql.repository.EnseignantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class EnseignantController {
    private final EnseignantRepository enseignantRepository;

    // obtenir tout les enseignants
    @QueryMapping
    public List<Enseignant> enseignants() {
        return enseignantRepository.findAll();
    }

    // obtenir un enseignant via son Id
    @QueryMapping
    public Enseignant enseignant(Long id) {
        return enseignantRepository.findById(id).orElseThrow();
    }

    // ajouter un enseignant
    @MutationMapping
    public Enseignant ajouterUnEnseignant(String nom, String prenom, String email) {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        return enseignantRepository.save(enseignant);
    }

    // mettre a jour un enseignant
    @MutationMapping
    public Enseignant mettreAjourUnEnseignant(Long id, String nom, String prenom, String email) {
        Enseignant enseignant = enseignantRepository.findById(id).orElseThrow();

        if (nom != null){
            enseignant.setNom(nom);
        }
        if (prenom != null){
            enseignant.setPrenom(prenom);
        }
        if (email != null){
            enseignant.setEmail(email);
        }

        return enseignantRepository.save(enseignant);
    }

    // supprimer un enseignant
    @MutationMapping
    public Boolean supprimerUnEnseignant(Long id) {
        enseignantRepository.deleteById(id);
        return true;
    }

    @SchemaMapping
    public Set<Cours> cours(Enseignant enseignant) {
        return enseignant.getCours();
    }
}
