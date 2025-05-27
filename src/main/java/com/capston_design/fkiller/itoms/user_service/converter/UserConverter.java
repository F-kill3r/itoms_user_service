package com.capston_design.fkiller.itoms.user_service.converter;

import com.capston_design.fkiller.itoms.user_service.dto.UserResponse;
import com.capston_design.fkiller.itoms.user_service.model.User;

public class UserConverter {
    public static UserResponse.UserCreateResponseDTO toUserResponseDTO(User user) {
        return UserResponse.UserCreateResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .category(user.getCategory())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
