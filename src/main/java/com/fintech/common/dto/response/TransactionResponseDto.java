package com.fintech.common.dto.response;

import com.fintech.common.enums.Currency;
import com.fintech.common.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto(
        Long id,
        Long accountId,
        TransactionType transactionType,
        BigDecimal amount,
        Currency currency,
        LocalDateTime transactionDate
) {
}
