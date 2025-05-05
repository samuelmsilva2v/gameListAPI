package com.example.demo.application.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class GameRequestDto {

	private String name;
	private String description;
	private String genre;
	private String platform;
	private String publisher;
	private Date releaseDate;
	private String imageUrl;
}
