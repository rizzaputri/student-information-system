package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String id;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String name;

    @NotNull(message = ConstantMessage.NOT_BLANK_VALIDATION)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String studyGroup;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    @Pattern(regexp = "^(?:\\+62|62|0)[2-9]\\d{7,11}$", message = ConstantMessage.MOBILE_PHONE_VALIDATION)
    private String mobilePhone;
}
