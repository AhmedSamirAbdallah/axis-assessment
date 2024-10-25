package com.fintech.common.dto.request;

import com.fintech.common.enums.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequestDto(
        @NotNull(message = "User ID is required")
        Long userId,

        @DecimalMin(value = "0.0", message = "Balance must be a positive number")
        BigDecimal balance,

        @NotNull(message = "Currency is required")
        Currency currency

) {
}
