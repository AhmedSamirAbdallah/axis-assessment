package com.fintech.controller;

import com.fintech.common.dto.request.TransactionRequestDto;
import com.fintech.common.dto.response.TransactionResponseDto;
import com.fintech.payload.ApisResponse;
import com.fintech.service.TransactionService;
import com.fintech.utilty.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/accounts")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Deposit funds into an account", description = "Deposits a specified amount into the specified account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit successful", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @PostMapping("/{id}/deposit")
    public ApisResponse<TransactionResponseDto> deposit(@PathVariable Long id, @Valid @RequestBody TransactionRequestDto requestDto) {
        return ApisResponse.success(transactionService.deposit(requestDto), Constants.SuccessMessage.TRANSACTION_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = "Withdraw funds from an account", description = "Withdraws a specified amount from the specified account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdrawal successful", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Insufficient funds", content = @Content)
    })
    @PostMapping("/{id}/withdraw")
    public ApisResponse<TransactionResponseDto> withdraw(@PathVariable Long id, @Valid @RequestBody TransactionRequestDto requestDto) {
        return ApisResponse.success(transactionService.withdraw(requestDto), Constants.SuccessMessage.TRANSACTION_SUCCESSFUL, HttpStatus.OK);
    }
}
