package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}
