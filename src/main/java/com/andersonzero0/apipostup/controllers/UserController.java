package com.andersonzero0.apipostup.controllers;

import com.andersonzero0.apipostup.domain.users.dto.RegisterUserDTO;
import com.andersonzero0.apipostup.domain.users.dto.UpdateUserDTO;
import com.andersonzero0.apipostup.domain.users.dto.UserResponseDTO;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import com.andersonzero0.apipostup.exceptions.UserNotFoundException;
import com.andersonzero0.apipostup.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User operations")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO registerUser(@Valid @RequestBody RegisterUserDTO data) {
        var newUser = userService.registerUser(new UserEntity(data));
        return new UserResponseDTO(newUser);
    }

    @GetMapping
    public List<UserResponseDTO> findAllUsers() {
        var users = userService.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    @GetMapping("profile")
    public UserResponseDTO findUserByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        if (authUser == null) {
            throw new UserNotFoundException("User not found");
        }

        return new UserResponseDTO(authUser);
    }

    @PutMapping
    public UserResponseDTO updateUser(@Valid @RequestBody UpdateUserDTO user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var userEntity = new UserEntity(user);
        userEntity.setId(authUser.getId());

        var updatedUser = userService.updateUser(userEntity);
        return new UserResponseDTO(updatedUser);
    }
}
