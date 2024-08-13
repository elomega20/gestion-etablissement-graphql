package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
