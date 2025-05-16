package com.example.demo.domain.collections;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "log_userslibrary")
public class LogUsersLibrary {

	@Id
	private UUID id;
	private String user;
	private String game;
	private String operation;
	private String details;
}
