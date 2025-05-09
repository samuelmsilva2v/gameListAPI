package com.example.demo.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequestDto {

	@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
	@NotEmpty(message = "Por favor, informe o nome do usuário.")
	private String name;

	@Email(message = "Por favor, informe um e-mail válido")
	private String email;

	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$", 
			message = "Informe a senha com letras minúculas, maiúsculas, números, símbolos e pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do usuário.")
	private String password;
}
