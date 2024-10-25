package com.fintech.common.dto.response;

import com.fintech.common.enums.AccountStatus;
import com.fintech.common.enums.Currency;

import java.math.BigDecimal;

public record AccountResponseDto(
        Long id,
        String accountNumber,
        BigDecimal balance,
        Currency currency,
        AccountStatus status
) {
}
