package com.example.demo.application.dtos;

import java.util.UUID;

import com.example.demo.domain.models.enums.Role;

import lombok.Data;

@Data
public class AuthenticateUserResponseDto {

	private UUID id;
	private String name;
	private String email;
	private Role role;
	private String token;
}
