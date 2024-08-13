package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Long> {
}
