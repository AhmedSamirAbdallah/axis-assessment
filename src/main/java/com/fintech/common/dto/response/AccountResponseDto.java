package com.fintech.common.dto.response;

import com.fintech.common.enums.AccountStatus;
import com.fintech.common.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record AccountResponseDto(
        @Schema(description = "The unique identifier of the account", example = "1")
        Long id,

        @Schema(description = "The account number", example = "1234567890")
        String accountNumber,

        @Schema(description = "The current balance of the account", example = "1000.00")
        BigDecimal balance,

        @Schema(description = "The currency of the account", example = "USD")
        Currency currency,

        @Schema(description = "The current status of the account", example = "ACTIVE")
        AccountStatus status
) {
}
