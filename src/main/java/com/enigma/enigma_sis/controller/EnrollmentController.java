package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.EnrollmentRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.dto.response.EnrollmentResponse;
import com.enigma.enigma_sis.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.ENROLLMENT_URL)
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<CommonResponse<EnrollmentResponse>> createNewEnrollment(
            @RequestBody EnrollmentRequest enrollmentRequest
    ) {
        EnrollmentResponse enrollment = enrollmentService.create(enrollmentRequest);
        CommonResponse<EnrollmentResponse> response = CommonResponse
                .<EnrollmentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.INPUT_SUCCESS + "enrollments")
                .data(enrollment)
                .paging(null)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<List<EnrollmentResponse>>> getAllEnrollments(
            @PathVariable String id
    ) {
        List<EnrollmentResponse> enrollments = enrollmentService.getAll(id);
        CommonResponse<List<EnrollmentResponse>> response = CommonResponse
                .<List<EnrollmentResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCESS + "all enrollments")
                .data(enrollments)
                .paging(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
