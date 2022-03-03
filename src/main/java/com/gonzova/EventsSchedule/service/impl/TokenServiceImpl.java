package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class TokenServiceImpl implements TokenService {

    private final String secretKey = "Anastasia";

    @Override
    public String generateToken(Employee employee) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(employee.getCredential().getLogin())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(8).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public String extractUsernameAndValidate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
