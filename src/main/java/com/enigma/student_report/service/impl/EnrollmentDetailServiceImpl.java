package com.enigma.student_report.service.impl;

import com.enigma.student_report.entity.EnrollmentDetail;
import com.enigma.student_report.repository.EnrollmentDetailRepository;
import com.enigma.student_report.service.EnrollmentDetailService;
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
