package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
}
