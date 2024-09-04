package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Page<Student> findAll(Specification<Student> spec, Pageable pageable);
}
