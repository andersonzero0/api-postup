package com.andersonzero0.apipostup.services;

import com.andersonzero0.apipostup.domain.posts.dto.CreatePostDTO;
import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.posts.repository.PostRepository;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import com.andersonzero0.apipostup.exceptions.NotFoundException;
import com.andersonzero0.apipostup.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

        PostEntity parentPost = null;

        if (data.parentPostId() != null && !data.parentPostId().isBlank()) {
            parentPost = postRepository.findById(data.parentPostId())
                    .orElseThrow(() -> new NotFoundException("Parent post not found"));
        }

        var post = new PostEntity(data, user, parentPost);
        return postRepository.save(post);
    }

    public PostEntity findPostById(String postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public List<PostEntity> findAllMyPosts(UserEntity user) {
        return findAllMyPosts(user, false);
    }

    public List<PostEntity> findAllMyPosts(UserEntity user, boolean withResponses) {
        Sort sort = Sort.by(Sort.Order.desc("createdAt"));

        if (withResponses) {
            return postRepository.findAllByUser(user, sort);
        }

        return postRepository.findAllByUserAndParentPostIsNull(user, sort);
    }

    public PostEntity updatePost(UserEntity user, String postId, CreatePostDTO data) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new NotFoundException("Post not found");
        }

        post.setContent(data.content());

        return postRepository.save(post);
    }

    public void deletePost(UserEntity user, String postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new NotFoundException("Post not found");
        }

        postRepository.delete(post);
    }
}