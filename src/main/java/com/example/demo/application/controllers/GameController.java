package com.example.demo.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.GameRequestDto;
import com.example.demo.application.dtos.GameResponseDto;
import com.example.demo.domain.services.interfaces.GameDomainService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/games")
public class GameController {

	@Autowired
	private GameDomainService gameDomainService;
	
	@Operation(summary = "Serviço para adicionar um novo jogo.", description = "Add a new game")
	@PostMapping
	public ResponseEntity<GameResponseDto> post(@RequestBody @Valid GameRequestDto request) throws Exception {
		var response = gameDomainService.addGame(request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para editar um jogo existente.", description = "Edit a game by ID")
	@PutMapping("/{id}")
	public ResponseEntity<GameResponseDto> put(@RequestBody @Valid GameRequestDto request, @PathVariable UUID id) throws Exception {
		var response = gameDomainService.editGame(request, id);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para deletar um jogo existente.", description = "Delete a game by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<GameResponseDto> delete(@PathVariable UUID id) throws Exception {
		var response = gameDomainService.deleteGame(id);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para buscar um jogo existente.", description = "Get a game by ID")
	@GetMapping("/{id}")
	public ResponseEntity<GameResponseDto> get(@PathVariable UUID id) throws Exception {
		var response = gameDomainService.getGameById(id);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para buscar todos os jogos existentes." , description = "Get all games")
	@GetMapping
	public ResponseEntity<Iterable<GameResponseDto>> getAll() throws Exception {
		var response = gameDomainService.getAllGames();
		return ResponseEntity.ok(response);
	}
}
