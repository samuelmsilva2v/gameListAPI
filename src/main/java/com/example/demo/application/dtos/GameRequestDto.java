package com.example.demo.application.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class GameRequestDto {

	@NotEmpty(message = "Por favor, informe o nome do jogo.")
	private String title;
	
	@NotEmpty(message = "Por favor, informe a descrição do jogo.")
	private String description;
	
	@NotEmpty(message = "Por favor, informe o gênero do jogo.")
	private String genre;
	
	@NotEmpty(message = "Por favor, informe a plataforma do jogo.")
	private String platform;
	
	@NotEmpty(message = "Por favor, informe o desenvolvedor do jogo.")
	private String publisher;
	
	@NotEmpty(message = "Por favor, informe a data de lançamento do jogo.")
	@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$", message = "Data inválida. O formato deve ser dd/MM/yyyy.")
	private String releaseDate; // dd/MM/yyyy
	private String imageUrl;
}
