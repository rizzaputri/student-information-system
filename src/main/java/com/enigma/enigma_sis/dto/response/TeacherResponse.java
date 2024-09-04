package com.enigma.enigma_sis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private String id;
    private String name;
    private Boolean status;
    private String teacherEmail;
    private String userAccountId;
}
