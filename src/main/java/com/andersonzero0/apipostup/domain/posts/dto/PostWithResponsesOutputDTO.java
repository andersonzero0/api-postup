package com.andersonzero0.apipostup.domain.posts.dto;

import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.users.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record PostWithResponsesOutputDTO(
        String id,
        String content,
        String mediaUrl,
        List<String> hashtags,
        PostOutputDTO parentPost,
        List<PostWithResponsesOutputDTO> responses,
        UserResponseDTO user,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public PostWithResponsesOutputDTO(PostEntity post) {
        this(
                post.getId(),
                post.getContent(),
                post.getMediaUrl(),
                post.getHashtags(),
                post.getParentPost() == null ? null : new PostOutputDTO(post.getParentPost()),
                post.getResponses().stream().map(PostWithResponsesOutputDTO::new).toList(),
                new UserResponseDTO(post.getUser()),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
