package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDetailRequest {
    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String subjectId;
}
