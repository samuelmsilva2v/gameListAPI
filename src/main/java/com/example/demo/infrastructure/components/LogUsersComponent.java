package com.example.demo.infrastructure.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.collections.LogUsers;
import com.example.demo.infrastructure.repositories.LogUsersRepository;

@Component
public class LogUsersComponent {
	
	@Autowired
	private LogUsersRepository logUsersRepository;
	
	public void saveLog(String user, String details) {
		
		var log = new LogUsers();
		
        log.setId(UUID.randomUUID());
		log.setUser(user);
		log.setDetails(details);
		log.setCreatedAt(new Date());

		logUsersRepository.save(log);
	}
}
