package com.fintech.common.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequestDto(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "SSN is required")
        String ssn,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid according to international formats")
        String phoneNumber,

        LocalDate birthDate,

        String address,

        @NotBlank(message = "Security answer is required")
        String securityAnswer

) {
}
