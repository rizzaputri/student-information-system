package com.enigma.enigma_sis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String id;
    private String name;
    private String studyGroup;
    private String studentEmail;
    private String userAccountId;
}
