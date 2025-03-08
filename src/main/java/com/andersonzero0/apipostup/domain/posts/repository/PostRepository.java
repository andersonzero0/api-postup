package com.andersonzero0.apipostup.domain.posts.repository;

import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, String> {
    List<PostEntity> findAllByUser(UserEntity user, Sort sort);
    List<PostEntity> findAllByUserAndParentPostIsNull(UserEntity user, Sort sort);
}
