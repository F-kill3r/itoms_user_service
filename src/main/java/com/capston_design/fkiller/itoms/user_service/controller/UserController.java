package com.capston_design.fkiller.itoms.user_service.controller;

import com.capston_design.fkiller.itoms.user_service.apiPayload.ApiResponse;
import com.capston_design.fkiller.itoms.user_service.converter.UserConverter;
import com.capston_design.fkiller.itoms.user_service.dto.UserRequest;
import com.capston_design.fkiller.itoms.user_service.dto.UserResponse;
import com.capston_design.fkiller.itoms.user_service.model.User;
import com.capston_design.fkiller.itoms.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse.UserCreateResponseDTO>> createUser(
            @RequestBody UserRequest userRequest) {

        User user = userService.createUser(userRequest);
        var responseDTO = UserConverter.toUserResponseDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(responseDTO));
    }
}
