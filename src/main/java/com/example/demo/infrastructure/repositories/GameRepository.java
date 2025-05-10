package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.models.entities.Game;

public interface GameRepository extends JpaRepository<Game, UUID> {

	boolean existsByTitle(String title);
}
