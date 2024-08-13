package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
}
