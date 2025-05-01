package com.example.demo.domain.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Game {

	@Id
	private UUID id;
	private String name;
	private String description;
	private String genre;
	private String platform;
	private String publisher;
	private Date releaseDate;
	private String imageUrl;
}
