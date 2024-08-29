package com.enigma.student_report.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private String id;
    private String studentId;
    private String enrollmentTerm;
    private List<EnrollmentDetailResponse> enrollmentDetails;
}
