package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Page<Subject> findAll(Specification<Subject> spec, Pageable pageable);
}
