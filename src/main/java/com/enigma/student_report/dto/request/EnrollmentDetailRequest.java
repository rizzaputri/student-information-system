package com.enigma.student_report.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDetailRequest {
    private String subjectId;
    private String teacherId;
}
