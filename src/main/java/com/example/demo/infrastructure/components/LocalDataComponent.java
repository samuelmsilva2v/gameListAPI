package com.example.demo.infrastructure.components;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.models.entities.User;
import com.example.demo.domain.models.enums.Role;
import com.example.demo.infrastructure.repositories.UserRepository;

@Component
public class LocalDataComponent implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SHA256Component sha256Component;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Verifica se o usuário admin já existe
        if (!userRepository.existsByEmail("admin@email.com")) {
            var admin = new User();
            admin.setId(UUID.randomUUID());
            admin.setName("admin");
            admin.setEmail("admin@email.com");
            admin.setPassword(sha256Component.encrypt("@Admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Usuário ADMIN criado com sucesso.");
        }
    }
}