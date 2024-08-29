package com.enigma.student_report.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    private String studentId;
    private String enrollmentTerm;
    private List<EnrollmentDetailRequest> enrollmentDetails;
}
