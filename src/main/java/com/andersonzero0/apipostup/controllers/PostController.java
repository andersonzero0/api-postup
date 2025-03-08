package com.andersonzero0.apipostup.controllers;

import com.andersonzero0.apipostup.domain.posts.dto.CreatePostDTO;
import com.andersonzero0.apipostup.domain.posts.dto.PostOutputDTO;
import com.andersonzero0.apipostup.domain.posts.dto.PostWithResponsesOutputDTO;
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
    public PostOutputDTO createPost(@Valid @RequestBody CreatePostDTO createPostDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var post = postService.createPost(authUser, createPostDTO);

        return new PostOutputDTO(post);
    }

    @GetMapping("details/{id}")
    public PostWithResponsesOutputDTO getPostDetails(@PathVariable String id) {
        var post = postService.findPostById(id);

        return new PostWithResponsesOutputDTO(post);
    }

    @GetMapping("my")
    public List<PostOutputDTO> getMyPosts(@RequestParam(required = false) boolean withResponses) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var posts = postService.findAllMyPosts(authUser, withResponses);

        return posts.stream().map(PostOutputDTO::new).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public PostOutputDTO updatePost(@PathVariable String id, @Valid @RequestBody CreatePostDTO createPostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var post = postService.updatePost(authUser, id, createPostDTO);

        return new PostOutputDTO(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        postService.deletePost(authUser, id);
    }
}
