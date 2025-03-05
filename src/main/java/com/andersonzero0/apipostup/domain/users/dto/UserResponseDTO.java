package com.andersonzero0.apipostup.domain.users.dto;

import com.andersonzero0.apipostup.domain.users.entity.UserEntity;

import java.time.LocalDateTime;

public record UserResponseDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String fullName,
        String avatarUrl,
        LocalDateTime createdAt
) {
    public UserResponseDTO(UserEntity userEntity) {
        this(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getFirstName() + " " + userEntity.getLastName(),
                userEntity.getAvatarUrl(),
                userEntity.getCreatedAt()
        );
    }
}

