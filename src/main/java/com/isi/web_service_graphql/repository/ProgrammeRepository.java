package com.isi.web_service_graphql.repository;

import com.isi.web_service_graphql.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
}
