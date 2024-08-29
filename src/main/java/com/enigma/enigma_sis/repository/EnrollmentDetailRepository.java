package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.EnrollmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, String> {
}
