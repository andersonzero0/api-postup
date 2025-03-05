package com.andersonzero0.apipostup.domain.users.dto;

public record LoginResponseDTO(
        String token,
        UserResponseDTO user
) {
}
