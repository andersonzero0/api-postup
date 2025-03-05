package com.andersonzero0.apipostup.controllers;

import com.andersonzero0.apipostup.domain.users.dto.AuthDTO;
import com.andersonzero0.apipostup.domain.users.dto.LoginResponseDTO;
import com.andersonzero0.apipostup.domain.users.dto.UserResponseDTO;
import com.andersonzero0.apipostup.domain.users.entity.UserEntity;
import com.andersonzero0.apipostup.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, new UserResponseDTO((UserEntity) auth.getPrincipal())));
    }
}
