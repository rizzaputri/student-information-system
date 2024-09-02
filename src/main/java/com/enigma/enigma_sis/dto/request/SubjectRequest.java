package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequest {
    private String id;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String name;

    @NotNull(message = ConstantMessage.NOT_BLANK_VALIDATION)
    @Min(value = 1, message = ConstantMessage.GREATER_THAN_VALIDATION)
    private Integer lessonsHours;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String teacherId;
}
