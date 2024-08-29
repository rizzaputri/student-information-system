package com.enigma.student_report.service;

import com.enigma.student_report.dto.request.EnrollmentRequest;
import com.enigma.student_report.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);
    List<EnrollmentResponse> getAll();
}
