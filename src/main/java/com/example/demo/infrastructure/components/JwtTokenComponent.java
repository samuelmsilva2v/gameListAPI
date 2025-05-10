package com.example.demo.infrastructure.components;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.domain.models.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {

	public String getToken(User user) {

		var secretKey = "9658011f-94ec-49e6-8602-f517a94fc6bb";
		return Jwts.builder().setSubject(user.getEmail()).claim("role", user.getRole()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1800000))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
	
	
}
