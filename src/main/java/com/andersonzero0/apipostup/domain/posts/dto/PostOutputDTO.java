package com.andersonzero0.apipostup.domain.posts.dto;

import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.users.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record PostOutputDTO(
        String id,
        String content,
        String mediaUrl,
        List<String> hashtags,
        PostOutputDTO parentPost,
        UserResponseDTO user,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public PostOutputDTO(PostEntity post) {
        this(
                post.getId(),
                post.getContent(),
                post.getMediaUrl(),
                post.getHashtags(),
                post.getParentPost() == null ? null : new PostOutputDTO(post.getParentPost()),
                new UserResponseDTO(post.getUser()),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
