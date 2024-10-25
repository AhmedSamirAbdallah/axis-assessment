package com.fintech.common.dto.response;

import com.fintech.common.enums.Currency;

import java.math.BigDecimal;

public record BalanceResponseDto(
        Long accountId,
        String accountNumber,
        BigDecimal balance,
        Currency currency

) {
}
