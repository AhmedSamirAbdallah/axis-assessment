package com.fintech.common.dto.response;

import java.math.BigDecimal;

public record AccountResponseDto(
        Long id,
        String accountNumber,
        BigDecimal balance
) {
}
