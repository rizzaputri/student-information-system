package com.enigma.enigma_sis.dto.request;

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
