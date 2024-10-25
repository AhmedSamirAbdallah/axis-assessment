package com.fintech.service.Impl;

import com.fintech.common.dto.request.UserRequestDto;
import com.fintech.common.dto.response.UserResponseDto;
import com.fintech.common.entity.UserEntity;
import com.fintech.common.enums.UserAccountStatus;
import com.fintech.repository.UserRepository;
import com.fintech.service.UserService;
import com.fintech.utilty.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private void validateUserRequest(UserRequestDto requestDto) {
        if (requestDto.firstName() == null || requestDto.firstName().isBlank()) {
            throw new IllegalArgumentException(Constants.ErrorMessage.FIRST_NAME_REQUIRED);
        }

        if (requestDto.lastName() == null || requestDto.lastName().isBlank()) {
            throw new IllegalArgumentException(Constants.ErrorMessage.LAST_NAME_REQUIRED);
        }

        if (!isValidEmail(requestDto.email())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.EMAIL_INVALID);
        }

        if (requestDto.ssn() == null || requestDto.ssn().isBlank()) {
            throw new IllegalArgumentException(Constants.ErrorMessage.SSN_REQUIRED);
        }

        if (!isValidPhoneNumber(requestDto.phoneNumber())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.PHONE_NUMBER_INVALID);
        }

        if (requestDto.birthDate() != null && requestDto.birthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.BIRTH_DATE_FUTURE);
        }

        if (userRepository.existsByEmail(requestDto.email())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsBySsn(requestDto.ssn())) {
            throw new IllegalArgumentException(Constants.ErrorMessage.SSN_ALREADY_EXISTS);
        }

    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        String phoneRegex = "^\\+?[1-9]\\d{1,14}$";
        return phoneNumber.matches(phoneRegex);
    }

    private UserResponseDto mapToDto(UserEntity user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getSsn(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getAddress(),
                user.getStatus(),
                null
        );
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        validateUserRequest(requestDto);

        UserEntity user = UserEntity.builder()
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .ssn(requestDto.ssn())
                .phoneNumber(requestDto.phoneNumber())
                .birthDate(requestDto.birthDate())
                .address(requestDto.address())
                .securityAnswer(requestDto.securityAnswer())
                .status(UserAccountStatus.ACTIVE)
                .build();

        user = userRepository.save(user);

        return mapToDto(user);
    }

    private UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format(Constants.ErrorMessage.USER_NOT_FOUND, id)));
    }

    @Override
    public UserResponseDto getUser(Long id) {

        UserEntity user = getUserById(id);
        return mapToDto(user);
    }


}
