package com.fintech.controller;

import com.fintech.common.dto.request.AccountRequestDto;
import com.fintech.common.dto.response.AccountResponseDto;
import com.fintech.common.dto.response.BalanceResponseDto;
import com.fintech.payload.ApisResponse;
import com.fintech.service.AccountService;
import com.fintech.utilty.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Create a new account", description = "Creates a new account and returns the account details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ApisResponse<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return ApisResponse.success(accountService.createAccount(accountRequestDto), Constants.SuccessMessage.ACCOUNT_CREATED, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve account balance", description = "Retrieves the balance for a specific account by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance retrieved successfully", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @GetMapping("/{id}/balance")
    public ApisResponse<BalanceResponseDto> getBalance(@Parameter(description = "ID of the account to retrieve balance") @PathVariable Long id) {
        return ApisResponse.success(accountService.getBalance(id), Constants.SuccessMessage.BALANCE_RETRIEVED, HttpStatus.OK);
    }
}
