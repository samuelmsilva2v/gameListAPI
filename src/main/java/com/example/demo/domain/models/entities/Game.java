package com.example.demo.domain.models.entities;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Game {

	@Id
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private String platform;
	
	@Column(nullable = false)
	private String publisher;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date releaseDate;
	
	private String imageUrl;
}
