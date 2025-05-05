package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.application.dtos.GameRequestDto;
import com.example.demo.application.dtos.GameResponseDto;

public interface GameDomainService {

	GameResponseDto addGame(GameRequestDto request);
	
	GameResponseDto editGame(GameRequestDto request, UUID id);
	
	GameResponseDto deleteGame(UUID id);
	
	GameResponseDto getGameById(UUID id);
	
	List<GameResponseDto> getAllGames();
}
