package com.example.demo.domain.collections;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "log_users")
public class LogUsers {

	@Id
	private UUID id;
	private Date createdAt;
	private String user;
	private String details;
}
