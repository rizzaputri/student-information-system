package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.entity.EnrollmentDetail;
import com.enigma.enigma_sis.repository.EnrollmentDetailRepository;
import com.enigma.enigma_sis.service.EnrollmentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentDetailServiceImpl implements EnrollmentDetailService {
    private final EnrollmentDetailRepository enrollmentDetailRepository;

    @Override
    public List<EnrollmentDetail> createBulk(List<EnrollmentDetail> enrollmentDetails) {
        return enrollmentDetailRepository.saveAllAndFlush(enrollmentDetails);
    }
}
