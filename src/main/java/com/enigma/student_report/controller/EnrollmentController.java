package com.enigma.student_report.controller;

import com.enigma.student_report.constant.ApiUrl;
import com.enigma.student_report.dto.request.EnrollmentRequest;
import com.enigma.student_report.dto.response.EnrollmentResponse;
import com.enigma.student_report.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.ENROLLMENT_URL)
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentResponse createNewEnrollment(
            @RequestBody EnrollmentRequest enrollmentRequest
    ) {
        return enrollmentService.create(enrollmentRequest);
    }

    @GetMapping
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.getAll();
    }
}
