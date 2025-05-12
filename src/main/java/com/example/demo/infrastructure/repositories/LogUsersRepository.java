package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.collections.LogUsers;

public interface LogUsersRepository extends MongoRepository<LogUsers, UUID> {

}
