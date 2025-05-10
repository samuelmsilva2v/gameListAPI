package com.example.demo.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.UserLibraryResponseDto;
import com.example.demo.domain.services.interfaces.UserLibraryDomainService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user-library")
public class UserLibraryController {

	@Autowired
	private UserLibraryDomainService userLibraryDomainService;

	@Operation(summary = "Serviço para consultar a biblioteca de jogos de um usuário.", description = "Get user library by user ID")
	@GetMapping("/{userId}")
	public ResponseEntity<UserLibraryResponseDto> getUserLibrary(@PathVariable UUID userId) {
		UserLibraryResponseDto userLibrary = userLibraryDomainService.getUserLibrary(userId);
		return ResponseEntity.ok(userLibrary);
	}

	@Operation(summary = "Serviço para adicionar um jogo à biblioteca de um usuário.", description = "Add game to user library")
	@PostMapping("/{userId}/add-game/{gameId}")
	public ResponseEntity<UserLibraryResponseDto> addGameToLibrary(@PathVariable UUID userId,
			@PathVariable UUID gameId) {
		UserLibraryResponseDto userLibrary = userLibraryDomainService.addGameToLibrary(userId, gameId);
		return ResponseEntity.ok(userLibrary);
	}

	@Operation(summary = "Serviço para remover um jogo da biblioteca de um usuário.", description = "Remove game from user library")
	@PutMapping("/{userId}/remove-game/{gameId}")
	public ResponseEntity<UserLibraryResponseDto> removeGameFromLibrary(@PathVariable UUID userId,
			@PathVariable UUID gameId) {
		UserLibraryResponseDto userLibrary = userLibraryDomainService.removeGameFromLibrary(userId, gameId);
		return ResponseEntity.ok(userLibrary);
	}
}
