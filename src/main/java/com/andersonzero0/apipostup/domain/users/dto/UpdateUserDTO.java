package com.andersonzero0.apipostup.domain.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateUserDTO(
        @Email(message = "Email is invalid")
        String email,

        @Pattern(regexp = "^[a-zA-Z0-9_.]+$",
                message = "Username can only contain alphanumeric characters, underscores and dots")
        String username,

        String firstName,

        String lastName,

        String password
) {
}
