package com.example.SpringHalcones.controller;

import com.example.SpringHalcones.dto.login.LoginRequest;
import com.example.SpringHalcones.util.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        //Autenticar (verifica usuario y contraseña en BD)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        //Establecer contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Generar Token
        String jwt = jwtUtils.generateToken(authentication);

        //Devolver respuesta JSON
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", jwt);
        response.put("type", "Bearer");

        return ResponseEntity.ok(response);
    }
}