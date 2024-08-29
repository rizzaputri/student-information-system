package com.enigma.student_report.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewSubjectRequest {
    private String name;
    private Integer lessonsHours;
    private String teacherId;
}
