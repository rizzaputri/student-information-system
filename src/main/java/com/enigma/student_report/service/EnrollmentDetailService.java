package com.enigma.student_report.service;

import com.enigma.student_report.entity.EnrollmentDetail;

import java.util.List;

public interface EnrollmentDetailService {
    List<EnrollmentDetail> createBulk(List<EnrollmentDetail> enrollmentDetails);
}
