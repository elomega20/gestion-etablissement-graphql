package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
