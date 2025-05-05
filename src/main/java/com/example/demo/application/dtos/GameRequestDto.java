package com.example.demo.application.dtos;

import lombok.Data;

@Data
public class GameRequestDto {

	private String name;
	private String description;
	private String genre;
	private String platform;
	private String publisher;
	private String releaseDate; // dd/MM/yyyy
	private String imageUrl;
}
