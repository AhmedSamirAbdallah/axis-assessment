package com.fintech.common.dto.response;

import com.fintech.common.enums.UserAccountStatus;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String ssn,
        String phoneNumber,
        LocalDate birthDate,
        String address,
        UserAccountStatus status,
        List<AccountResponseDto> accountEntityList
) {
}
