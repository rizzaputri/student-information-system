package com.enigma.enigma_sis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private String id;
    private String name;
    private String studyGroup;
    private String mobilePhone;
    private String studentEmail;
}
