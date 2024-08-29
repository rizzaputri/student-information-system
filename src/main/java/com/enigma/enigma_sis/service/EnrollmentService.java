package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.EnrollmentRequest;
import com.enigma.enigma_sis.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);
    List<EnrollmentResponse> getAll();
}
