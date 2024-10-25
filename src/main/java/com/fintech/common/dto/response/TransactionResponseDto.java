package com.fintech.common.dto.response;

import com.fintech.common.enums.Currency;
import com.fintech.common.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto(
        @Schema(description = "The unique identifier of the transaction", example = "1")
        Long id,

        @Schema(description = "The unique identifier of the associated account", example = "1001")
        Long accountId,

        @Schema(description = "The type of the transaction (e.g., DEPOSIT, WITHDRAWAL)", example = "DEPOSIT")
        TransactionType transactionType,

        @Schema(description = "The amount involved in the transaction", example = "200.00")
        BigDecimal amount,

        @Schema(description = "The currency of the transaction", example = "USD")
        Currency currency,

        @Schema(description = "The date and time when the transaction occurred", example = "2023-10-25T10:15:30")
        LocalDateTime transactionDate
) {
}
