package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String username;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String password;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    @Pattern(regexp = ".*@(student|teacher)\\.enigma\\.ac\\.id$", message = ConstantMessage.EMAIL_VALIDATION)
    private String email;
}
