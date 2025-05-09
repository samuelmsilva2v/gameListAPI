package com.example.demo.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticateUserRequestDto {

	@Email(message = "Por favor, informe um e-mail válido")
	@NotEmpty(message = "Por favor, informe o e-mail de acesso.")
	private String email;
	
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha de acesso.")
	private String password;
}
