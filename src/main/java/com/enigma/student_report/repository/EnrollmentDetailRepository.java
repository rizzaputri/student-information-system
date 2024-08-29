package com.enigma.student_report.repository;

import com.enigma.student_report.entity.EnrollmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, String> {
}
