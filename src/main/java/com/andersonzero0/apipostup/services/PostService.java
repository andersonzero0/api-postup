package com.andersonzero0.apipostup.services;

import com.andersonzero0.apipostup.domain.posts.dto.CreatePostDTO;
import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.posts.repository.PostRepository;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import com.andersonzero0.apipostup.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public PostEntity createPost(UserEntity user, CreatePostDTO data) {
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        var post = new PostEntity(data, user);
        return postRepository.save(post);
    }

    public PostEntity findPostById(String postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public List<PostEntity> findAllMyPosts(UserEntity user) {
        return postRepository.findAllByUser(user);
    }
}