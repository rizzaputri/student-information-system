package com.enigma.enigma_sis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewStudentRequest {
    private String name;
    private String mobilePhone;
}
