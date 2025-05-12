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

			var body = """
					    <div style="font-family: Arial, sans-serif; color: #333;">
					        <h2 style="color: #2c3e50;">👋 Bem-vindo(a), %s!</h2>
					        <p>Estamos muito felizes em ter você conosco na <strong>Biblioteca de Jogos</strong>.</p>
					        <p>Agora você pode explorar, salvar e gerenciar seus jogos favoritos diretamente na sua biblioteca personalizada.</p>
					        <p>Se tiver alguma dúvida ou sugestão, nossa equipe está sempre à disposição para ajudar.</p>
					        <p style="margin-top: 20px;">🎮 Divirta-se e aproveite a experiência!</p>
					        <hr style="margin-top: 30px;">
					        <p style="font-size: 12px; color: #999;">Este e-mail foi gerado automaticamente. © %d Biblioteca de Jogos</p>
					    </div>
					"""
					.formatted(user.getName(), java.time.Year.now().getValue());

			mailSenderComponent.sendMail(to, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
