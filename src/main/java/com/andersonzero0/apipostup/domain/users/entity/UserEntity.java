package com.andersonzero0.apipostup.domain.users.entity;

import com.andersonzero0.apipostup.domain.posts.entity.PostEntity;
import com.andersonzero0.apipostup.domain.users.dto.RegisterUserDTO;
import com.andersonzero0.apipostup.domain.users.dto.UpdateUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users_tb")
@Entity(name = "users_tb")
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "username", unique = true)
    private String username;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PostEntity> posts;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public UserEntity(RegisterUserDTO registerUserDTO) {
        this.email = registerUserDTO.email();
        this.username = registerUserDTO.username();
        this.firstName = registerUserDTO.firstName();
        this.lastName = registerUserDTO.lastName();
        this.password = registerUserDTO.password();
    }

    public UserEntity(UpdateUserDTO updateUserDTO) {
        this.email = updateUserDTO.email();
        this.username = updateUserDTO.username();
        this.firstName = updateUserDTO.firstName();
        this.lastName = updateUserDTO.lastName();
        this.password = updateUserDTO.password();
    }
}
