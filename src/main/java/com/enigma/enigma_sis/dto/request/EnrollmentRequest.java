package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String studentId;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String enrollmentTerm;

    private List<EnrollmentDetailRequest> enrollmentDetails;
}
