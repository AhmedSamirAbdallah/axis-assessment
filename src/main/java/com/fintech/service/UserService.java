package com.fintech.service;

import com.fintech.common.dto.request.UserRequestDto;
import com.fintech.common.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);

    List<UserResponseDto> getUsers(Long id);
}
