package com.andersonzero0.apipostup.domain.posts.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePostDTO(
        @NotBlank(message = "Content is required")
        String content
) {
}
