package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.models.entities.UserLibrary;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, UUID> {

	UserLibrary findByUserId(UUID userId);
}
