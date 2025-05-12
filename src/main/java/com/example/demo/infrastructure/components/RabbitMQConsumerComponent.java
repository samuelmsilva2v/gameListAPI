package com.example.demo.infrastructure.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumerComponent {
	
	@Autowired
	private MailSenderComponent mailSenderComponent;
	
	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = "gamelistapi")
	public void receiveMessage(@Payload String message) {
		
		try {
			
			var user = objectMapper.readValue(message, User.class);
			var to = user.getEmail();
			var subject = "Novo usuário cadastrado";
			
			var body = "<h1>Um novo usuário foi cadastrado</h1>" + "<p>Nome: " + user.getName() + "</p>" + "<p>Email: "
					+ user.getEmail() + "</p>";
			
			mailSenderComponent.sendMail(to, subject, body);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
