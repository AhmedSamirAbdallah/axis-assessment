package com.fintech.common.dto.request;

import com.fintech.common.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequestDto(
        @NotNull(message = "Account ID is required")
        @Schema(description = "The ID of the account for the transaction", example = "1")
        Long accountId,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
        @Schema(description = "The amount to deposit or withdraw", example = "50.00")
        BigDecimal amount,

        @NotNull(message = "Currency is required")
        @Schema(description = "The currency of the transaction", example = "USD")
        Currency currency
) {
}
