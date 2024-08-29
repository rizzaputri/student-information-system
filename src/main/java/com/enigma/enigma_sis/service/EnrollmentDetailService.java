package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.entity.EnrollmentDetail;

import java.util.List;

public interface EnrollmentDetailService {
    List<EnrollmentDetail> createBulk(List<EnrollmentDetail> enrollmentDetails);
}
