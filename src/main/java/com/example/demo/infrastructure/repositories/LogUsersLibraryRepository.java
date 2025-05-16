package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.collections.LogUsersLibrary;

public interface LogUsersLibraryRepository extends MongoRepository<LogUsersLibrary, UUID> {

}
