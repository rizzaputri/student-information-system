package com.enigma.enigma_sis.dto.request;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.entity.Subject;
import com.enigma.enigma_sis.entity.UserAccount;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherRequest {
    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String id;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    private String name;

    @NotBlank(message = ConstantMessage.NOT_BLANK_VALIDATION)
    @Pattern(regexp = "^(?:\\+62|62|0)[2-9]\\d{7,11}$", message = ConstantMessage.MOBILE_PHONE_VALIDATION)
    private String mobilePhone;
}
