package com.example.demo.infrastructure.components;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.collections.LogUsersLibrary;
import com.example.demo.infrastructure.repositories.LogUsersLibraryRepository;

@Component
public class LogUsersLibraryComponent {

	@Autowired
	private LogUsersLibraryRepository logUsersLibraryRepository;
	
	public void saveLog(String user, String operation, String details) {
		
		var log = new LogUsersLibrary();
		log.setId(UUID.randomUUID());
		log.setUser(user);
		log.setDetails(details);
		log.setOperation(operation);

		logUsersLibraryRepository.save(log);
	}
	
	public enum Operation {
		CREATE_LIBRARY,
		ADD_GAME_TO_LIBRARY,
		REMOVE_GAME_FROM_LIBRARY,
	}
}
