package com.example.demo.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.AuthenticateUserRequestDto;
import com.example.demo.application.dtos.AuthenticateUserResponseDto;
import com.example.demo.application.dtos.RegisterUserRequestDto;
import com.example.demo.application.dtos.RegisterUserResponseDto;
import com.example.demo.domain.services.interfaces.UserDomainService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserDomainService userDomainService;

	@Operation(summary = "Serviço para registrar um novo usuário.", description = "Register a new user")
	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponseDto> register(@RequestBody @Valid RegisterUserRequestDto request) {
		var response = userDomainService.registerUser(request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para autenticar um novo usuário.", description = "Authenticate a user")
	@PostMapping("/login")
	public ResponseEntity<AuthenticateUserResponseDto> authenticate(@RequestBody @Valid AuthenticateUserRequestDto request) {
		var response = userDomainService.authenticateUser(request);
		return ResponseEntity.ok(response);
	}
}
