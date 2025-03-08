package com.andersonzero0.apipostup.domain.posts.entity;

import com.andersonzero0.apipostup.domain.posts.dto.CreatePostDTO;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "posts_tb")
@Entity(name = "posts_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(name = "media_url")
    private String mediaUrl;

    @ElementCollection
    @CollectionTable(name = "post_hashtags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "hashtag")
    private List<String> hashtags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    private PostEntity parentPost;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> responses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, name = "user_id", insertable = false, updatable = false)
    private String userId;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;


    public PostEntity(CreatePostDTO createPostDTO, UserEntity user, PostEntity parentPost) {
        this.content = createPostDTO.content();
        this.user = user;
        this.userId = user.getId();
        this.parentPost = parentPost;
    }
}
