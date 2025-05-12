package com.example.demo.infrastructure.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerComponent {

	@RabbitListener(queues = "gamelistapi")
	public void receiveMessage(@Payload String message) {
		
		System.out.println("Mensagem recebida: " + message);
	}
}
