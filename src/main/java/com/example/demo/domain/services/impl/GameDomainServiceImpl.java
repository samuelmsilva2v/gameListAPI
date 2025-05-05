package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.GameRequestDto;
import com.example.demo.application.dtos.GameResponseDto;
import com.example.demo.domain.models.entities.Game;
import com.example.demo.domain.services.interfaces.GameDomainService;
import com.example.demo.infrastructure.repositories.GameRepository;

@Service
public class GameDomainServiceImpl implements GameDomainService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public GameResponseDto addGame(GameRequestDto request) {

		var game = modelMapper.map(request, Game.class);
		game.setId(UUID.randomUUID());

		gameRepository.save(game);

		return modelMapper.map(game, GameResponseDto.class);
	}

	@Override
	public GameResponseDto editGame(GameRequestDto request, UUID id) {

		var game = gameRepository.findById(id).get();
		
		modelMapper.map(request, game);

		gameRepository.save(game);

		return modelMapper.map(game, GameResponseDto.class);
	}

	@Override
	public GameResponseDto deleteGame(UUID id) {

		var game = gameRepository.findById(id).get();

		gameRepository.delete(game);

		return modelMapper.map(game, GameResponseDto.class);
	}

	@Override
	public GameResponseDto getGameById(UUID id) {

		var game = gameRepository.findById(id).get();

		return modelMapper.map(game, GameResponseDto.class);
	}

	@Override
	public List<GameResponseDto> getAllGames() {
		
		return gameRepository.findAll().stream().map(game -> modelMapper.map(game, GameResponseDto.class)).toList();
	}

}
