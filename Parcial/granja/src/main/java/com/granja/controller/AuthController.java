package com.granja.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Key key;

    public AuthController(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "1234".equals(password)) {
            String token = Jwts.builder()
                    .subject(username)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        }
        throw new RuntimeException("Credenciales inválidas");
    }
}
