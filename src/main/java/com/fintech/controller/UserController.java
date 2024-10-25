package com.fintech.controller;

import com.fintech.common.dto.request.UserRequestDto;
import com.fintech.common.dto.response.UserResponseDto;
import com.fintech.payload.ApisResponse;
import com.fintech.service.UserService;
import com.fintech.utilty.Constants;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Creates a new user and returns the user details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
    @PostMapping
    public ApisResponse<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return ApisResponse.success(userService.createUser(requestDto), Constants.SuccessMessage.USER_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve users", description = "Retrieves a list of users or a specific user by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "No users found", content = @Content)
    })
    @GetMapping
    public ApisResponse<List<UserResponseDto>> getUsers(@Parameter(description = "ID of the user to retrieve") @RequestParam(required = false) Long id) {
        return ApisResponse.success(userService.getUsers(id), Constants.SuccessMessage.USER_RETRIEVED_SUCCESSFULLY, HttpStatus.OK);
    }
}
