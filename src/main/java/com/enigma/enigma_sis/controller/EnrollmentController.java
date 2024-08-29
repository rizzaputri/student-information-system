package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.dto.request.EnrollmentRequest;
import com.enigma.enigma_sis.dto.response.EnrollmentResponse;
import com.enigma.enigma_sis.service.EnrollmentService;
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
