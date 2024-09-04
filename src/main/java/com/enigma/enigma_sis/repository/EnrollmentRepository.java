package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    List<Enrollment> findAllByStudentId(String studentId);
}
