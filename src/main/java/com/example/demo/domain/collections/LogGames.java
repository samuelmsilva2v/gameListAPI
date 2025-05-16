package com.example.demo.domain.collections;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "log_games")
public class LogGames {

	private UUID id;
	private Date createdAt;
	private String game;
	private String operation;
	private String details;
}
