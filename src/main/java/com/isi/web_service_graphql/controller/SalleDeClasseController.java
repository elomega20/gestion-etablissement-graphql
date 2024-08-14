package com.isi.web_service_graphql.controller;

import com.isi.web_service_graphql.model.SalleDeClasse;
import com.isi.web_service_graphql.repository.SalleDeClasseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SalleDeClasseController {
    private final SalleDeClasseRepository salleDeClasseRepository;

    // obtenir tout les salles de classe
    @QueryMapping
    public List<SalleDeClasse> salledeclasses() {
        return salleDeClasseRepository.findAll();
    }

    // obtenir une salle de classe via son Id
    @QueryMapping
    public SalleDeClasse salledeclasse(Long id) {
        return salleDeClasseRepository.findById(id).orElseThrow();
    }

    // ajouter une salle de classe
    @MutationMapping
    public SalleDeClasse ajouterUneSalleDeClasse(String nom, String localisation) {
        SalleDeClasse salleDeClasse = new SalleDeClasse();
        salleDeClasse.setNom(nom);
        salleDeClasse.setLocalisation(localisation);
        return salleDeClasseRepository.save(salleDeClasse);
    }

    // mettre a jour une salle de classe
    @MutationMapping
    public SalleDeClasse mettreAjourUneSalleDeClasse(Long id, String nom, String localisation) {
        SalleDeClasse salleDeClasse = salleDeClasseRepository.findById(id).orElseThrow();

        if (nom != null){
            salleDeClasse.setNom(nom);
        }
        if (localisation != null){
            salleDeClasse.setLocalisation(nom);
        }

        return salleDeClasseRepository.save(salleDeClasse);
    }

    // supprimer une salle de classe
    @MutationMapping
    public Boolean supprimerUneSalleDeClasse(Long id) {
        salleDeClasseRepository.deleteById(id);
        return true;
    }
}
