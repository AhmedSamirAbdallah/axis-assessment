package com.fintech.common.dto.response;

import com.fintech.common.enums.UserAccountStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDto(
        @Schema(description = "The unique identifier of the user", example = "1")
        Long id,

        @Schema(description = "The user's first name", example = "John")
        String firstName,

        @Schema(description = "The user's last name", example = "Doe")
        String lastName,

        @Schema(description = "The user's email address", example = "john.doe@example.com")
        String email,

        @Schema(description = "The user's Social Security Number (SSN)", example = "123-45-6789")
        String ssn,

        @Schema(description = "The user's phone number", example = "+1234567890")
        String phoneNumber,

        @Schema(description = "The user's date of birth", example = "1980-01-01")
        LocalDate birthDate,

        @Schema(description = "The user's residential address", example = "123 Main St, Springfield, IL")
        String address,

        @Schema(description = "The current account status of the user", example = "ACTIVE")
        UserAccountStatus status,

        @Schema(description = "List of accounts associated with the user")
        List<AccountResponseDto> accountEntityList
) {
}
