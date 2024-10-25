package com.fintech.common.dto.response;

import com.fintech.common.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record BalanceResponseDto(
        @Schema(description = "The unique identifier of the account", example = "1")
        Long accountId,

        @Schema(description = "The account number", example = "1234567890")
        String accountNumber,

        @Schema(description = "The current balance of the account", example = "1500.75")
        BigDecimal balance,

        @Schema(description = "The currency of the account", example = "EUR")
        Currency currency

) {
}
