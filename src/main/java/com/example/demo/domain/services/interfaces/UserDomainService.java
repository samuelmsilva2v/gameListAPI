package com.example.demo.domain.services.interfaces;

import com.example.demo.application.dtos.AuthenticateUserRequestDto;
import com.example.demo.application.dtos.AuthenticateUserResponseDto;
import com.example.demo.application.dtos.RegisterUserRequestDto;
import com.example.demo.application.dtos.RegisterUserResponseDto;

public interface UserDomainService {

	public RegisterUserResponseDto registerUser(RegisterUserRequestDto request);
	
	public AuthenticateUserResponseDto authenticateUser(AuthenticateUserRequestDto request);
}
