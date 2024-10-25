package com.fintech.service;

import com.fintech.common.dto.request.TransactionRequestDto;
import com.fintech.common.dto.response.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto deposit(TransactionRequestDto requestDto);

    TransactionResponseDto withdraw(TransactionRequestDto requestDto);
}
