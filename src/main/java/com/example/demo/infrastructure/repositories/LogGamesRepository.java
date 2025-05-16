package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.collections.LogGames;

public interface LogGamesRepository extends MongoRepository<LogGames, UUID> {

}
