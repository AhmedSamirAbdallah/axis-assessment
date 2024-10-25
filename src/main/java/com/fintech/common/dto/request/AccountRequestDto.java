package com.fintech.common.dto.request;

import com.fintech.common.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequestDto(
        @NotNull(message = "User ID is required")
        @Schema(description = "The ID of the user associated with the account", example = "1")
        Long userId,

        @DecimalMin(value = "0.0", message = "Balance must be a positive number")
        @Schema(description = "The initial balance of the account", example = "100.00")
        BigDecimal balance,

        @NotNull(message = "Currency is required")
        @Schema(description = "The currency of the account", example = "USD")
        Currency currency
) {
}
