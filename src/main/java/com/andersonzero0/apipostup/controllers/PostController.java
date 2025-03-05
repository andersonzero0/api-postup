package com.andersonzero0.apipostup.controllers;

import com.andersonzero0.apipostup.domain.posts.dto.CreatePostDTO;
import com.andersonzero0.apipostup.domain.posts.dto.PostResponseDTO;
import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import com.andersonzero0.apipostup.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public PostResponseDTO createPost(@Valid @RequestBody CreatePostDTO createPostDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var post = postService.createPost(authUser, createPostDTO);

        return new PostResponseDTO(post);
    }

    @GetMapping("my")
    public List<PostResponseDTO> getMyPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var posts = postService.findAllMyPosts(authUser);

        return posts.stream().map(PostResponseDTO::new).collect(Collectors.toList());
    }
}
