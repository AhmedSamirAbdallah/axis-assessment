package com.fintech.service.Impl;

import com.fintech.common.dto.request.TransactionRequestDto;
import com.fintech.common.dto.response.TransactionResponseDto;
import com.fintech.common.entity.AccountEntity;
import com.fintech.common.entity.TransactionEntity;
import com.fintech.common.enums.TransactionType;
import com.fintech.repository.AccountRepository;
import com.fintech.repository.TransactionRepository;
import com.fintech.service.TransactionService;
import com.fintech.utilty.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private void validateTransactionRequest(TransactionRequestDto requestDto) {
        if (requestDto.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(Constants.ErrorMessage.AMOUNT_MUST_BE_POSITIVE);
        }

        if (requestDto.accountId() == null) {
            throw new IllegalArgumentException(Constants.ErrorMessage.ACCOUNT_ID_REQUIRED);
        }
    }

    private AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.ErrorMessage.ACCOUNT_NOT_FOUND + id));
    }

    private TransactionResponseDto mapToDto(TransactionEntity transaction) {
        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getAccount().getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getTransactionDate()
        );
    }


    @Override
    @Transactional
    public TransactionResponseDto deposit(TransactionRequestDto requestDto) {

        validateTransactionRequest(requestDto);

        AccountEntity account = getAccountById(requestDto.accountId());

        if (!account.getCurrency().equals(requestDto.currency())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.CURRENCY_MISMATCH);
        }

        TransactionEntity transaction = TransactionEntity.builder()
                .amount(requestDto.amount())
                .currency(requestDto.currency())
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.DEPOSIT)
                .account(account)
                .build();

        transaction = transactionRepository.save(transaction);

        account.setBalance(account.getBalance().add(transaction.getAmount()));
        accountRepository.save(account);

        return mapToDto(transaction);
    }

    @Override
    @Transactional
    public TransactionResponseDto withdraw(TransactionRequestDto requestDto) {
        validateTransactionRequest(requestDto);

        AccountEntity account = getAccountById(requestDto.accountId());

        if (account.getBalance().compareTo(requestDto.amount()) < 0) {
            throw new IllegalArgumentException(Constants.ErrorMessage.INSUFFICIENT_FUNDS);
        }

        if (!account.getCurrency().equals(requestDto.currency())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.CURRENCY_MISMATCH);
        }

        TransactionEntity transaction = TransactionEntity.builder()
                .amount(requestDto.amount())
                .currency(requestDto.currency())
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.WITHDRAWAL)
                .account(account)
                .build();

        transaction = transactionRepository.save(transaction);

        account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        accountRepository.save(account);

        return mapToDto(transaction);
    }
}
