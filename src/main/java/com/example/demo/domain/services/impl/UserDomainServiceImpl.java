package com.example.demo.domain.services.impl;

import java.time.Instant;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.AuthenticateUserRequestDto;
import com.example.demo.application.dtos.AuthenticateUserResponseDto;
import com.example.demo.application.dtos.RegisterUserRequestDto;
import com.example.demo.application.dtos.RegisterUserResponseDto;
import com.example.demo.domain.models.entities.User;
import com.example.demo.domain.models.entities.UserLibrary;
import com.example.demo.domain.models.enums.Role;
import com.example.demo.domain.services.interfaces.UserDomainService;
import com.example.demo.infrastructure.components.JwtTokenComponent;
import com.example.demo.infrastructure.components.SHA256Component;
import com.example.demo.infrastructure.repositories.UserLibraryRepository;
import com.example.demo.infrastructure.repositories.UserRepository;

@Service
public class UserDomainServiceImpl implements UserDomainService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLibraryRepository userLibraryRepository;
	
	@Autowired
	private SHA256Component sha256Component;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtTokenComponent jwtTokenComponent;

	@Override
	public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
		
		var user = new User();
		user.setId(UUID.randomUUID());
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(sha256Component.encrypt(request.getPassword()));
		user.setRole(Role.USER);
		
		userRepository.save(user);
		
		var userLibrary = new UserLibrary();
		userLibrary.setId(UUID.randomUUID());
		userLibrary.setUser(user);
		
		userLibraryRepository.save(userLibrary);
		
		var response = modelMapper.map(user, RegisterUserResponseDto.class);
		response.setCreatedAt(Instant.now());
		
		return response;
	}

	@Override
	public AuthenticateUserResponseDto authenticateUser(AuthenticateUserRequestDto request) {
		
		var user = userRepository.findByEmailAndPassword(request.getEmail(),
				sha256Component.encrypt(request.getPassword()));
		
		var response = modelMapper.map(user, AuthenticateUserResponseDto.class);
		response.setToken(jwtTokenComponent.getToken(user));
		
		return response;
	}

}
