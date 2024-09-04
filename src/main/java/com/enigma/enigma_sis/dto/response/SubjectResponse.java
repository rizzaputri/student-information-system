package com.enigma.enigma_sis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private String id;
    private String name;
    private Integer lessonsHours;
    private String teacherId;
}
