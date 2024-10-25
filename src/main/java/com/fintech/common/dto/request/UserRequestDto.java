package com.fintech.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequestDto(
        @NotBlank(message = "First name is required")
        @Schema(description = "The first name of the user", example = "John")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Schema(description = "The last name of the user", example = "Doe")
        String lastName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        @Schema(description = "The user's email address", example = "john.doe@example.com")
        String email,

        @NotBlank(message = "SSN is required")
        @Schema(description = "The user's Social Security Number", example = "123-45-6789")
        String ssn,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid according to international formats")
        @Schema(description = "The user's phone number", example = "+1234567890")
        String phoneNumber,

        @Schema(description = "The user's date of birth", example = "1980-01-01")
        LocalDate birthDate,

        @Schema(description = "The user's address", example = "123 Main St, Anytown, USA")
        String address,

        @NotBlank(message = "Security answer is required")
        @Schema(description = "The answer to the security question", example = "My first pet's name")
        String securityAnswer
) {
}
