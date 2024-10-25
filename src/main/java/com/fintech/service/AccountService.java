package com.fintech.service;

import com.fintech.common.dto.request.AccountRequestDto;
import com.fintech.common.dto.response.AccountResponseDto;
import com.fintech.common.dto.response.BalanceResponseDto;

public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto requestDto);

    BalanceResponseDto getBalance(Long id);
}
