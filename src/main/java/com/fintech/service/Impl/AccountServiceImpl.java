package com.fintech.service.Impl;

import com.fintech.common.dto.request.AccountRequestDto;
import com.fintech.common.dto.response.AccountResponseDto;
import com.fintech.common.dto.response.BalanceResponseDto;
import com.fintech.common.entity.AccountEntity;
import com.fintech.common.entity.UserEntity;
import com.fintech.common.enums.AccountStatus;
import com.fintech.repository.AccountRepository;
import com.fintech.repository.UserRepository;
import com.fintech.service.AccountService;
import com.fintech.utilty.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private void validateAccountRequest(AccountRequestDto requestDto) {
        if (requestDto.balance() == null || requestDto.balance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(Constants.ErrorMessage.BALANCE_POSITIVE);
        }

        if (requestDto.currency() == null) {
            throw new IllegalArgumentException(Constants.ErrorMessage.CURRENCY_REQUIRED);
        }

        if (requestDto.userId() == null) {
            throw new IllegalArgumentException(Constants.ErrorMessage.USER_ID_REQUIRED);
        }

        if (!userRepository.existsById(requestDto.userId())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.USER_NOT_FOUND + requestDto.userId());
        }
    }


    private UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format(Constants.ErrorMessage.USER_NOT_FOUND, id)));
    }

    private AccountResponseDto mapToDto(AccountEntity account) {
        return new AccountResponseDto(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus()
        );
    }


    private String generateAccountNumber(UserEntity user) {
        //<USER_ID>-<INITIALS>-<SSN_LAST_FOUR>-<CURRENT_TIMESTAMP>-<RANDOM_CHAR>

        //<USER_ID>: Unique identifier for the user (e.g., 12345).
        //<INITIALS>: First letter of the first name and last name combined (e.g., JD for John Doe).
        //<SSN_LAST_FOUR>: The last four digits of the SSN (e.g., 6789).
        //<CURRENT_TIMESTAMP>: A numeric representation of the current time (e.g., 1692953567000).
        //<RANDOM_CHAR>: A string of random characters for uniqueness (e.g., ABC).

        String userId = String.valueOf(user.getId());
        String initials = getInitials(user.getFirstName(), user.getLastName());

        String ssnLastFour = user.getSsn().substring(user.getSsn().length() - 4);

        String currentTimestamp = String.valueOf(System.currentTimeMillis());

        String randomSuffix = generateRandomSuffix(3);

        return String.format("%s-%s-%s-%s-%s", userId, initials, ssnLastFour, currentTimestamp, randomSuffix);
    }

    private String getInitials(String firstName, String lastName) {
        return (firstName.charAt(0) + "" + lastName.charAt(0)).toUpperCase();
    }

    private String generateRandomSuffix(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder suffix = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            suffix.append(characters.charAt(index));
        }

        return suffix.toString();
    }


    @Override
    @Transactional
    public AccountResponseDto createAccount(AccountRequestDto requestDto) {
        validateAccountRequest(requestDto);

        UserEntity user = getUserById(requestDto.userId());

        String accountNumber = generateAccountNumber(user);

        if (accountRepository.existsByAccountNumber(accountNumber)) {
            throw new IllegalArgumentException(Constants.ErrorMessage.ACCOUNT_NUMBER_UNIQUE);
        }


        AccountEntity account = AccountEntity.builder()
                .user(user)
                .accountNumber(accountNumber)
                .balance(requestDto.balance())
                .currency(requestDto.currency())
                .status(AccountStatus.ACTIVE)
                .build();

        account = accountRepository.save(account);

        return mapToDto(account);
    }

    private AccountEntity getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(Constants.ErrorMessage.ACCOUNT_NOT_FOUND, id)));
    }

    @Override
    public BalanceResponseDto getBalance(Long id) {

        AccountEntity account = getAccountById(id);

        return new BalanceResponseDto(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency()
        );
    }


}
