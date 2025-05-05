package com.example.demo.application.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class GameResponseDto {

	private UUID id;
	private String name;
	private String description;
	private String genre;
	private String platform;
	private String publisher;
	private Date releaseDate;
	private String imageUrl;
}
