package com.enigma.enigma_sis.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentDetailResponse {
    private String id;
    private String subjectId;
    private Integer lessonsHours;
    private String teacherId;
}
