package com.fintech.mapper;

import com.fintech.common.dto.request.UserRequestDto;
import com.fintech.common.dto.response.UserResponseDto;
import com.fintech.common.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    UserResponseDto toUserResponseDto(UserEntity user);

    UserEntity toUserEntity(UserRequestDto requestDto);

}
