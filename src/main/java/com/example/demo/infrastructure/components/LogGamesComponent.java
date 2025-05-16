package com.example.demo.infrastructure.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.collections.LogGames;
import com.example.demo.infrastructure.repositories.LogGamesRepository;

@Component
public class LogGamesComponent {

	@Autowired
	private LogGamesRepository logGamesRepository;
	
	public void logGame(String game, String operation, String details) {
		
		var log = new LogGames();
		log.setId(UUID.randomUUID());
		log.setCreatedAt(new Date());
		log.setGame(game);
		log.setOperation(operation);
		log.setDetails(details);
		
		logGamesRepository.save(log);
	}
	
	public enum Operation {
		ADD, UPDATE, DELETE
	}
}
