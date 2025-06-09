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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/randomRequester")
    public ResponseEntity<ApiResponse<UserResponse.UserRandomResponseDTO>> getRequesterUser(){
        User user = userService.getRandomRequesterUser();
        var responseDTO = UserConverter.toRandomUserResponseDTO(user);
        return ResponseEntity.ok(ApiResponse.onSuccess(responseDTO));
    }

    @GetMapping("/randomCreator")
    public ResponseEntity<ApiResponse<UserResponse.UserRandomResponseDTO>> getCreatorUser(){
        User user = userService.getRandomCreatorUser();
        var responseDTO = UserConverter.toRandomUserResponseDTO(user);
        return ResponseEntity.ok(ApiResponse.onSuccess(responseDTO));
    }

    @GetMapping("/randomCharger")
    public ResponseEntity<ApiResponse<UserResponse.UserRandomResponseDTO>> getChargerUser(){
        User user = userService.getRandomChargerUser();
        var responseDTO = UserConverter.toRandomUserResponseDTO(user);
        return ResponseEntity.ok(ApiResponse.onSuccess(responseDTO));
    }
}
