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
import com.example.demo.infrastructure.components.LogUsersComponent;
import com.example.demo.infrastructure.components.LogUsersLibraryComponent;
import com.example.demo.infrastructure.components.LogUsersLibraryComponent.Operation;
import com.example.demo.infrastructure.components.RabbitMQProducerComponent;
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

	@Autowired
	private RabbitMQProducerComponent rabbitMQProducerComponent;

	@Autowired
	private LogUsersComponent logUsersComponent;

	@Autowired
	private LogUsersLibraryComponent logUsersLibraryComponent;

	@Override
	public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {

		if (userRepository.existsByEmail(request.getEmail()))
			throw new RuntimeException("Já existe um usuário com esse email. Tente outro.");

		var user = new User();
		user.setId(UUID.randomUUID());
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(sha256Component.encrypt(request.getPassword()));
		user.setRole(Role.USER);

		userRepository.save(user);

		rabbitMQProducerComponent.sendMessage(user);
		logUsersComponent.saveLog(user.getEmail(), "Usuário cadastrado com sucesso.");

		var userLibrary = new UserLibrary();
		userLibrary.setId(UUID.randomUUID());
		userLibrary.setUser(user);

		userLibraryRepository.save(userLibrary);
		logUsersLibraryComponent.saveLog(user.getName(), Operation.CREATE_LIBRARY.toString(),
				"Biblioteca do jogador " + user.getName() + " criada com sucesso.");

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
